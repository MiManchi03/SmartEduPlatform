package com.llf.service;

import com.llf.pojo.JobOption;
import com.llf.pojo.StudentOption;

import java.util.List;
import java.util.Map;

public interface ReportService {

    JobOption countEmpData();

    List<Map<String, Object>> empGenderData();

    /*学院学历统计*/
    List<Map<String, Integer>> studentDegreeData();

    /*班级人数统计*/
    StudentOption studentCountData();
}
