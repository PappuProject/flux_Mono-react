package com.verizon.learning;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class StepVerifierDemo {


    @Test
    public void step_verifier() {

        Flux<Integer> i = Flux.range(0, 10);

        StepVerifier
                .create(i)
                .expectSubscription()
                .expectNextCount(10)
                .verifyComplete();
    }


}
