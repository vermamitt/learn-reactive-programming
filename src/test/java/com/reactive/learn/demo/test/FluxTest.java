package com.reactive.learn.demo.test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxTest {

    @Test
    public void should_send_data_one_by_one(){

        Flux<String> items = Flux.just("Item 1", "Item 2", "Item 3")
                .log();
        items
                .subscribe(System.out::println);
    }

    @Test
    public void should_send_exception_on_fourth_onEvent(){

        Flux<String> items = Flux.just("Item 1", "Item 2", "Item 3")
                .concatWith(Flux.error(new RuntimeException("Runtime exception occured")))
                .log();
        items
                .subscribe(System.out::println, e -> System.err.println("Exception is: " + e.getMessage()));
    }

    @Test
    public void should_get_complete_message(){

        Flux<String> items = Flux.just("Item 1", "Item 2", "Item 3")
                //.concatWith(Flux.error(new RuntimeException("Runtime exception occured")))
                .log();
        items
                .subscribe(System.out::println, e -> System.err.println("Exception is: " + e.getMessage()),
                        () -> System.out.println("Completed"));
    }

    @Test
    public void should_not_get_complete_message(){

        Flux<String> items = Flux.just("Item 1", "Item 2", "Item 3")
                .concatWith(Flux.error(new RuntimeException("Runtime exception occured")))
                .log();
        items
                .subscribe(System.out::println, e -> System.err.println("Exception is: " + e.getMessage()),
                        () -> System.out.println("Completed"));
    }

    @Test
    public void should_not_get_after_message(){

        Flux<String> items = Flux.just("Item 1", "Item 2", "Item 3")
                .concatWith(Flux.error(new RuntimeException("Runtime exception occured")))
                .concatWith(Flux.just("After Error"))
                .log();
        items
                .subscribe(System.out::println, e -> System.err.println("Exception is: " + e.getMessage()),
                        () -> System.out.println("Completed"));
    }

    @Test
    public void should_get_after_message(){

        Flux<String> items = Flux.just("Item 1", "Item 2", "Item 3")
                //.concatWith(Flux.error(new RuntimeException("Runtime exception occured")))
                .concatWith(Flux.just("After Error"))
                .log();
        items
                .subscribe(System.out::println, e -> System.err.println("Exception is: " + e.getMessage()),
                        () -> System.out.println("Completed"));
    }

    @Test
    public void should_assert_flux_without_error(){

        Flux<String> items = Flux.just("Item 1", "Item 2", "Item 3")
                .log();

        StepVerifier.create(items)
                .expectNext("Item 1")
                .expectNext("Item 2")
                .expectNext("Item 3")
                .verifyComplete();
    }

    @Test
    public void should_assert_flux_with_error(){

        Flux<String> items = Flux.just("Item 1", "Item 2", "Item 3")
                .concatWith(Flux.error(new RuntimeException("Custom Runtime Error")))
                .log();

        StepVerifier.create(items)
                .expectNext("Item 1")
                .expectNext("Item 2")
                .expectNext("Item 3")
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    public void should_assert_flux_with_error_message(){

        Flux<String> items = Flux.just("Item 1", "Item 2", "Item 3")
                .concatWith(Flux.error(new RuntimeException("Custom Runtime Error")))
                .log();

        StepVerifier.create(items)
                .expectNext("Item 1", "Item 2", "Item 3")
                .expectErrorMessage("Custom Runtime Error")
                .verify();
    }

    @Test
    public void should_assert_flux_with_count(){

        Flux<String> items = Flux.just("Item 1", "Item 2", "Item 3")
                .concatWith(Flux.error(new RuntimeException("Custom Runtime Error")))
                .log();

        StepVerifier.create(items)
                .expectNextCount(3)
                .expectErrorMessage("Custom Runtime Error")
                .verify();
    }
}
