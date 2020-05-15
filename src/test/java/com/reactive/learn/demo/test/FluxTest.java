package com.reactive.learn.demo.test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

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
}
