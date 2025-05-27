package com.llf.controller;

import com.llf.pojo.Dept;
import com.llf.pojo.Result;
import com.llf.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/depts")
@RestController
public class DeptController {
    //开启日志
    private static Logger LOGGER = LoggerFactory.getLogger(DeptController.class);
    @Autowired
    private DeptService deptService;

    //@RequestMapping(value = "/depts",method = RequestMethod.GET)//method: 用于知道请求方式
    @GetMapping
    /*
     * @XXX(请求方式)Mapping：指定请求方式
     */
    public Result list() {
        //System.out.println("查询全部的部门数据");
        LOGGER.info("查询全部的部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);

    }

    //删除指定id的部门
   /* @DeleteMapping("/depts")
    public Result delete(@RequestParam(value = "id",required = false) Integer id){//@RequestParam: 从请求参数中获取数据
        System.out.println("删除id为"+id+"的部门");
        return Result.success();
    }*/
    @DeleteMapping
    public Result delete(Integer id) {//当前端请求的参数名与服务器端的方法形参名一致时@RequestParam可省略
        deptService.delete(id);
        //System.out.println("删除id为" + id + "的部门");
        LOGGER.info("删除id为 {} 的部门",id);//{}为后面id的占位符，有几个数据就写几个占位符，这里只有id一个所有只写一个占位符
        return Result.success();
    }

    //添加部门(只添加部门名称即可)
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        deptService.add(dept);
        //System.out.println("添加部门" + dept);
        LOGGER.info("添加部门{}" ,dept);
        return Result.success();
    }

    /*根据id查询部门*/
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {//@PathVariable: 从路径中获取数据
        Dept dept = deptService.findById(id);
        LOGGER.info("根据id查询部门 {}" ,id);
        return Result.success(dept);
    }
    //根据id修改部门信息
    @PutMapping
    public Result update(@RequestBody Dept dept){
        deptService.update(dept);
        //System.out.println("修改部门"+dept);
        LOGGER.info("修改部门{}",dept);
        return Result.success();
    }
}