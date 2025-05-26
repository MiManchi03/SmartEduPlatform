package com.llf.controller;

import com.llf.pojo.Result;
import com.llf.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class ReportController {
    //开启日志
    private static Logger LOGGER = LoggerFactory.getLogger(DeptController.class);
    @Autowired
    private ReportService reportService;
    /*职位统计*/
    @GetMapping("/empJobData")
    public Result getEmpJobDate(){
        LOGGER.info("各个职位的人数");
        return Result.success(reportService.countEmpData());
    }
    /*性别统计*/
    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        return Result.success(reportService.empGenderData());
    }
    /*学院学历统计*/
    @GetMapping("/studentDegreeData")
    public Result studentDegreeData(){
        return Result.success(reportService.studentDegreeData());
    }
    /*班级人数统计*/
    @GetMapping("/studentCountData")
    public Result studentCountData(){
            return Result.success(reportService.studentCountData());
    }
}
