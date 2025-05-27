package com.llf;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class LogbackTest {
    //测试日志
    private static final Logger logger = LoggerFactory.getLogger(LogbackTest.class);
    @Test
    public void testLog() {
        //System.out.println(LocalDateTime.now() + ":开始计算");
        logger.debug("开始计算");
        int[] arr = {1, 2, 3, 4, 5};
      int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        //System.out.println("计算结果为:" + sum);
       logger.info("计算结果为：" + sum);
        //System.out.println(LocalDateTime.now() + ":计算结束");
        logger.debug("计算结束");
    }
}
