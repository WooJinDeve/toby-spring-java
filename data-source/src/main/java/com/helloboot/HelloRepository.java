package com.helloboot;

import java.util.Optional;

public interface HelloRepository {

    Optional<Hello> findHello(String name);

    void increaseCount(String name);

    default int countOf(String name) {
        return findHello(name)
                .map(Hello::getCount).orElse(0);
    }
}
