package com.llf.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.llf.controller.ClazzController;
import com.llf.exception.ClassHasStudentException;
import com.llf.mapper.ClazzMapper;
import com.llf.mapper.EmpMapper;
import com.llf.mapper.StudentMapper;
import com.llf.pojo.Clazz;
import com.llf.pojo.ClazzQueryParam;
import com.llf.pojo.PageResult;
import com.llf.service.ClazzService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClazzServiceImpl implements ClazzService {
    //开启日志
    private static Logger LOGGER = LoggerFactory.getLogger(ClazzServiceImpl.class);
    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<Clazz> getClazzPage(ClazzQueryParam clazzQueryParam) {
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
        Page<Clazz> pageClazz = clazzMapper.clazzList(clazzQueryParam);
        LOGGER.info("查询内容结果是:{}", pageClazz.getResult());
        PageResult<Clazz> clazzPageResult = new PageResult<>(pageClazz.getTotal(), pageClazz.getResult());
        return clazzPageResult;
    }

    /*根据id删除班级信息*/
    @Override
    public void deleteClazzById(Integer id) {
        Integer count = studentMapper.sumStudent(id);
        if (count != null || count != 0) {
            /*1.先判断班级是否有学生，如果没有再删*/
            throw new ClassHasStudentException("该班级下有学生，不能直接删除"); // 抛出自定义异常
        } else {
            /*2.如果count是null或者0，即该班级下没有学生的情况下再删*/
            clazzMapper.deleteClazzById(id);
        }
    }

    @Override
    public void addClazz(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.addClazz(clazz);
    }

    @Override
    public LinkedHashMap<String, Object> selectClazzById(Integer id) {
        return clazzMapper.selectClazzById(id);
    }

    @Override
    public void put(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.put(clazz);
    }

    @Override
    public List<LinkedHashMap<String, Object>> getAllClazzes() {

        return clazzMapper.getAllClazzes();
    }


}
