package com.universe.utils;

import java.util.UUID;

/**
 * @author yuanjs
 * @description:
 * @date 2020-08-01 18:05
 */
public class UUIDUtils {
    public static String generateId() {
        return UUID.randomUUID().toString().replace("-","");
    }
}
