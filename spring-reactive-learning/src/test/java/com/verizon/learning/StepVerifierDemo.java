package com.verizon.learning;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class StepVerifierDemo {

	@Test
	public void when_stepverifier() {

		Flux<Integer> f0 = Flux.range(0, 5);
		// @formatter:off

		StepVerifier.create(f0)
					.expectSubscription()
					.expectNext(0)
					.expectNext(1)
					.expectNext(2)
					.expectNext(3)
					.expectNext(4)
					.verifyComplete();
		// @formatter:on

	}

	@Test
	public void when_expectcount() {

		Flux<Integer> f0 = Flux.range(0, 5);
		// @formatter:off
		
		StepVerifier.create(f0)
		.expectSubscription()
		.expectNext(1, 2,3, 4,5)		
		.verifyComplete();
		
		StepVerifier.create(f0)
		.expectSubscription()
		.verifyComplete();
		// @formatter:on

	}

	@Test
	public void when_using_map() {

		Flux<Integer> f0 = Flux.range(0, 5).map(x -> x + 1);
		// @formatter:off
		
		StepVerifier.create(f0)
		.expectSubscription()
		.expectNext(1)
		.expectNext(2)
		.expectNext(3)
		.expectNext(4)
		.expectNext(5)
		.verifyComplete();
		// @formatter:on

	}

	@Test
	public void when_using_map_and_filter() {

		Flux<Integer> f0 = Flux.range(0, 5).map(x -> x + 1).filter(x -> x > 3);
		// @formatter:off
		
		StepVerifier.create(f0)
		.expectSubscription()
		.expectNext(4)
		.expectNext(5)
		
		.verifyComplete();
		// @formatter:on

	}

}
