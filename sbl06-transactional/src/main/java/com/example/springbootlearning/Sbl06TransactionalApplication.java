package com.example.springbootlearning;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.example.springbootlearning.dao"})
public class Sbl06TransactionalApplication {

    public static void main(String[] args) {
        SpringApplication.run(Sbl06TransactionalApplication.class, args);
    }

}

