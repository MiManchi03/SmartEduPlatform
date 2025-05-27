package com.llf.controller;

import com.llf.pojo.*;
import com.llf.service.EmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/emps")
@RestController
public class EmpController {
    //开启日志
    private static Logger LOGGER = LoggerFactory.getLogger(DeptController.class);
    @Autowired
    private EmpService empService;

    /*分页查询*/
    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    /*新增员工信息*/
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        empService.save(emp);
        return Result.success();
    }

    /*根据id删除员工*/
    /*@DeleteMapping
    public Result delete(@RequestParam("ids") ArrayList<Integer> ids) {
        LOGGER.info("删除员工:{}",ids);
        //empService.delete(ids);
        return Result.success();
    }*/
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        LOGGER.info("删除员工:{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    /* @DeleteMapping
     public Result delete(@RequestParam Integer[] ids) {
         LOGGER.info("删除员工:{}", Arrays.toString(ids));
         //empService.delete(ids);
         return Result.success();
     }*/
    /*根据id查询员工信息，查询回显*/
    @GetMapping("/{id}")
    public Result select(@PathVariable Integer id) {
        LOGGER.info("根据员工id查询信息:{}", id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    /*修改员工信息*/
    @PutMapping
    public Result put(@RequestBody Emp emp) {
        empService.update(emp);
        return Result.success();
    }
    /*查询所有员工*/
    @GetMapping("/list")
    public Result allEmpList(){
        List<Emp> allList = empService.allEmpList();
        return Result.success(allList);
    }

}
