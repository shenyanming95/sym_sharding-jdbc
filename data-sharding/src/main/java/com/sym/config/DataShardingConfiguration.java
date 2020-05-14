package com.sym.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.ShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.apache.shardingsphere.shardingjdbc.jdbc.core.datasource.ShardingDataSource;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * java配置例子
 *
 * @author shenyanming
 * Created on 2020/5/14 14:11
 */
public class DataShardingConfiguration {

    /**
     * 创建sharding-jdbc用于分片的数据源
     * @return {@link ShardingDataSource}
     */
    @Bean
    public DataSource shardingDataSource() throws SQLException {
        // 获取实际的数据源
        Map<String, DataSource> dataSourceMap = this.getDataSourceMap();

        // 额外的属性配置
        Properties properties = new Properties();
        properties.setProperty("sql.show", "true");

        // 分片配置
        ShardingRuleConfiguration config = this.getShardingRuleConfiguration();

        // 返回用于分片的数据源
        return ShardingDataSourceFactory.createDataSource(dataSourceMap, config, properties);
    }

    /**
     * 数据分片的配置
     * @return ShardingRuleConfiguration
     */
    private ShardingRuleConfiguration getShardingRuleConfiguration(){
        ShardingRuleConfiguration configuration = new ShardingRuleConfiguration();

        /* 分片规则配置 - start */
        // 创建配置类, 需要指定逻辑表名和实际数据节点表达式
        TableRuleConfiguration tableRuleConfiguration = new TableRuleConfiguration("t_simple",
                "db${1..2}.t_simple{1..2}");
        // 主键生成策略
        KeyGeneratorConfiguration keyGeneratorConfiguration = new KeyGeneratorConfiguration("SNOWFLAKE", "simple_id");
        tableRuleConfiguration.setKeyGeneratorConfig(keyGeneratorConfiguration);

        // 分库策略配置
        ShardingStrategyConfiguration databaseShardingStrategyConfig = new InlineShardingStrategyConfiguration(
                "simple_id", "db${simple_id % 2 + 1}");
        tableRuleConfiguration.setDatabaseShardingStrategyConfig(databaseShardingStrategyConfig);

        // 分表策略配置
        ShardingStrategyConfiguration tableShardingStrategyConfig = new InlineShardingStrategyConfiguration(
                "simple_id", "t_simple_${simple_id % 2 + 1}");
        tableRuleConfiguration.setTableShardingStrategyConfig(tableShardingStrategyConfig);
        /* 分片规则配置 - end */

        configuration.setTableRuleConfigs(Collections.singleton(tableRuleConfiguration));
        return configuration;
    }

    /**
     * 实际数据源配置, 每个分库的数据源都需要配置在这里
     * @return map,key-为自定义的数据源名称; value-为数据源实例
     */
    private Map<String, DataSource> getDataSourceMap(){
        Map<String, DataSource> retMap = new HashMap<>();
        retMap.put("db1", new HikariDataSource());
        retMap.put("db2", new HikariDataSource());
        return retMap;
    }
}
