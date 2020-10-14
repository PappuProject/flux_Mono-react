package com.verizon.learning;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;

public class ErrorHandlingDemo {

	@Test
	public void on_error_resume() {

		// @formatter:off

		Flux<Integer> ints = Flux.range(0, 5);
		
//		Flux<Integer> mappedIntegers = 
//				ints
//				   .map(x -> {
//					   
//					   if (x == 2) {
//						   throw new RuntimeException(x + " recevied the error");
//					   }
//					   return x + 1;
//				   })
//				   .onErrorResume(x -> {
//					  
//					   System.out.println("The errro : " + x.getMessage());
//					   return Flux.just(10, 20);
//					   
//				   });
////				   .log();
		
		Flux<Integer> mappedIntegers = 
				ints
				.map(x -> {
					
					if (x == 2) {
						throw new RuntimeException(x + " recevied the error");
					}
					return x + 1;
				})
				.onErrorContinue( (x, y) -> {
					
					System.out.println(x.getMessage() + " -- " + y);
						
				});
//				   .log();
		
		
		   mappedIntegers
		     .subscribe(
		    		 
		    		 // 1st parameter
		    		 x -> {System.out.println(x);},
		    		 
		    		 // 2nd parameter
		    		 x -> {System.out.println("on Error " + x.getMessage());},
		    		 
		    		 // 3rd parameter
		    		 () -> { 
		    			 System.out.println("Completed");
		    		 }
		    	);

	}

}
