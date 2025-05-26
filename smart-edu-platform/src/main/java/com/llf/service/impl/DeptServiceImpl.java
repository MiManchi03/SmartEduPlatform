package com.llf.service.impl;

import com.llf.anno.LogOperation;
import com.llf.mapper.DeptMapper;
import com.llf.pojo.Dept;
import com.llf.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public void delete(Integer id) {
        deptMapper.delete(id);
    }

    @Override
    public void add(Dept dept) {
        //补全基础属性，因为添加部门时，json中没有传递createTime和updateTime的信息，所以需要手动补全为当前系统时间
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.add(dept);
    }

    @LogOperation
    @Override
    public Dept findById(Integer id) {
        return deptMapper.findById(id);
    }

    @Override
    public void update(Dept dept) {
        //补全基础属性，因为修改部门时，json中没有传递updateTime，所以需要手动补全为当前系统时间
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}
