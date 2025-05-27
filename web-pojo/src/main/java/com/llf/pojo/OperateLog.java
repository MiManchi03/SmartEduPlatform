package com.llf.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OperateLog {
    private Integer id;//ID
    private String operateEmpName;//操作人名字
    private LocalDateTime operateTime;//作时间
    private String className;//作类名
    private String methodName;//操作方法名
    private String methodParams;//操作方法参数
    private String returnValue;//操作方法返回值
    private Long costTime;//操作耗时
}
