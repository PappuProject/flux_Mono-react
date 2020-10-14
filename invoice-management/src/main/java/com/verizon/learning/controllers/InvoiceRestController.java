package com.verizon.learning.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.verizon.learning.valueobjects.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class InvoiceRestController {

	WebClient webClient = WebClient.create("http://localhost:8080");

	

	@GetMapping(path = "/invoice-customers")
	public Flux<Customer> handleGetInvoiceCustomers() {

		// @formatter:off

		Flux<Customer> invoiceCustomers =	webClient
				.get()
				.uri("/customers")
				.retrieve()
				.bodyToFlux(Customer.class);
		 
		// @formatter:on

		return invoiceCustomers;

	}

	@GetMapping(path = "/invoice-customer-by-ids")
	public Flux<Customer> handleGetCustomerById() {

		List<String> ids = Arrays.asList("101", "103", "105");
		

		// @formatter:off		
		
		Flux<Customer> customers =	
				Flux.fromIterable(ids)
					.flatMap(APIUtil::getCustomerByCustomerId);
		 
		// @formatter:on

		return customers;
	}

}
