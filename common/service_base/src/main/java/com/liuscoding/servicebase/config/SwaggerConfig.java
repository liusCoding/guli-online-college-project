package com.liuscoding.servicebase.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @className: SwaggerConfig
 * @description: swagger配置
 * @author: liusCoding
 * @create: 2020-05-02 09:08
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(true)
                .apiInfo(apiInfo())
                .select()
                //这里配置要扫描的包，接口在哪个包就配置哪个包
                .apis(RequestHandlerSelectors.basePackage("com.liuscoding"))
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("谷粒学院")
                .description("谷粒学院接口")
                .termsOfServiceUrl("liuscoding")
                .contact(new Contact("liusCoding","liuscoding.cn","liuscoding@163.com"))
                .version("1.0")
                .build();
    }
}
