package com.helloboot;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class HelloServiceTest {

    @Autowired
    private HelloRepository helloRepository;

    @Test
    void simpleHelloService(){
        SimpleHelloService helloService = new SimpleHelloService(helloRepositoryStub);

        String ret = helloService.sayHello("Test");
        assertThat(ret).isEqualTo("Hello Test");
    }

    @Test
    void helloDecorator(){
        HelloDecorator helloDecorator = new HelloDecorator(name -> name);

        String ret = helloDecorator.sayHello("Test");

        assertThat(ret).isEqualTo("*Test*");
    }

    private static HelloRepository helloRepositoryStub = new HelloRepository() {
        @Override
        public Optional<Hello> findHello(String name) {
            return Optional.empty();
        }

        @Override
        public void increaseCount(String name) {

        }
    };
}
