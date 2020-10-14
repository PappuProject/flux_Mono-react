package com.verizon.learning;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class ConcatAndMergeDemo {

	@Test
	public void when_concat_and_merge() throws InterruptedException {

		Flux<Integer> f1 = Flux
				             .range(0, 5)
				             .publishOn(Schedulers.parallel())
				             .doOnNext(x -> {
				            	
				            	 try {
									Thread.sleep(501);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
//				            	 System.out.println("x : " + x);
				             });

		// @formatter:off

		Flux<Integer> f2 = Flux
							.range(20, 5)
							.publishOn(Schedulers.elastic())
							.doOnNext(x -> {
								try {
									Thread.sleep(500);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							});
							
		

//		f1.subscribe(x -> {
//			System.out.println(x);
//		});
//
//		f2.subscribe(x -> {
//			System.out.println(x);
//		});
		
		Flux<Integer> f3 = Flux.concat(f1, f2);
		
		f3.subscribe(x -> {
			System.out.println(x);
		});
		
		
		
		Thread.sleep(10000);
		// @formatter:on

	}

}
