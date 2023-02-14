package com.helloboot;

import com.config.MySpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MySpringBootApplication
public class AutoEnvironmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoEnvironmentApplication.class, args);
    }

}
