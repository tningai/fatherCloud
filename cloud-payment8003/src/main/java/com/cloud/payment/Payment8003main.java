package com.cloud.payment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan
@EnableEurekaClient
@EnableDiscoveryClient
public class Payment8003main {
    public static void main(String[] args) {
        SpringApplication.run(Payment8003main.class, args);
    }
}
