package com.liuscoding.edu;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @className: EduApplication
 * @description: edu启动类
 * @author: liusCoding
 * @create: 2020-05-02 00:45
 */

@SpringBootApplication
@ComponentScan(basePackages = {"com.liuscoding"})
@EnableDiscoveryClient//nacos注册
@EnableFeignClients
public class EduApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
