package com.verizon.learning;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;

public class ZipDemo {

	@Test
	public void when_zip() throws InterruptedException {

		Flux<String> f1 = Flux.just("f1-1", "f1-2", "f1-3");

		Flux<Long> f2 = Flux.interval(Duration.ofSeconds(1));

		Flux<String> f3 = f2.zipWith(f1, (x, y) -> x + " -- " + y);

		f3.log().subscribe();
		Thread.sleep(10000);

	}

}
