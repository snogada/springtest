package com.kakaopay.invest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.kakaopay.invest.mapper")
public class InvestApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvestApplication.class, args);
    }

}
