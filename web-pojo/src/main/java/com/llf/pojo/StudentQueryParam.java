package com.llf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentQueryParam {
    private String name;
    private Integer degree;
    private Integer clazzId;
    private Integer page = 1;//设置当前页码默认值（下面同理）
    private Integer pageSize = 10;//设每页记录数的默认值

}
