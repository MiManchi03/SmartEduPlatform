package com.llf.service;

import com.llf.pojo.Clazz;
import com.llf.pojo.ClazzQueryParam;
import com.llf.pojo.PageResult;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface ClazzService {
    PageResult<Clazz> getClazzPage(ClazzQueryParam clazzQueryParam);

    void deleteClazzById(Integer id);

    void addClazz(Clazz clazz);


    LinkedHashMap<String, Object> selectClazzById(Integer id);

    void put(Clazz clazz);


    List<LinkedHashMap<String, Object>> getAllClazzes();
}
