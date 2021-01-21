package com.experiencers.server.smj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class SmjApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmjApplication.class, args);
    }

}
