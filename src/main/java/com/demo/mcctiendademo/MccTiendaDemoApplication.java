package com.demo.mcctiendademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MccTiendaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MccTiendaDemoApplication.class, args);
    }

}
