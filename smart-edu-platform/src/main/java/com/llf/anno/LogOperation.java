package com.llf.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//@Target：修饰注解的注解，表示当前为一个注解
//ElementType.METHOD：表示注解可以加在方法上
@Target(ElementType.METHOD)
//@Retention：指定该注解在何时生效
//RetentionPolicy.RUNTIME：表示运行时生效
@Retention(RetentionPolicy.RUNTIME)
public @interface LogOperation {
}
