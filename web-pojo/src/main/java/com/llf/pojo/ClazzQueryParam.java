package com.llf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClazzQueryParam {
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")//把传递过来多种样式的时间统一为yyyy-MM-dd这种样式（下面同理）
    private LocalDate begin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;
    private Integer page = 1;//要查询哪一页设置默认值（下面同理）
    private Integer pageSize = 5;//这一页有多少条数据

    @Override
    public String toString() {
        return "ClazzQueryParam{" +
                "name='" + name + '\'' +
                ", begin=" + begin +
                ", end=" + end +
                ", page=" + page +
                ", pageSize=" + pageSize +
                '}';
    }
}
