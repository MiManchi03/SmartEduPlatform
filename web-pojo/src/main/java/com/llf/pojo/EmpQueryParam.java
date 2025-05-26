package com.llf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpQueryParam {
    private String name;
    private Integer gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")//把传递过来多种样式的时间统一为yyyy-MM-dd这种样式（下面同理）
    private LocalDate begin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;
    private Integer page=1;//设置默认值（下面同理）
    private Integer pageSize=10;

    @Override
    public String toString() {
        return "EepQueryParam{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", begin=" + begin +
                ", end=" + end +
                ", page=" + page +
                ", pageSize=" + pageSize +
                '}';
    }
}
