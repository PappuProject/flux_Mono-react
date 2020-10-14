package com.verizon.learning;

import java.util.List;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CollectListDemo {
	
	@Test
	public void when_collect_list() {
		
		
		Flux<String> f = Flux.just("1", "2", "3");
		
		Mono<List<String>> m =  f.collectList();
		
		m.subscribe(x -> {
		
			
		});
		
		
	}

}
