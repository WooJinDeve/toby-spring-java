package com.helloboot;

import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class SpringBootApplyApplication {

    private final JdbcTemplate jdbcTemplate;

    public SpringBootApplyApplication(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    void init(){
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS hello(name varchar(50) primary key, count int)");
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplyApplication.class, args);
    }

}
