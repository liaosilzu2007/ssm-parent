package com.lzumetal.mybatispages.page;


public class OracleDialect implements Dialect {

    /**
     * 参考hibernate的实现完成oracle的分页
     *
     * @param sql
     * @param pageParam
     * @return
     */
    @Override
    public String changeToPageSql(String sql, Pagination pageParam) {

        //将SQL构造为分页SQL
        StringBuilder pageSql = new StringBuilder(sql.length() + 100);
        String beginRow = String.valueOf((pageParam.getCurrentPage() - 1) * pageParam.getPageSize());
        String endrow = String.valueOf(pageParam.getCurrentPage() * pageParam.getPageSize());
        pageSql.append("SELECT * FROM ( SELECT TEMP.*, ROWNUM ROW_ID FROM ( ");
        pageSql.append(sql);
        pageSql.append(" ) TEMP  WHERE ROWNUM <= " + endrow + " ) WHERE ROW_ID > " + beginRow);

        return pageSql.toString();
    }
}
