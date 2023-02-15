package com.helloboot;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private final HelloService helloService;
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    public String hello(String name){
        Assert.notNull(name, "name is null");
        Assert.hasText(name, "name is empty");

        return helloService.sayHello(name);
    }

}
