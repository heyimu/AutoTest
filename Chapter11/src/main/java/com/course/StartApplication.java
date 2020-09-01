package com.course;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PreDestroy;

@EnableScheduling
@SpringBootApplication
public class StartApplication {
    private static ConfigurableApplicationContext context;

    public static void main(String[] args){
        StartApplication.context = SpringApplication.run(StartApplication.class, args);
    }

    @PreDestroy
    public void close(){
        StartApplication.context.close();
    }
}
