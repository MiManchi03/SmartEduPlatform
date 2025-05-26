package com.llf.exception;

import com.llf.controller.DeptController;
import com.llf.pojo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/*全局异常处理器*/
@RestControllerAdvice
public class GlobalExceptionHandler {
    //开启日志
    private static Logger LOGGER = LoggerFactory.getLogger(DeptController.class);

    @ExceptionHandler
    /*Exception ：所以异常都捕获*/
    public Result handleException(Exception e) {
        LOGGER.error("程序出错了", e);
        return Result.error("出错了~请联系管理员");
    }

    /*精准捕获异常-*/
    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        LOGGER.error("出错了", e);
        String message = e.getMessage();//获取这个报错信息
        int index = message.indexOf("Duplicate entry");//获取到报错信息中Duplicate entry第一次出现的位置
        String errMSG = message.substring(index);//将报错信息从Duplicate entry这个字符串开始往后一直截取到末尾
        String[] arr = errMSG.split(" ");//将截取到的字符串按照空格分割成数组
        return Result.error(arr[3] + "已存在");//获取到到准确的爆粗信息，如手机号，这里它是在第三个索引处

    }
    //处理自定义的“班级有学生”异常
    @ExceptionHandler(ClassHasStudentException.class)
    public Result handleClassHasStudentException(ClassHasStudentException e) {
        LOGGER.error("删除班级时检测到学生关联", e); // 记录日志
        return Result.error("对不起，该班级下有学生，不能直接删除"); // 返回业务友好提示
    }
    //处理自定义的“密码错误”异常
    @ExceptionHandler(LoginException.class)
    public Result handleLoginException(LoginException e){
        LOGGER.error("错误原因:{}", e.getMessage()); // 记录日志
        return Result.error(e.getMessage());
    }
}
