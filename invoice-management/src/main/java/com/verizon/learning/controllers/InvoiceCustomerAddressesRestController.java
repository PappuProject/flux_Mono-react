package com.verizon.learning.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.verizon.learning.valueobjects.CustomerAddress;

import reactor.core.publisher.Flux;

@RestController
public class InvoiceCustomerAddressesRestController {
	
	WebClient webClient = WebClient.create("http://localhost:8080");
	
	
	private Flux<CustomerAddress> getPostalAddressesByCustomerId(String customerId) {
		// @formatter:off
		return
			webClient
			    .get()
			    .uri(uriBuilder -> {
				    	  return 
				    		uriBuilder
				    		    .path("/customers/{customerId}/addresses")
				    		    .queryParam("type", "P")
				    		    .build(customerId);
			    })
			    .retrieve()
			    .bodyToFlux(CustomerAddress.class);
		// @formatter:on		
	}
	
	private Flux<CustomerAddress> getDeliveryAddressesByCustomerId(String customerId) {
		// @formatter:off
		return
				webClient
				.get()
				.uri(uriBuilder -> {
					return 
							uriBuilder
							.path("/customers/{customerId}/addresses")
							.queryParam("type", "D")
							.build(customerId);
				})
				.retrieve()
				.bodyToFlux(CustomerAddress.class);
		// @formatter:on		
	}

	@GetMapping(path = "/inv-customer-postal-addresses")
	public Flux<CustomerAddress> handleGetPostalAddresses() {
		List<String> ids = Arrays.asList("101", "103", "105");
		// @formatter:off
		Flux<CustomerAddress> addresses=
				Flux.fromIterable(ids)
				    .flatMap(this::getPostalAddressesByCustomerId);
		// @formatter:on				
		return addresses;				
	}
	
	@GetMapping(path = "/inv-customer-addresses")
	public Flux<CustomerAddress> handleGetAddresses() {
		
		
		List<String> ids = Arrays.asList("101", "103", "105");
		// @formatter:off
		Flux<CustomerAddress> postalAddresses =
				
				Flux.fromIterable(ids)
				.flatMap(this::getPostalAddressesByCustomerId);

		Flux<CustomerAddress> deliveryAddresses =
				
				Flux.fromIterable(ids)
				.flatMap(this::getDeliveryAddressesByCustomerId);
		// @formatter:on				
		
		
		Flux<CustomerAddress> addresses = Flux.merge(postalAddresses, deliveryAddresses);
		
		return addresses;				
	}
	

}
