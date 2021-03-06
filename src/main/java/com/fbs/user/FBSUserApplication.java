package com.fbs.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class FBSUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(FBSUserApplication.class, args);
    }

}
