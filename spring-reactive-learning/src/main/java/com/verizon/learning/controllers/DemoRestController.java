package com.verizon.learning.controllers;

import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class DemoRestController {

	@GetMapping(path = "/demo", produces = { MediaType.APPLICATION_STREAM_JSON_VALUE })
	public Flux<Long> demo() {

		System.out.println("Thread : " + Thread.currentThread().getName() + " entered..");

		// @formatter:off
		
		Flux<Long> fluxOfLong = Flux
		  .interval(Duration.ofSeconds(1))
		  .take(10)
		  .log();
		 
		// @formatter:on

		System.out.println("Thread : " + Thread.currentThread().getName() + " is leaving....");

		return fluxOfLong;

	}

}
