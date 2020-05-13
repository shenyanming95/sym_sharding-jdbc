package com.sym;

import com.sym.entity.SimpleEntity;
import com.sym.mapper.SimpleMapper;
import com.sym.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * sharding-jdbc分库分表, 测试类
 *
 * @author shenyanming
 * @date 2020/5/10 20:02.
 */
@SpringBootTest(classes = ShardingJdbcApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class SimpleRepositoryTest {

    @Autowired
    private SimpleMapper simpleMapper;

    /**
     * 分库分表后, 执行插入
     */
    @Test
    public void insertTest() {
        SimpleEntity simpleEntity = new SimpleEntity();
        simpleEntity.setName("test_2020").setDescription("sharding-jdbc").setDeleted(false);
        simpleMapper.insert(simpleEntity);
    }

    /**
     * 分库分表后, 批量插入
     */
    @Test
    public void batchInsertTest() {
        SimpleEntity simpleEntity = new SimpleEntity();
        for (int i = 0; i < 10; i++) {
            simpleEntity.setName(RandomUtil.getRandomString())
                    .setDescription("sharding-jdbc_" + i).setDeleted(false);
            simpleMapper.insert(simpleEntity);
        }
    }

    /**
     * 分库分表后, 根据分表分片键批量搜索
     */
    @Test
    public void selectListByIdsTest() {
        List<Long> idList = Arrays.asList(466344819833176064L, 466344820575567873L);
        List<SimpleEntity> simpleEntities = simpleMapper.selectListByIds(idList);
        log.info("查询结果：{}", simpleEntities);
    }

    /**
     * 分库分表后, 根据分库分片键批量搜索
     */
    @Test
    public void selectListByNamesTest() {
        List<String> names = Collections.singletonList("test_2020_3");
        List<SimpleEntity> simpleEntities = simpleMapper.selectListByNames(names);
        log.info("查询结果：{}", simpleEntities);
    }

}
