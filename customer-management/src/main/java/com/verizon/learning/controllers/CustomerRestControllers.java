package com.verizon.learning.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.verizon.learning.model.Customer;
import com.verizon.learning.model.CustomerDAO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CustomerRestControllers {

	@Autowired
	CustomerDAO customerDAO;

	@GetMapping(path = "/customers")
	public Flux<Customer> handleGetCustomers() {

		Flux<Customer> customers = customerDAO.getAllCustomers().log();

		return customers;

	}

	@GetMapping(path = "/customers/{customerId}")
	public Mono<ResponseEntity<Customer>> handleGetCustomerById(@PathVariable String customerId) {

		// @formatter:off
				      
		Mono<ResponseEntity<Customer>> responseEntity =
				   customerDAO
				      .getCustomerById(customerId)
				      .map(x -> new ResponseEntity<>(x , HttpStatus.OK))
				      .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NO_CONTENT));
		 
		// @formatter:on

//		
//		if (optionalCustomer.isPresent()) {
//			return new ResponseEntuty///
//		} else {
//			return new ResponseEntity(optionalCustomer.get(), HttpStatus.OK);
//		}

		return responseEntity;

	}

}
