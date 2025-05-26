package com.llf.aop;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@Aspect
public class MyAspect1 {
    @Autowired
    private Gson gson;

    @Pointcut("execution(* com.llf.service.impl.*.*(..))")
    public void pt() {
    }

    @Before("execution(* com.llf.service.impl.*.*(..))")
    public void before() {
        log.info("前置通知");
    }
/*    @Before("pt()")
    public void before() {
        log.info("前置通知");
    }*/

    //@Around("execution(* com.llf.service.impl.*.*(..))")
/*@Around("execution(* com.llf.service.impl.*.dele*(..)) ||" +
        "execution(* com.llf.service.impl.*.list*(..)))")*/
    @Around("@annotation(com.llf.anno.LogOperation)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        log.info("around.....before");
        Object obj = pjp.proceed();
        log.info("around.....after");/*调用 pjp.proceed()
        时若抛出异常，后续代码（log.info("around.....after")）不会执行。*/
        return obj;
    }

    @After(("execution(* com.llf.service.impl.*.*(..))"))
    //无论是否抛出异常，都会执行
    public void after() {
        log.info("后置通知");
    }

    @AfterReturning(("execution(* com.llf.service.impl.*.*(..))"))
    //返回值后执行，目标方法正常返回时执行，目标方法抛出异常，则不会执行
    public void afterReturning() {
        log.info("afterReturning.....");
    }

    @AfterThrowing(("execution(* com.llf.service.impl.*.*(..))"))
    //异常后执行，目标方法抛出异常时执行
    public void afterThrowing() {
        log.info("afterThrowing.....");
    }
}

