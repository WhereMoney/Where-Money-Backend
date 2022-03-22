package com.javacourse.wheremoney;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.javacourse.wheremoney.mapper")
public class WheremoneyApplication {

    public static void main(String[] args) {
        SpringApplication.run(WheremoneyApplication.class, args);
    }

}
