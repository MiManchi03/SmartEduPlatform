package com.llf.controller;

import com.llf.pojo.OperateLog;
import com.llf.pojo.PageResult;
import com.llf.pojo.Result;
import com.llf.service.OperateLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
@Slf4j
public class OperateLogController {
    @Autowired
    private OperateLogService operateLogService;
    /*分页查询日志*/
    @GetMapping("/page")
    public Result page(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        return Result.success(operateLogService.page(page, pageSize));
    }
}
