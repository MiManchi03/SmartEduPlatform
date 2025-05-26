package com.llf.exception;

public class ClassHasStudentException extends RuntimeException {
    //用于接收异常描述信息（如 “该班级下有学生，不能直接删除”）。
    public ClassHasStudentException(String message) {
        /*super 调用父类（RuntimeException）的构造方法。
        RuntimeException的父类是Exception，其构造方法会将message
        保存为异常的错误信息，可通过e.getMessage()获取。*/
        super(message);
    }
}
