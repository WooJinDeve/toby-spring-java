package com.example.ditest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;


public class HelloApiTest {

    @Test
    void helloApi() {
        // http localhost:8080/hello?name=Spring
        TestRestTemplate rest = new TestRestTemplate();

        ResponseEntity<String> res = rest
                .getForEntity("http://localhost:8080/hello?name={name}", String.class, "Spring");

        // status code 200
        // header(content-type) text/plain
        // body Hello Spring
        assertThat(res.getStatusCode()).isEqualTo(OK);
        assertThat(res.getHeaders().getFirst(CONTENT_TYPE)).startsWith(TEXT_PLAIN_VALUE);
        assertThat(res.getBody()).isEqualTo("*Hello Spring*");
    }

    @Test
    void failsHelloApi() {
        // http localhost:8080/hello?name=Spring
        TestRestTemplate rest = new TestRestTemplate();

        ResponseEntity<String> res = rest
                .getForEntity("http://localhost:8080/hello?name=", String.class);

        // status code 500
        assertThat(res.getStatusCode()).isEqualTo(INTERNAL_SERVER_ERROR);
    }
}
