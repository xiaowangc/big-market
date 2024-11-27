package com.chige;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@Slf4j
@EnableDubbo
@SpringBootApplication
public class TestApplication {
    public static void main( String[] args ) {
        SpringApplication.run(TestApplication.class);
        log.info("Test测试服务启动成功...");
    }
}
