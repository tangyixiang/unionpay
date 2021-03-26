package com.sky.unionpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class UnionpayApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnionpayApplication.class, args);
    }

}
