package com.liuscoding.edu.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @className: EduConfig
 * @description: 配置类
 * @author: liusCoding
 * @create: 2020-05-02 00:55
 */

@Configuration
@MapperScan("com.liuscoding.edu.mapper")
public class EduConfig {
}
