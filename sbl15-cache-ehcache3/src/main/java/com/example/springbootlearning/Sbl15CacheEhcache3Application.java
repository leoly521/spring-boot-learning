package com.example.springbootlearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Sbl15CacheEhcache3Application {

    public static void main(String[] args) {
        SpringApplication.run(Sbl15CacheEhcache3Application.class, args);
    }

}

