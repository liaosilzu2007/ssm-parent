package com.lzumetal.mybatispages.enums;

public enum DBType {

    MYSQL(1, "MySQL"),
    ORACLE(2, "oracle");


    private final Integer code;
    private final String dbName;

    DBType(Integer code, String dbName) {
        this.code = code;
        this.dbName = dbName;
    }

    public Integer getCode() {
        return code;
    }

    public String getDbName() {
        return dbName;
    }
}
