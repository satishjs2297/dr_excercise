package com.drmtx.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@EnableAutoConfiguration
@ImportResource("classpath:spring-jpa-config.xml")
public class TaskRunnerApp {
	
	    
    public static void main(String[] args) throws Exception {
        SpringApplication.run(TaskRunnerApp.class, args);
    }

}