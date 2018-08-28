package com.sport.peking.poper.util;

import java.util.Date;

/**
 * @author miracle
 * create at 2018-08-27 17:04
 */
public class Result {

    Integer code;
    String description;
    Object data;

    public Result() {
    }

    public Result(Integer code) {
        this.code = code;
    }

    public Result(Integer code, String description) {
        this(code, description, null);
    }

    public Result(Integer code, String description, Object data) {
        this.code = code;
        this.description = description;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Result success() {
        return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage());
    }

    public static Result success(Object data) {
        Result result = new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    public static Result error() {
        return new Result(ResultCode.ERROR.getCode(), ResultCode.ERROR.getMessage());
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", description='" + description + '\'' +
                ", data=" + data +
                '}';
    }
}
