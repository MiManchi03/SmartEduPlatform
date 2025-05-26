package com.llf.mapper;

import com.llf.pojo.EmpLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpLogMapper {
    @Insert("insert into emp_log(operate_time,info) values (#{operateTime},#{info})")
    public  void insert(EmpLog empLog);
}
