package com.llf.mapper;

import com.github.pagehelper.Page;
import com.llf.pojo.Student;
import com.llf.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.LinkedHashMap;
import java.util.List;

@Mapper
public interface StudentMapper {
    /*该id对应的班级下有多少学生*/
    Integer sumStudent(Integer id);

    /*分页查询学生信息*/
    Page<Student> studentPage(StudentQueryParam studentQueryParam);

    /*根据id批量删除学生*/
    void deleteStudentByIds(List<Integer> ids);

    /*添加学生信息*/
    void insertStudent(Student student);

    /*根据id查询学生信息-查询回显*/
    LinkedHashMap<Student, Object> getStudentById(Integer id);

    /*修改学生信息*/
    void putStudent(Student student);

    /*违纪处理-扣分情况*/
    void updateViolation(Integer id, Integer score);
}
