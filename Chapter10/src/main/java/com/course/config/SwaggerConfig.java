package com.course.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathMapping("/")//配置访问路径
                .select()
                .paths(PathSelectors.regex("/.*"))//匹配路径
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("这是一个开头")
                .contact(new Contact("作者名字","","email"))
                .version("1.0.0")
                .build();
    }
}
