package com.llf.mapper;

import com.github.pagehelper.Page;
import com.llf.pojo.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*操作员工信息的mapper*/
@Mapper
public interface EmpMapper {
    //@Select("select e.*, d.
    //name dept_name from emp e left join dept d on e.dept_id = d.id order by e.update_time desc")
    //List<Emp> list(EmpQueryParam empQueryParam);
    Page<Emp> list(EmpQueryParam empQueryParam);

    /*MyBatis的主键返回功能：将这个新加入的员工的主键给返回赖来
      keyProperty：将返回的主键赋值给emp对象中的id这个值*/
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "value (#{username}, #{name},#{gender},#{phone},#{job}, #{salary}, #{image}, #{entryDate}, #{deptId},  #{createTime}, #{updateTime})")
    void insert(Emp emp);

    void deleteByIds(List<Integer> ids);

    Emp getInfo(Integer id);

    void updateById(Emp emp);


    List<Emp> allEmpList();

    /*获取用户登录信息*/
    @Select("select id, username, password, name from emp where username = #{username}")
    Emp getLogin(String username);

    /*根据id查询对于员工*/
    @Select("select name from emp where id = #{id}")
    String getName(Integer id);
}