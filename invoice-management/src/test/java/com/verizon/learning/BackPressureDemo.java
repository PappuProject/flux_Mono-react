package com.verizon.learning;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;

import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class BackPressureDemo {

	@Test
	public void when_back_pressure_demo() {

		Flux<Integer> ints = Flux.range(0, 15);
		
		
		ints
		 .log()
		 .subscribe(new BaseSubscriber<Integer>() {
			
			@Override
			protected void hookOnSubscribe(Subscription subscription) {
				System.out.println("onSubcribe Event is fired by the publisher");
				subscription.request(6);
			}
			
			@Override
			protected void hookOnNext(Integer value) {
			
//				System.out.println("value is received : " + value);
				
				if (value > 0 && value % 5 == 0) {
					try {
						System.out.println("Will request after 5 seconds");
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					super.request(5);
				}
				
			}
			
			
			
			
		});

	}

}
