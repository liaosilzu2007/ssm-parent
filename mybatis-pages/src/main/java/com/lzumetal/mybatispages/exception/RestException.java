package com.lzumetal.mybatispages.exception;

public class RestException extends RuntimeException {

    private String msg;

    public RestException(String msg) {
        this.msg = msg;
    }

    public RestException(String message, String msg) {
        super(message);
        this.msg = msg;
    }

    public RestException(String message, Throwable cause, String msg) {
        super(message, cause);
        this.msg = msg;
    }

    public RestException(Throwable cause, String msg) {
        super(cause);
        this.msg = msg;
    }




}
