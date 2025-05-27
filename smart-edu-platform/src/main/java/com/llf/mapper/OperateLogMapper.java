package com.llf.mapper;

import com.github.pagehelper.Page;
import com.llf.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OperateLogMapper {
    /*添加日志*/
    @Insert("insert into operate_log(operate_emp_name, operate_time, class_name, method_name, method_params, return_value, cost_time)\n" +
            "values (#{operateEmpName}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime});")
    void insert(OperateLog log);

    /*分页查询日志*/
    @Select("select * from operate_log")
    Page<OperateLog> page(Integer page, Integer pageSize);
}
