package com.llf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
/*分页结果查询*/
public class PageResult<T> {
    private Long total;
    private List<T> rows;
}
