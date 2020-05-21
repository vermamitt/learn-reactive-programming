package com.reactive.learn.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
@WebFluxTest
class MonoFluxResourceTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void should_get_flux() {
        Flux<Integer> response = webTestClient.get().uri("/api/reactor/fluxstream")
                .exchange()
                .expectStatus().isOk()
                .returnResult(Integer.class)
                .getResponseBody();
        StepVerifier.create(response)
                .expectNext(1, 2, 3, 4, 5)
                .verifyComplete();
    }

    @Test
    void should_get_infinite_flux() {
        Flux<Long> response = webTestClient.get().uri("/api/reactor/fluxstream/infinite")
                .exchange()
                .expectStatus().isOk()
                .returnResult(Long.class)
                .getResponseBody();
        StepVerifier.create(response)
                .expectNext(0l, 1l, 2l, 3l)
                .thenCancel()
                .verify();
    }
}