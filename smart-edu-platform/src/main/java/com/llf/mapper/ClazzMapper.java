package com.llf.mapper;

import com.github.pagehelper.Page;
import com.llf.pojo.Clazz;
import com.llf.pojo.ClazzQueryParam;
import com.llf.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.LinkedHashMap;
import java.util.List;
@Mapper
public interface ClazzMapper {
    Page<Clazz> clazzList(ClazzQueryParam clazzQueryParam);

    void deleteClazzById(Integer id);

    void addClazz(Clazz clazz);

    LinkedHashMap<String, Object> selectClazzById(Integer id);

    void put(Clazz clazz);

    List<LinkedHashMap<String, Object>> getAllClazzes();
}
