package com.llf.service.impl;

import com.llf.mapper.EmpMapper;
import com.llf.mapper.ReportMapper;
import com.llf.pojo.JobOption;
import com.llf.pojo.StudentOption;
import com.llf.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportMapper reportMapper;

    /*员工职位人数统计*/
    @Override
    public JobOption countEmpData() {
        List<Map<String, Object>> list = reportMapper.countEmpData();
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("job")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();
        return new JobOption(jobList, dataList);
    }

    /*员工性别统计*/
    @Override
    public List<Map<String, Object>> empGenderData() {
        return reportMapper.getEmpGenderData();
    }

    /*学院学历统计*/
    @Override
    public List<Map<String, Integer>> studentDegreeData() {
        return reportMapper.studentDegreeData();
    }

    /*班级人数统计*/
    @Override
    public StudentOption studentCountData() {
        List<Map<String, Object>> list = reportMapper.studentCountData();
        List<Object> clazzList = list.stream().map(map -> map.get("clazzList")).toList();
        List<Object> dataList = list.stream().map(map -> map.get("dataList")).toList();
        return new StudentOption(clazzList, dataList);
    }


}
