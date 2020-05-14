package com.sym;

import com.sym.domain.Customer;
import com.sym.mapper.CustomerMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * @author shenyanming
 * @date 2020/5/13 22:48.
 */
@SpringBootTest(classes = ShardingJdbcApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class MainTest {

    @Autowired
    private CustomerMapper customerMapper;

    /**
     * 读写分离后, 数据都会往主库插入
     */
    @Test
    public void insertTest(){
        Customer customer = new Customer();
        customer.setUsername("zhangsan").setRealName("张三").setDeleted(false)
                .setCreateTime(LocalDateTime.now());
        long l = customerMapper.insert(customer);
        log.info("插入结果：{}", l);
    }

    /**
     * 读写分离后, 数据的查询都会走从库的数据源, 若有多个从库, 可以指定负载均衡策略
     */
    @Test
    public void selectTest(){
        int i = 0;
        while(i < 2){
            Customer customer = customerMapper.selectOneById(1);
            log.info("查询结果: {}", customer);
            i++;
        }
    }
}
