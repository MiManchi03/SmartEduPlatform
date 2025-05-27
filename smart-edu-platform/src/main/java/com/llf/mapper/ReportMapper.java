package com.llf.mapper;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReportMapper {
    /*员工职位人数统计*/
    @MapKey("jop")
    List<Map<String, Object>> countEmpData();
    /*员工性别统计*/
    @MapKey("name")
    List<Map<String, Object>> getEmpGenderData();
    /*学院学历统计*/
    @MapKey("degree")
    List<Map<String, Integer>> studentDegreeData();
    /*班级人数统计*/
    @MapKey("clazzList")
    List<Map<String, Object>> studentCountData();
}
