package com.llf.service;

import com.llf.pojo.Clazz;
import com.llf.pojo.OperateLog;
import com.llf.pojo.PageResult;
import com.llf.pojo.Result;

public interface OperateLogService {
    PageResult<OperateLog> page(Integer page, Integer pageSize);
}
