package com.sym.mapper;

import com.sym.entity.SimpleEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 数据层Dao
 *
 * @author shenyanming
 * @date 2020/5/10 20:01.
 */
@Mapper
public interface SimpleMapper{

    /**
     * 分库分表后, 执行插入
     * @param simpleEntity 实体信息
     */
    @Insert("insert into t_simple(name,description,is_deleted) values " +
            "(#{entity.name},#{entity.description},#{entity.isDeleted})")
    void insert(@Param("entity") SimpleEntity simpleEntity);

    /**
     * 执行批量查询, 通过分表分片键[id]作批量查询
     * @param idList 分表分片键id
     * @return 集合
     */
    @Select("<script>" +
            "   select * from t_simple where id in" +
            "   <foreach collection='list' item='id' open='(' close=')' separator=','>" +
            "       #{id}" +
            "   </foreach>" +
            "</script>")
    List<SimpleEntity> selectListByIds(List<Long> idList);

    /**
     * 执行批量查询, 通过分库分片键[name]作批量查询
     * @param nameList 分库分片键name
     * @return 集合
     */
    @Select("<script>" +
            "   select * from t_simple where name in" +
            "   <foreach collection='list' item='name' open='(' close=')' separator=','>" +
            "       #{name}" +
            "   </foreach>" +
            "</script>")
    List<SimpleEntity> selectListByNames(List<String> nameList);

}
