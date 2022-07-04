package com.universe.handler;

/**
 * @author yuanjs
 * @description:
 * @date 2019-11-23 20:14
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

}