package com.llf.controller;

import com.llf.pojo.PageResult;
import com.llf.pojo.Result;
import com.llf.pojo.Student;
import com.llf.pojo.StudentQueryParam;
import com.llf.service.ClazzService;
import com.llf.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    //开启日志
    private static Logger LOGGER = LoggerFactory.getLogger(StudentController.class); // 修改: DeptController.class -> ClazzController.class
    @Autowired
    private StudentService studentService;

    /*学生列表查询-分页查询*/
    @GetMapping
    public Result studentPage(StudentQueryParam studentQueryParam) {
        PageResult<Student> studentPageResult = studentService.studentPage(studentQueryParam);
        return Result.success(studentPageResult);
    }

    /*根据id批量删除学生*/
    @DeleteMapping("/{ids}")
    public Result deleteStudentByIds(@PathVariable List<Integer> ids) {
        studentService.deleteStudentByIds(ids);
        return Result.success();
    }

    /*根据id查询学生信息-查询回显*/
    @GetMapping("/{id}")
    public Result getStudentById(@PathVariable Integer id) {
        return Result.success(studentService.getStudentById(id));
    }

    /*添加学生信息*/
    @PostMapping
    public Result insertStudent(@RequestBody Student student) {
        studentService.insertStudent(student);
        return Result.success();
    }

    /*修改学生信息*/
    @PutMapping
    public Result putStudent(@RequestBody Student student) {
        studentService.putStudent(student);
        return Result.success();
    }

    /*违纪处理-扣分情况*/
    @PutMapping("/violation/{id}/{score}")
    public Result updateViolation(@PathVariable Integer id, @PathVariable Integer score) {
        LOGGER.info("给id为{}的同学扣{}分",id,score);
        studentService.updateViolation(id,score);
        return Result.success();
    }
}
