package com.helloboot;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.annotation.HelloBootTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@HelloBootTest
public class JdbcTemplateTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void init(){
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS hello(name varchar(50) primary key, count int)");
    }

    @Test
    void insertAndQuery(){
        jdbcTemplate.update("INSERT INTO hello VALUES(?, ?)", "woojin", 3);
        jdbcTemplate.update("INSERT INTO hello VALUES(?, ?)", "spring", 3);

        Long count = jdbcTemplate.queryForObject("SELECT count(*) FROM hello", Long.class);

        assertThat(count).isEqualTo(2);
    }
}
