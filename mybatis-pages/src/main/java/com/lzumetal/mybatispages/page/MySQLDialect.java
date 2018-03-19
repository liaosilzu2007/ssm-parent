package com.lzumetal.mybatispages.page;


public class MySQLDialect implements Dialect {


    /**
     * 实现mysql的分页语句
     * @param sql
     * @param pageParam
     * @return
     */
    @Override
    public String changeToPageSql(String sql, Pagination pageParam) {
        StringBuilder pageSql = new StringBuilder(100);
        int beginrow = (pageParam.getCurrentPage() - 1) * pageParam.getPageSize();
        pageSql.append(sql);
        pageSql.append(" limit " + beginrow + "," + pageParam.getPageSize());
        return pageSql.toString();
    }
}
