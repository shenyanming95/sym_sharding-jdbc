package com.sym;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author shenyanming
 * @date 2020/5/10 16:35.
 */
@SpringBootApplication
@MapperScan("com.sym.mapper")
public class ShardingJdbcApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication();
        springApplication.run(args);
    }
}
