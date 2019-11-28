package com.wang.kettle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KettleApplication {

    public static void main(String[] args) {
        SpringApplication.run(KettleApplication.class, args);
    }

}
