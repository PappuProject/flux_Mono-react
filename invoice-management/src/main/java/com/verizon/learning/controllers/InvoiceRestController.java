package com.verizon.learning.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.verizon.learning.valueobjects.Customer;

import reactor.core.publisher.Flux;

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

}
