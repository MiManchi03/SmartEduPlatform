package com.llf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpLog {
    private Integer id;//id
    private LocalDateTime operateTime;//操作时间
    private String info;//详细时间
    @Override
    public String toString() {
        return "EmpLog{" +
                "id=" + id +
                ", operateTime=" + operateTime +
                ", info='" + info + '\'' +
                '}';
    }
}
