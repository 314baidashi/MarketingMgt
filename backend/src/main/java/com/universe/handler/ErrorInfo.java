package com.universe.handler;

import lombok.Data;

/**
 * @author yuanjs
 * @description:
 * @date 2019-11-23 20:13
 */
@Data
public class ErrorInfo<T> {

    public static final Integer OK = 0;
    public static final Integer ERROR = 500;
    private String errorType;
    private Integer code;
    private String message;
    private String url;
    private T exception;
}
