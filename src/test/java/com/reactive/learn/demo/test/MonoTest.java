package com.reactive.learn.demo.test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class MonoTest {

    @Test
    void mono() {

        Mono<String> item = Mono.just("Spring");

        StepVerifier.create(item.log())
                .expectNext("Spring")
                .verifyComplete();
    }

    @Test
    void mono_error() {

        Mono<String> item = Mono.error(new RuntimeException());

        StepVerifier.create(item.log())
                .expectError(RuntimeException.class)
                .verify();
    }
}
