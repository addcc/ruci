package com.ruci;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ruci.dao")
public class RuciApplication {

    public static void main(String[] args) {
        SpringApplication.run(RuciApplication.class, args);
    }

}
