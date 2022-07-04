package com.universe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication//(scanBasePackages = {"com.universe"})
@MapperScan("com.universe.marketing.*.mapper")
public class MarketingMgtApplication {
    public static void main(String[] args) {
        SpringApplication.run(MarketingMgtApplication.class, args);
    }
}
