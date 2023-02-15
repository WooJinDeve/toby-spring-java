package com.helloboot;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.annotation.TransactionTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@TransactionTest
public class HelloRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private HelloRepository helloRepository;

    @BeforeEach
    void init(){
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS hello(name varchar(50) primary key, count int)");
    }

    @Test
    void findHelloFailed(){
        Assertions.assertTrue(helloRepository.findHello("woojin").isEmpty());
    }

    @Test
    void increaseCount(){
        helloRepository.increaseCount("woojin");
        assertThat(helloRepository.countOf("woojin")).isEqualTo(1);


        helloRepository.increaseCount("woojin");
        assertThat(helloRepository.countOf("woojin")).isEqualTo(2);
    }

}
