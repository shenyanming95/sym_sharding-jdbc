package com.sym.mapper;

import com.sym.domain.Customer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author shenyanming
 * Created on 2020/5/14 11:47
 */
@Mapper
public interface CustomerMapper {


    @Insert("insert into t_customer(username,real_name,is_deleted,create_time) values" +
            "(#{customer.username},#{customer.realName},#{customer.isDeleted},#{customer.createTime})")
    @Options(useGeneratedKeys = true)
    long insert(@Param("customer") Customer customer);


    @Select("select * from t_customer where id=#{id}")
    Customer selectOneById(long id);
}
