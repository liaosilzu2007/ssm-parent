package com.lzumetal.mybatispages.page;

import com.lzumetal.mybatispages.exception.RestException;

public class DialectFactory {

    public static Dialect createDialect(String dialectName) {
        if (PageConfig.ORACLE_DIALECT.equals(dialectName)) {
            return new OracleDialect();
        } else if (PageConfig.MYSQL_DIALECT.equals(dialectName)) {
            return new MySQLDialect();
        }
        throw new RestException("Dialect create failed: diealectName is invalid, the dialectName = " + dialectName);
    }
}
