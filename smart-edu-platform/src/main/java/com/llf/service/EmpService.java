package com.llf.service;

import com.llf.pojo.*;

import java.util.ArrayList;
import java.util.List;

public interface EmpService {

    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void save(Emp emp);

    void delete(List<Integer> ids);

    Emp getInfo(Integer id);

    void update(Emp emp);

    List<Emp> allEmpList();

    /*用户登录*/
    LoginInfo getLogin(Emp emp);
}
