package com.liuscoding.canal;

import com.liuscoding.canal.client.CanalClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * @className: CanalApplication
 * @description:
 * @author: liusCoding
 * @create: 2020-05-19 22:49
 */
@SpringBootApplication
public class CanalApplication implements CommandLineRunner {
    @Resource
    private  CanalClient canalClient;


    public static void main(String[] args) {
        SpringApplication.run(CanalApplication.class,args);
    }


    @Override
    public void run(String... args) throws Exception {
        //项目启动，执行canal客户端监听
        canalClient.run();
    }
}
