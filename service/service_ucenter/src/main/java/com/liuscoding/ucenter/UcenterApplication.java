package com.liuscoding.ucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @className: UcenterApplication
 * @description:
 * @author: liusCoding
 * @create: 2020-05-16 13:44
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.liuscoding")
@MapperScan("com.liuscoding.ucenter.mapper")
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class,args);
    }
}
