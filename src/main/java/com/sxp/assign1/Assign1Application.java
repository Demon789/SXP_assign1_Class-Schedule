package com.sxp.assign1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

@MapperScan("com.sxp.assign1.mapper")
public class Assign1Application {

    public static void main(String[] args) {
        SpringApplication.run(Assign1Application.class, args);
    }

}
