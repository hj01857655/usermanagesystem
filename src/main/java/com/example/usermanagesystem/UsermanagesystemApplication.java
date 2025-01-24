package com.example.usermanagesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.example.usermanagesystem.mapper")
public class UsermanagesystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsermanagesystemApplication.class, args);
    }

}
