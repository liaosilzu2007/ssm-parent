package com.lzumetal.mybatispages.page;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Mybatis拦截器PageInterceptor中启动的任务，用于新启线程，计算totalCount总数量
 */
//@SuppressWarnings("all")
public class PageCountTask implements Callable {

    private final static Logger logger = LoggerFactory.getLogger(PageCountTask.class);

    private String originalSql;//原始的查询结果集的SQL
    private Pagination page;
    private MappedStatement mappedStatement;
    private BoundSql boundSql;
    private DataSource dataSource;

    public PageCountTask(String originalSql, Pagination page, MappedStatement mappedStatement, BoundSql boundSql, DataSource dataSource) {
        this.originalSql = originalSql;
        this.page = page;
        this.mappedStatement = mappedStatement;
        this.boundSql = boundSql;
        this.dataSource = dataSource;
    }


    @Override
    public Object call() throws Exception {
        Connection connection = dataSource.getConnection();

        //记录总记录数
        String countSql = "select count(0) from (" + originalSql + ")  total";
        PreparedStatement countStmt = null;
        ResultSet rs = null;
        try {
            countStmt = connection.prepareStatement(countSql);
            //去除追加的endRown beginRown
            List<ParameterMapping> countToalMapping = new ArrayList<ParameterMapping>();
            countToalMapping.addAll(boundSql.getParameterMappings());
            if (countToalMapping.size() > 0) {
                for (int i = countToalMapping.size() - 1; i >= 0; i--) {
                    ParameterMapping para = countToalMapping.get(i);
                    String pro = para.getProperty();
                    if ("endRown".equals(pro) || "beginRown".equals(pro))
                        countToalMapping.remove(para);
                }
            }
            //生成count相关的BoundSql
            BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql, countToalMapping, boundSql.getParameterObject());

            //往countStmt中设置参数
            ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, boundSql.getParameterObject(), countBS);
            parameterHandler.setParameters(countStmt);

            //执行count
            rs = countStmt.executeQuery();
            int totalCount = 0;
            if (rs.next()) totalCount = rs.getInt(1);
            page.setTotalCount(totalCount);
            int totalPage = totalCount / page.getPageSize() + ((totalCount % page.getPageSize() == 0) ? 0 : 1);
            page.setTotalPage(totalPage);
            logger.warn(countSql.replaceAll("[\\t\\n\\r]", "").replaceAll("[\\\\]", ".").replaceAll("\"", ""));
            //logger.debug(String.valueOf(boundSql.getParameterObject()));
            logger.warn("Total:" + String.valueOf(totalCount));
        } catch (Exception e) {
            logger.error("Ignore this exception", e);
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (Exception e) {
                logger.error("Ignore this exception", e);
            }
            try {
                if (countStmt != null) countStmt.close();
            } catch (Exception e) {
                logger.error("Ignore this exception", e);
            }
            try {
                if (connection != null) connection.close();
            } catch (Exception e) {
                logger.error("Ignore this exception", e);
            }
            //总数统计完后 清空 futureResult
            page.setFutureResult(null);
        }


        return "get";
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
