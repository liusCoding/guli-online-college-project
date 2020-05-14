package com.liuscoding.oss;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @className: OssApplication
 * @description:
 * @author: liusCoding
 * @create: 2020-05-09 14:45
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.liuscoding"})
public class OssApplication {

    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class,args);
    }
}
