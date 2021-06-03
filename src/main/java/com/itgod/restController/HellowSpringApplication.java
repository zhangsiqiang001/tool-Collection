package com.itgod.restController;

import com.testBean.apply;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableScheduling
public class HellowSpringApplication {
    //hello
    public static void main(String[] args) {
        ConfigurableApplicationContext beanfactory = SpringApplication.run(HellowSpringApplication.class, args);
        apply apply = beanfactory.getBean(com.testBean.apply.class);
        apply.getadd();
    }
}
