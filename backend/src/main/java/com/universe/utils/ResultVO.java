package com.universe.utils;

import lombok.Data;

/**
 * @author yuanjs
 * @version 1.0.0
 * @ClassName ResultVO
 * @Description TODO
 * @createTime 2021年04月29日 07:56:00
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String message;

    private T data;

    public ResultVO() {
    }

    public ResultVO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultVO(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


}