package com.sym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * sharding-jdbc的读写分离, 是将增删改的SQL路由到主库上, 将查询的SQL路由到从库,
 * 而它自己本身是不做主从同步的, 需要数据库自身支持, 例如MySQL支持主从复制。
 *
 * @author shenyanming
 * @date 2020/5/13 22:48.
 */
@SpringBootApplication
public class ShardingJdbcApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcApplication.class, args);
    }
}
