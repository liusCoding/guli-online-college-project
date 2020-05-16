package com.liuscoding.msm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @className: MsmApplication
 * @description:
 * @author: liusCoding
 * @create: 2020-05-16 11:00
 */

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan("com.liuscoding")
public class MsmApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsmApplication.class,args);
    }
}
