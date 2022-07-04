package com.universe.marketing.users.entity.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class R {
    private Integer code;
    private Object data;
    private String msg;


    public R(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }


    public R(String msg) {
        this.msg = msg;
    }

    public R(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static R ok() {
        return new R(200, null, null);
    }

    public static R ok(Object data) {
        return new R(200, data, null);
    }

    public static R ok(String msg) {
        return new R(200, null, msg);
    }
    public static R ok(Object data,String msg) {
        return new R(200, data, msg);
    }
    public static R fail(String errorMsg) {
        return new R(400, null, errorMsg);
    }

    public static R error(Integer code, String msg) {
        return new R(code, msg);
    }
}
