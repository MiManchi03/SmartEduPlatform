package com.llf.config;

import com.llf.utils.AliyunOSSOperator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {
    @Bean
    //形参前其实是省略了个@Autowired，即：
    //public Aliyunossoperator aliyunossoperator(@Autowired AliyunossProperties aliyunosSProperties) {
    public AliyunOSSOperator aliyunossoperator() {
        return new AliyunOSSOperator();
    }
}

