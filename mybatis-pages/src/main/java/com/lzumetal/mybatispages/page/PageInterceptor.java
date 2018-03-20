package com.lzumetal.mybatispages.page;

import com.google.gson.Gson;
import com.lzumetal.mybatispages.thread.ExecutorUtil;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;

import static com.lzumetal.mybatispages.page.PageConfig.MYSQL_DIALECT;

/**
 * 通过拦截<code>StatementHandler</code>的<code>prepare</code>方法，重写sql语句实现物理分页。
 * 签名里要拦截的类型只能是接口。
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class PageInterceptor implements Interceptor {

    private final static Logger logger = LoggerFactory.getLogger(PageInterceptor.class);
    private final static String DEFAULT_PAGE_SQL_ID = ".+ByPage$"; // 需要拦截的ID(正则匹配)
    private String dialectName; // 数据库类型
    private final static String DEFAULT_DIALECT_NAME = MYSQL_DIALECT; // 数据库类型

    @Autowired
    private DataSource dataSource;


    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        //获取StatementHandler，默认是RoutingStatementHandler
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();

        //获取statementHandler包装类
        MetaObject MetaObjectHandler = SystemMetaObject.forObject(statementHandler);

        // 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环可以分离出最原始的的目标类
        while (MetaObjectHandler.hasGetter("h")) {
            Object obj = MetaObjectHandler.getValue("h");
            MetaObjectHandler = SystemMetaObject.forObject(obj);
        }

        //分离最后一个代理对象的目标类
        while (MetaObjectHandler.hasGetter("target")) {
            Object obj = MetaObjectHandler.getValue("target");
            MetaObjectHandler = SystemMetaObject.forObject(obj);
        }

        //获取连接对象
        //Connection connection = (Connection) invocation.getArgs()[0];

        //获取StatementHandler的实现类
        //object.getValue("delegate");

        //此处的configuration是指的mybatis的配置文件。configuration.getVariables()是myabatis文件中的<properties>标签下的配置内容
        Configuration configuration = (Configuration) MetaObjectHandler.getValue("delegate.configuration");
        String pageSqlId = "";
        if (configuration.getVariables() != null) {
            dialectName = configuration.getVariables().getProperty("dialectName");
            if (null == dialectName || "".equals(dialectName)) {
                logger.warn("Property dialectName is not setted,use default 'mysql' ");
                dialectName = DEFAULT_DIALECT_NAME;
            }
            pageSqlId = configuration.getVariables().getProperty("pageSqlId");
            if (null == pageSqlId || "".equals(pageSqlId)) {
                logger.warn("Property pageSqlId is not setted,use default '.+Page$' ");
                pageSqlId = DEFAULT_PAGE_SQL_ID;
            }
        } else {
            dialectName = DEFAULT_DIALECT_NAME;
            pageSqlId = DEFAULT_PAGE_SQL_ID;
        }


        //获取查询接口映射的相关信息
        MappedStatement mappedStatement = (MappedStatement) MetaObjectHandler.getValue("delegate.mappedStatement");
        String mapId = mappedStatement.getId();

        //statementHandler.getBoundSql().getParameterObject();

        //拦截以.ByPage结尾的请求，重写这些请求的sql语句，这是分页功能的统一实现
        if (mapId.matches(pageSqlId)) {
            //获取进行数据库操作时管理参数的handler
            ParameterHandler parameterHandler = (ParameterHandler) MetaObjectHandler.getValue("delegate.parameterHandler");
            //获取请求时的参数
            Map<String, Object> paramMap = (Map<String, Object>) parameterHandler.getParameterObject();
            //也可以这样获取
            //paramMap = (Map<String, Object>) statementHandler.getBoundSql().getParameterObject();

            //参数名称和在service中设置到map中的名称一致
            Pagination pagination = (Pagination) paramMap.get("pagination");

            String sql = (String) MetaObjectHandler.getValue("delegate.boundSql.sql");
            //也可以通过statementHandler直接获取
            //sql = statementHandler.getBoundSql().getSql();

            String countSql = changeToCountSql(sql);
            // 同jdbc一个流程查询sql语句
            Connection connection = (Connection) invocation.getArgs()[0];
            PreparedStatement preparedStatement = connection.prepareStatement(countSql);
            // 为了先查询总条数，所以需要先统计原始sql结果，但是原始sql中参数还没赋值，所以就需要先拿到原始sql的参数处理对象，通过反射工具
            parameterHandler.setParameters(preparedStatement);
            // 参数被设置以后，直接执行sql语句得到结果集合
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                // 将查询到的结果集合设置到pagination中
                pagination.setTotalCount(resultSet.getInt(1));
                pagination.setTotalPage(pagination.getTotalPageCount());
                pagination.refresh();
            }

            //获取具体的分页实现
            Dialect dialect = DialectFactory.createDialect(dialectName);
            String limitSql = dialect.changeToPageSql(sql, pagination);
            // 采用物理分页后，就不需要mybatis的内存分页了，所以可以把rowBounds的偏移量恢复为初始值(offet:0,limit:Integer.max)
//            MetaObjectHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
//            MetaObjectHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);

            //将构建完成的分页sql语句赋值个体'delegate.boundSql.sql'，偷天换日
            MetaObjectHandler.setValue("delegate.boundSql.sql", limitSql);
        }

        //调用原对象的方法，进入责任链的下一级（将执行权交给下一个拦截器）
        return invocation.proceed();
    }

    /**
     * 根据数据库类型，生成特定的分页sql
     *
     * @param sql
     * @param pageParam
     * @return
     */
    private String buildPageSql(String sql, Pagination pageParam) {
        if (pageParam != null) {
            Dialect dialect = DialectFactory.createDialect(dialectName);
            if (dialect != null) {
                return dialect.changeToPageSql(sql, pageParam);
            }
        }
        return sql;

    }

    private String changeToCountSql(String originalSql) {
        return "SELECT COUNT(0) FROM (" + originalSql + ") ALIAS";
    }


    //获取代理对象
    @Override
    public Object plugin(Object target) {
        //生成object对象的动态代理对象
        // 当目标类是StatementHandler类型时，才包装目标类，否者直接返回目标本身,减少目标被代理的次数
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    //设置代理对象的参数
    @Override
    public void setProperties(Properties properties) {
        //如果项目中分页的pageSize是统一的，也可以在这里统一配置和获取，这样就不用每次请求都传递pageSize参数了。参数是在配置拦截器时配置的。
        /*String limit1 = properties.getProperty("limit", "10");
        this.pageSize = Integer.valueOf(limit1);*/
        this.dialectName = properties.getProperty("dialectName");
        if (dialectName == null || dialectName.equals("")) {
            throw new RuntimeException("Mybatis分页插件PageInterceptor无法获取dialectName参数!");
        }
    }
}
