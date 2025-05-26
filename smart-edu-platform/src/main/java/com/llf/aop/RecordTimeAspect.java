package com.llf.aop;

import com.llf.mapper.EmpMapper;
import com.llf.mapper.OperateLogMapper;
import com.llf.pojo.OperateLog;
import com.llf.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect//标识当前是一个AOP类
@Component
@Slf4j
public class RecordTimeAspect {
    @Autowired
    private OperateLogMapper operateLogMapper;
    @Autowired
    private EmpMapper empMapper;

    @Around("execution(* com.llf.controller.*.*(..)) && !execution(* com.llf.controller.OperateLogController.*(..))")
    public Object recordTime(ProceedingJoinPoint pjp) throws Throwable {
        //1.记录方法的开始时间
        long l = System.currentTimeMillis();
        //2.执行目标方法
        Object result = pjp.proceed();
        //3.记录方法的结束时间
        long l1 = System.currentTimeMillis();
        //4.计算方法执行时间
        long costTime = l1 - l;
        log.info("方法{}执行耗时:{}ms", pjp.getSignature(), costTime);
        //构建日志实体
        OperateLog oLog = new OperateLog();
        oLog.setOperateEmpName(getCurrentUserName(empMapper));//这里需要你根据实际情况获取当前用户名字
        oLog.setOperateTime(LocalDateTime.now());
        oLog.setClassName(pjp.getTarget().getClass().getName());
        oLog.setMethodName(pjp.getSignature().getName());
        oLog.setMethodParams(Arrays.toString(pjp.getArgs()));
        oLog.setReturnValue(result != null ? result.toString() : "void");
        oLog.setCostTime(costTime);
        //保存日志
        log.info("记录操作日志：{}", oLog);
        operateLogMapper.insert(oLog);
        return result;
    }

    //获取当前用户ID
    private String getCurrentUserName(EmpMapper empMapper) {
        return empMapper.getName(CurrentHolder.getCurrentId());
    }
}
