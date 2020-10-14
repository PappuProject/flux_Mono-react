package com.verizon.learning.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.verizon.learning.model.CustomerAddress;
import com.verizon.learning.model.CustomerAddressRepository;

import reactor.core.publisher.Flux;

@RestController
public class CustomerAddressRestController {

	@Autowired
	CustomerAddressRepository addressRepo;

	@GetMapping(path = "/customers/{customerId}/addresses", params = { "type" })
	public Flux<CustomerAddress> handleGetCustomerAddressesByCustomerId(@PathVariable String customerId,
			@RequestParam String type) {

		Flux<CustomerAddress> addresses = addressRepo.findByCustomerIdAndType(customerId, type);

		return addresses;
	}

}
