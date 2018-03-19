package com.lzumetal.mybatispages.page;

public interface Dialect {

    String changeToPageSql(String sql, Pagination parameter);
}
