package com.myDemo.common.bean;

import lombok.Data;

/**
 * 统一消息处理
 * 基础响应消息
 */
@Data
public class BaseResponse<T> {
    /**
     * 服务器响应数据
     */
    private T payload;

    /**
     * 请求是否成功
     */
    private boolean success;

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 状态码
     */
    private int code = -1;

    /**
     * 服务器响应时间
     */
    private long timestamp;

    public BaseResponse() {
        this.timestamp = System.currentTimeMillis() / 1000;
    }

    public BaseResponse(boolean success) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
    }

    public BaseResponse(boolean success, T payload) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.payload = payload;
    }

    public BaseResponse(boolean success, T payload, int code) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.payload = payload;
        this.code = code;
    }

    public BaseResponse(boolean success, String msg) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.msg = msg;
    }

    public BaseResponse(boolean success, String msg, int code) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.msg = msg;
        this.code = code;
    }

    public static BaseResponse ok() {
        return new BaseResponse(true);
    }

    public static <T> BaseResponse ok(T payload) {
        return new BaseResponse(true, payload);
    }

    public static <T> BaseResponse ok(int code) {
        return new BaseResponse(true, null, code);
    }

    public static <T> BaseResponse ok(T payload, int code) {
        return new BaseResponse(true, payload, code);
    }

    public static BaseResponse fail() {
        return new BaseResponse(false);
    }

    public static BaseResponse fail(String msg) {
        return new BaseResponse(false, msg);
    }

    public static BaseResponse fail(int code) {
        return new BaseResponse(false, null, code);
    }

    public static BaseResponse fail(int code, String msg) {
        return new BaseResponse(false, msg, code);
    }

}
