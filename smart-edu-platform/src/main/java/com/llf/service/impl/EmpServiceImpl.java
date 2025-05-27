package com.llf.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.llf.exception.LoginException;
import com.llf.mapper.ClazzMapper;
import com.llf.mapper.EmpExprMapper;
import com.llf.mapper.EmpMapper;
import com.llf.pojo.*;
import com.llf.service.EmpLogService;
import com.llf.service.EmpService;
import com.llf.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;
    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        /*设置分页参数：查询的页，总页数,设置分页参数，开启分页查询.如果不调用该方法，直接执行查询，就不会有分页效果*/
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
      /*  //接收mapper层获得的数据
        List<Emp> listEmp = empMapper.list(empQueryParam);
        //List数据是Page的父类，所以可以把上面的listEmp直接强制转换为Page类型，
        直接把listEmp中储存的数据传给Page，让它自动分析listEmp里面数据以此获得需要的数据*//*
        Page<Emp> p = (Page<Emp>) listEmp;
        //p.getTotal()和p.getResult()都是Page在获得listEmp里面的数据后分析获取的数据*//*
         */
        Page<Emp> p = empMapper.list(empQueryParam);
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Transactional(rollbackFor = {Exception.class})//添加事务注解
    @Override
    /*新增员工信息*/
    public void save(Emp emp) {
        try {
            //保存员工基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);
            //保存员工工作信息
            List<EmpExpr> exprList = emp.getExprList();
            //判断exprList是否为空的，如果不是空的就调用EmpExprMapper接口
            if (!CollectionUtils.isEmpty(exprList)) {
                /*因为前端传来的数据中并不会有员工的主键id，主键id只有
                再新加入员工信息后才会自动生成，所以要在EmpExprMapper
                层使用@Options注解将新增员工的主键id获取出来再赋值给
                emp类中的id，又因为EmpExpr类中的empId并没有值，而这
                个值正是这名新增员工的主键，所以要再从emp中获取id并赋值
                给EmpExpr类中的empId，为每一个工作经历指定对应的员工。
                用forEach遍历exprList这个集合，括号里的意思：拿到每个
                遍历得到的empExpr对象，并为这个对象的empExpr赋值*/
                exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            //记录操作日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "新增员工:" + emp);
            empLogService.insertLog(empLog);
        }

    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        //1.批量删除员工信息
        empMapper.deleteByIds(ids);
        //2.批量删除员工工作经历信息
        empExprMapper.deleteByEmpIds(ids);

    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getInfo(id);
    }

    @Override
    public void update(Emp emp) {
        //1.根据ID修改员工的基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        //2.根据ID修改员工的工作经历信息
        // 2.1先根据员工ID删除原有的工作经历
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        //2.2 再添加这个员工新的工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
        }
    }

    /*查询所有员工即教师信息*/
    @Override
    public List<Emp> allEmpList() {
        return empMapper.allEmpList();
    }

    /*用户登录-比较*/
    @Override
    public LoginInfo getLogin(Emp emp) {
        /*根据形参的username从数据库中匹配查询到的真正emp*/
        Emp empLogin = empMapper.getLogin(emp.getUsername());//返回的实际上的emp
        LoginInfo loginInfo = new LoginInfo();
        if (empLogin == null) {
            throw new LoginException("用户名错误,没有该用户");
        } else if (!emp.getPassword().equals(empLogin.getPassword())) {
            throw new LoginException("密码错误！");
        } else {
//            /*将正确的empLogin中的数据组装到loginInfo中*/
            //定义一个map集合，用来存储jwt令牌中携带的数据
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("id", empLogin.getId());//必须添加用户id,好知道到底是哪个用户登录
            dataMap.put("username", empLogin.getUsername());//该用户的用户名
            //生成携带该登录用户信息的jwt令牌。即生成token
            String token = JwtUtils.generateToken(dataMap);
            //将token封装到loginInfo中
            loginInfo.setToken(token);
            loginInfo.setId(empLogin.getId());
            loginInfo.setUsername(empLogin.getUsername());
            loginInfo.setName(empLogin.getName());
        }
        return loginInfo;
    }
}
