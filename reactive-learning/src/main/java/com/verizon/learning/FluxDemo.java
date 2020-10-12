package com.verizon.learning;

import reactor.core.publisher.Flux;

public class FluxDemo {

	public static void main(String[] args) {

		Flux<Integer> intFlux = Flux.range(0, 10);
		
		intFlux
		   .log()
		   .subscribe();

	}

}
