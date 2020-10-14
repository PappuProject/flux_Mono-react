package com.verizon.learning.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.verizon.learning.valueobjects.Customer;
import com.verizon.learning.valueobjects.CustomerAddress;

import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
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
	 * a) the customer by customerId b) the postalAddresses by customerId c) the
	 * deliveryAddresses by customerId
	 * 
	 * requirement : customer > and its respective postalAddresses +
	 * deliveryAddresses
	 * 
	 */

	@GetMapping(path = "/customer-address-map")
	public Flux<Customer> handleGetCustomerAddressesMap() {

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
		// @formatter:off
		//String customerId = "103";
		
		List<String> ids = Arrays.asList("101", "103", "105");
		Flux<Customer> customers = 
		
				Flux.fromIterable(ids)
				.flatMap(y -> {
					return 
						APIUtil.getCustomerByCustomerId(y)
						.zipWhen(customer -> {
							
							Flux<CustomerAddress> postalAddress = 
									this.getPostalAddressesByCustomerId(customer.getId());
							Flux<CustomerAddress> deliveryAddress = 
									this.getDeliveryAddressesByCustomerId(customer.getId());
							
							return 
									Flux.merge(postalAddress, deliveryAddress)
										.collectList();
							
						})
						.map(x -> {
							
							Customer c = x.getT1();
							List<CustomerAddress> addresses = x.getT2();
							c.setAddresses(addresses);
							return c;
						});
					});
				
		 
		// @formatter:on
		return customers;
	}

	@GetMapping (path ="/grouped-addresses")
	public Flux<Map<String, List<CustomerAddress>>> handleGetGroupedAddresses() {
		
		// @formatter:off

		/*
		 * onNext(["101", [ address1, address2 , address3   ]]
		 * onNext(["102", [ address102, address102 ]]
		 */
		List<String> ids = Arrays.asList("101", "102", "103", "104", "105");
		
		// All the customerAddresses
		Flux<CustomerAddress> allAddresses = 
				 Flux.fromIterable(ids)
				      .flatMap(id -> {
				    	  
				    	  return 
					    	  Flux.merge(this.getDeliveryAddressesByCustomerId(id),
					    			    this.getPostalAddressesByCustomerId(id));
				    			  
				    	  
				      });
		
		// Grouping
	    Flux<GroupedFlux<String, CustomerAddress>>	
		    groupedFlux = 
		    		allAddresses
					   .groupBy(x -> x.getCustomerId());
					
		
	    // map / flatMap 
	    // (transform GroupedFlux into Map<String, List<CustomerAddress>)
	    

	    
	    Flux<Map<String, List<CustomerAddress>>> groupedAddresses =
			groupedFlux
		       .flatMap(groupedId -> 		
		       
		    	   		groupedId
		    	   		   .collectList()
		    	   		   .map(listOfAddresses -> {
			    	   			Map<String, List<CustomerAddress>> m1 = new HashMap<>();
			    	   			m1.put(groupedId.key(), listOfAddresses);
			 		    	    return m1;
		    	   		 })
		    );
		 
		// @formatter:on

		return groupedAddresses;
	}
	
	
	
	

}
