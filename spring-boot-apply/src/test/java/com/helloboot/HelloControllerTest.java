package com.helloboot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class HelloControllerTest {

    @Test
    void helloController(){
        HelloController helloController = new HelloController(helloServiceStub);

        String ret = helloController.hello("Test");

        assertThat(ret).isEqualTo("Test");
    }

    @Test
    void failsHelloController(){
        HelloController helloController = new HelloController(helloServiceStub);

        //request is null
        assertThatThrownBy(() -> helloController.hello(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("name is null");

        //request is empty
        assertThatThrownBy(() -> helloController.hello(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("name is empty");
    }


    private static HelloService helloServiceStub = new HelloService() {
        @Override
        public String sayHello(String name) {
            return name;
        }

        @Override
        public int countOf(String name) {
            return 0;
        }
    };

}
