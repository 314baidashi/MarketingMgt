package com.universe.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author yuanjs
 * @description:
 * @date 2020-06-22 8:04
 */
@Slf4j
@Component
@Order(value = 1)
public class DirtyDataRunnerImpl implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.debug("SpringBoot启动后执行……");

    }
}
