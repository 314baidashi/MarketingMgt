package com.universe.marketing.client.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回的结果集
 */
@Data
public class Result implements Serializable {
    private int code; //200成功， 400失败
    private String msg;
    private Object data;


    public static Result success() {
        return success(200, "操作成功！", null);
    }

    public static Result success(String msg) {
        return success(200, msg, null);
    }

    public static Result success(Object data) {
        return success(200, "操作成功！", data);
    }

    public static Result success(String msg, Object data) {
        return success(200, msg, data);
    }

    public static Result success(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static Result fail() {
        return success(400, "操作失败！", null);
    }

    public static Result fail(Object data) {
        return fail(400, "操作失败！", data);
    }

    public static Result fail(String msg) {
        return fail(400, msg, null);
    }

    public static Result fail(String msg, Object data) {
        return fail(400, msg, data);
    }

    public static Result fail(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
}
