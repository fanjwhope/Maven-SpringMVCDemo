package com.myDemo.common.bean;

/**
 * 异常消息
 */
public class BussinessException extends RuntimeException {
    private int code;
    private String message;

    public BussinessException(String message) {
        this.code = 500;
        this.message = message;
    }

    public BussinessException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
