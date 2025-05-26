package com.llf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Integer gender;
    private String phone;
    private Integer job;
    private Integer salary;
    private String image;
    private LocalDate entryDate;
    private Integer deptId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    //封装外连接额外新增的部门名称信息
    private String deptName;
    //封装员工工作经历类的集合
    private List<EmpExpr> exprList;

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                ", job=" + job +
                ", salary=" + salary +
                ", image='" + image + '\'' +
                ", entryDate=" + entryDate +
                ", deptId=" + deptId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deptName='" + deptName + '\'' +
                ", exprList=" + exprList +
                '}';
    }
}
