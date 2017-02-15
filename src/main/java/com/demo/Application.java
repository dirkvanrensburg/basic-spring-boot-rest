package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.demo")
public class Application {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

    }
}