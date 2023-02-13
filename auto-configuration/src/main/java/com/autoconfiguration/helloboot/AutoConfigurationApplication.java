package com.autoconfiguration.helloboot;

import com.autoconfiguration.config.MySpringBootApplication;
import org.springframework.boot.SpringApplication;

@MySpringBootApplication
public class AutoConfigurationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoConfigurationApplication.class, args);
    }

}
