package com.reactive.learn.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@RequestMapping("/api/reactor")
public class MonoFluxResource {

    @GetMapping(value = "/fluxstream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Integer> getFlux() {
        return Flux.just(1, 2, 3, 4, 5)
                .delayElements(Duration.ofSeconds(1))
                .log();
    }

    @GetMapping(value = "/fluxstream/infinite", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Long> getInfiniteFlux() {
        return Flux.interval(Duration.ofSeconds(1))
                .log();
    }
}
