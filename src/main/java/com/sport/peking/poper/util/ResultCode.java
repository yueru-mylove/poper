package com.sport.peking.poper.util;

/**
 *
 * @author miracle
 * create at 2018-08-27 17:08
 */
public enum  ResultCode {

    SUCCESS(0,"OK"),
    ERROR_DEFAULT(-99999, "发生异常，未知错误"),
    ERROR(-1,"系统繁忙，请稍后重试"),
    ERROR_INVALID_PARAMS(-2,"请求报文参数缺失或无效:"),
    ERROR_HTTP_REQUEST_FAIL(-3,"HTTP接口调用失败"),
    ERROR_EXCEPTION_DATA(-4, "数据异常"),
    ERROR_BIZ_HTTP_REQUEST_FAIL(-5, "第三方http请求返回异常");

    private int code;
    private String message;

    ResultCode() {
    }

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
