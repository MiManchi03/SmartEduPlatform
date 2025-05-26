package com.llf.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.llf.mapper.StudentMapper;
import com.llf.pojo.PageResult;
import com.llf.pojo.Student;
import com.llf.pojo.StudentQueryParam;
import com.llf.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;


    @Override
    /*学生列表查询-分页查询*/
    public PageResult<Student> studentPage(StudentQueryParam studentQueryParam) {
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());
        Page<Student> studentPage = studentMapper.studentPage(studentQueryParam);
        return new PageResult<>(studentPage.getTotal(), studentPage.getResult());
    }

    /*待测试：根据id批量删除学生信息*/
    @Override
    public void deleteStudentByIds(List<Integer> ids) {
        studentMapper.deleteStudentByIds(ids);
    }

    /*添加学生信息*/
    @Override
    public void insertStudent(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.insertStudent(student);
    }

    /*根据id查询学生信息-查询回显*/
    @Override
    public LinkedHashMap<Student, Object> getStudentById(Integer id) {
        return studentMapper.getStudentById(id);
    }

    /*修改学生信息*/
    @Override
    public void putStudent(Student student) {
        studentMapper.putStudent(student);
    }

    /*违纪处理-扣分情况*/
    @Override
    public void updateViolation(Integer id, Integer score) {
        studentMapper.updateViolation(id, score);
    }


}
