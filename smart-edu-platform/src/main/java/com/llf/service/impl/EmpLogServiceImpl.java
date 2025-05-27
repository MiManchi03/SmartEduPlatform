package com.llf.service.impl;

import com.llf.mapper.EmpLogMapper;
import com.llf.pojo.EmpLog;
import com.llf.service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpLogServiceImpl implements EmpLogService {
    @Autowired
    private EmpLogMapper empLogMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)/*//在调用这个方法的时候需要开启一个新事务
REQUIRES_NEW:需要在一个新事务中运行，
无论加没加入到别的事务里，它也是新建一个事务自己独立运行，如果调用它的那个事务有错误回滚了，它也不会回滚*/
    @Override
    public void insertLog(EmpLog empLog) {
        empLogMapper.insert(empLog);
    }
}
