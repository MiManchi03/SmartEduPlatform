package com.llf.service;

import com.llf.pojo.PageResult;
import com.llf.pojo.Student;
import com.llf.pojo.StudentQueryParam;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface StudentService {
    /*分页查询学生信息*/
    PageResult<Student> studentPage(StudentQueryParam studentQueryParam);

    /*根据id批量删除信息*/
    void deleteStudentByIds(List<Integer> ids);

    /*添加学生信息*/
    void insertStudent(Student student);

    /*根据id查询学生-查询回显*/
    LinkedHashMap<Student, Object> getStudentById(Integer id);

    /*修改学生信息*/
    void putStudent(Student student);

    /*违纪处理-扣分情况*/
    void updateViolation(Integer id, Integer score);


}
