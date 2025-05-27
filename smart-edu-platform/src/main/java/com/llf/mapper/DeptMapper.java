package com.llf.mapper;

import com.llf.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    /*查询所有的部门数据*/
    @Select("select id, name, create_time, update_time from dept order by update_time desc;")
    List<Dept> findAll();

    /*根据id删除部门*/
    @Delete("delete from dept where id=#{id}")
    void delete(Integer id);

    /*添加部门*/
    @Insert("insert into dept(name,create_time,update_time) values(#{name},#{createTime},#{updateTime})")
    void add(Dept dept);

    /*根据id查询部门*/
    @Select("select id, name, create_time, update_time from dept where id=#{id}")
    Dept findById(Integer id);

    /*根据id修改部门*/
    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    void update(Dept dept);

}
