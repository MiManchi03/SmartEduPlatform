package com.llf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@ServletComponentScan//开启spring boot对servlet组件的支持
@SpringBootApplication
public class TliasWebManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(TliasWebManagementApplication.class, args);
    }

}

