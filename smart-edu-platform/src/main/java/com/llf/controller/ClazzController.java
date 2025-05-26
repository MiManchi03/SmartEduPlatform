package com.llf.controller;

import com.llf.pojo.*;
import com.llf.service.ClazzService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clazzs")
public class ClazzController {
    //开启日志
    private static Logger LOGGER = LoggerFactory.getLogger(ClazzController.class); // 修改: DeptController.class -> ClazzController.class
    @Autowired
    private ClazzService clazzService;

    /*班级列表查询-分页查询*/
    @GetMapping
    public Result getClazz(ClazzQueryParam clazzQueryParam) {
        LOGGER.info("查询的条件为:{}", clazzQueryParam);
        PageResult<Clazz> clazzPage = clazzService.getClazzPage(clazzQueryParam);
        return Result.success(clazzPage);
    }

    /*根据id删除班级*/
    @DeleteMapping("/{id}")
    public Result deleteClazzById(@PathVariable Integer id) {
        LOGGER.info("需要删除的id为:{}", id);
        clazzService.deleteClazzById(id);
        return Result.success();
    }

    /*添加班级信息*/
    @PostMapping
    public Result addClazz(@RequestBody Clazz clazz) {
        clazzService.addClazz(clazz);
        return Result.success();
    }

    /*根据id查询信息,查询回显*/
    @GetMapping("/{id}")
    public Result selectClazzById(@PathVariable Integer id) {
        LOGGER.info("需要查询的班级id为:{}", id);
        /*如果直接返回clazz对象，就会出现masterName，
        status这两个字段为空的情况，基于List<Map<String, Object>>启发，
        我选择使用Map<String, Object>。
        因为这是根据一个id查询的，结果只会有一个，使用不需要list,
        又因为需要保证存储顺序，让返回的结果跟前端一样，所以需要使用LinkedHashMap，
        这样可以保证按顺序储存，如果使用别的Map即使SQL语句是按照表的列名顺序查询的，
        但是因为其他Map性质的原因无法做到按顺序存储，所以需要用LinkedHashMap
        形式以去除为nul、前端不需要的两个字段*/
        //Clazz clazz = new Clazz();
        LinkedHashMap<String, Object> result = clazzService.selectClazzById(id);
        return Result.success(result);
        //return Result.success(clazz);
    }

    /*修改班级*/
    @PutMapping
    public Result put(@RequestBody Clazz clazz) {
        clazzService.put(clazz);
        return Result.success();
    }

    /*查询所有班级信息*/
    @GetMapping("/list")
    public Result list() {
        return Result.success(clazzService.getAllClazzes());
    }
}