package com.verizon.learning.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.verizon.learning.valueobjects.Customer;
import com.verizon.learning.valueobjects.CustomerAddress;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
	
	/**
	 * a) the customer by customerId
	 * b) the postalAddresses by customerId
	 * c) the deliveryAddresses by customerId
	 * 
	 * requirement : customer > and its respective postalAddresses
	 * 
	 */
	
	@GetMapping (path ="/customer-address-map")
	public Mono<Customer> handleGetCustomerAddressesMap() {
		
		String customerId = "103";
		
//		Mono<Customer> monoCustomer =
//				   APIUtil.getCustomerByCustomerId(customerId)
//				   		  .zipWhen(customer -> {
//				   			  
//				   			  Flux<CustomerAddress> postalAddress = 
//				   					  this.getPostalAddressesByCustomerId(customer.getId());
//				   			  
//				   			  return postalAddress.collectList();
//				   			  
//				   		  });
		
//		Mono<Tuple2<Customer, List<CustomerAddress>>>
//			monoCustomer =
//				APIUtil.getCustomerByCustomerId(customerId)
//					.zipWhen(customer -> {
//						
//						Flux<CustomerAddress> postalAddress = 
//								this.getPostalAddressesByCustomerId(customer.getId());
//						
//						return postalAddress.collectList();
//						
//					});
//		
		Mono<Customer>
			monoCustomer =
				APIUtil.getCustomerByCustomerId(customerId)
				.zipWhen(customer -> {
					
					Flux<CustomerAddress> postalAddress = 
							this.getPostalAddressesByCustomerId(customer.getId());
					
					return postalAddress.collectList();
					
				})
				.map(x -> {
					
					Customer c = x.getT1();
					List<CustomerAddress> addresses = x.getT2();
					c.setAddresses(addresses);
					return c;
				});
				
					   		  
		
		
		return monoCustomer;
		
	}
	
	
	
	

}
