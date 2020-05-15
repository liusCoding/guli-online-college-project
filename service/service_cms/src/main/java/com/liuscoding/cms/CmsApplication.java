package com.liuscoding.cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @className: CmsApplication
 * @description:
 * @author: liusCoding
 * @create: 2020-05-15 14:02
 */

@SpringBootApplication
@ComponentScan("com.liuscoding")
@MapperScan("com.liuscoding.cms.mapper")
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class,args);
    }
}
