package com.llf.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.llf.mapper.OperateLogMapper;
import com.llf.pojo.Clazz;
import com.llf.pojo.OperateLog;
import com.llf.pojo.PageResult;
import com.llf.service.OperateLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OperateLogServiceImpl implements OperateLogService {
    @Autowired
    private OperateLogMapper operateLogMapper;

    /*分页查询日志*/
    @Override
    public PageResult<OperateLog> page(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        Page<OperateLog> operateLogPage = operateLogMapper.page(page, pageSize);
        log.info("查询内容结果是:{}", operateLogPage.getResult());
        return new PageResult<>(operateLogPage.getTotal(), operateLogPage.getResult());
    }
}
