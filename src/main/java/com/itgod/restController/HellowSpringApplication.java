package com.itgod.restController;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class HellowSpringApplication {
    //hello
    public static void main(String[] args) {
        SpringApplication.run(HellowSpringApplication.class, args);
    }
}
