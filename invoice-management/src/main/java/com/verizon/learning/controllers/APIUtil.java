package com.verizon.learning.controllers;

import org.springframework.web.reactive.function.client.WebClient;

import com.verizon.learning.valueobjects.Customer;

import reactor.core.publisher.Mono;

public class APIUtil {
	
	public static WebClient webClient = WebClient.create("http://localhost:8080");

	public static Mono<Customer> getCustomerByCustomerId(String customerId) {

		// @formatter:off

		Mono<Customer> customer = webClient
				   .get()
				   .uri("/customers/{customerId}", customerId)
				   .retrieve()
				   .bodyToMono(Customer.class);
				
				return customer;
				 
		// @formatter:on

	}
}
