package com.autoconfiguration.helloboot;

import static org.assertj.core.api.Assertions.assertThat;

import com.autoconfiguration.helloboot.HelloDecorator;
import com.autoconfiguration.helloboot.SimpleHelloService;
import org.junit.jupiter.api.Test;


public class HelloServiceTest {

    @Test
    void simpleHelloService(){
        SimpleHelloService helloService = new SimpleHelloService();
        String ret = helloService.sayHello("Test");
        assertThat(ret).isEqualTo("Hello Test");
    }

    @Test
    void helloDecorator(){
        HelloDecorator helloDecorator = new HelloDecorator(name -> name);

        String ret = helloDecorator.sayHello("Test");

        assertThat(ret).isEqualTo("*Test*");
    }
}
