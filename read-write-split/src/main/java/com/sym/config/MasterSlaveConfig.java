package com.sym.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.api.config.masterslave.LoadBalanceStrategyConfiguration;
import org.apache.shardingsphere.api.config.masterslave.MasterSlaveRuleConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.MasterSlaveDataSourceFactory;
import org.apache.shardingsphere.shardingjdbc.jdbc.core.datasource.MasterSlaveDataSource;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 读写分离的配置类, 效果等同于yaml配置文件
 *
 * @author shenyanming
 * Created on 2020/5/14 13:55
 */
// @Configuration
public class MasterSlaveConfig {

    /**
     * 创建sharding-jdbc的主从复制数据源
     * @return {@link MasterSlaveDataSource}
     */
    @Bean
    public DataSource masterSlaveDataSource() throws SQLException {
        // 最全需要指定4个参数：
        // 1.表示这个主从规则的名称;
        // 2.表示主库数据源的名称;
        // 3.表示从库数据源的名称集合
        // 4.从库的负载均衡策略
        MasterSlaveRuleConfiguration  masterSlaveRuleConfig = new MasterSlaveRuleConfiguration(
                "sym_rules", "mysql_master", Arrays.asList("mysql_slave_1", "mysql_slave_2"),
                new LoadBalanceStrategyConfiguration(""));

        // 创建实际数据源, 自行定义主库数据源, 和两个从库数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("mysql_master", new HikariDataSource());
        dataSourceMap.put("mysql_slave_1", new HikariDataSource());
        dataSourceMap.put("mysql_slave_2", new HikariDataSource());

        // 返回代理数据源
        return MasterSlaveDataSourceFactory.createDataSource(dataSourceMap, masterSlaveRuleConfig, new Properties());
    }
}
