package com.helloboot;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class SimpleHelloServiceCountTest {

    @Autowired
    private HelloService helloService;
    @Autowired
    private HelloRepository helloRepository;

    @Test
    void sayHelloIncreaseCount(){
        IntStream.rangeClosed(1, 10).forEach(count -> {
            helloService.sayHello("woojin");
            assertThat(helloRepository.countOf("woojin")).isEqualTo(count);
        });
    }

}