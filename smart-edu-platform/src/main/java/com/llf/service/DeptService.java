package com.llf.service;

import com.llf.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> findAll();

    void delete(Integer id);

    void add(Dept dept);

    Dept findById(Integer id);

    void update(Dept dept);
}
