package com.liuscoding.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @className: EduApplication
 * @description: edu启动类
 * @author: liusCoding
 * @create: 2020-05-02 00:45
 */

@SpringBootApplication
@ComponentScan(basePackages = {"com.liuscoding"})
public class EduApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
