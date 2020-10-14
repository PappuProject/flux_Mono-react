package com.verizon.learning.web.util;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.verizon.learning.model.Customer;
import com.verizon.learning.model.CustomerAddress;
import com.verizon.learning.model.CustomerAddressRepository;
import com.verizon.learning.model.CustomerRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class DataBuider implements CommandLineRunner {

	@Autowired
	CustomerRepository customerRepo;

	@Autowired
	CustomerAddressRepository customerAddressRepo;

	@Override
	public void run(String... args) throws Exception {
		initialize();
	}

	private void initialize() {

		List<Customer> customers = this.customerData();

		// @formatter:off

        customerRepo
                .deleteAll()
                .thenMany(Flux.fromIterable(customers))
                .flatMap(x -> {
                    Mono<Customer> newlyAddedCustomer = customerRepo.save(x);
                    return newlyAddedCustomer;
                }).thenMany(customerRepo.findAll()).subscribe(x -> {
            System.out.println(x);
        });
        
        customerAddressRepo
        .deleteAll()
        .thenMany(Flux.fromIterable(addressData()))
        .flatMap(x -> {
        	Mono<CustomerAddress> newlyAddedAddress= customerAddressRepo.save(x);
        	return newlyAddedAddress;
        }).thenMany(customerAddressRepo.findAll()).subscribe(x -> {
        	System.out.println(x);
        });
        
        
        
        
        
     // @formatter:on
		/**
		 * 1) CustomerAddressRepository 2) initialize of DataBuilder : delete & insert &
		 * findAll & print CustomerAddresses
		 **/

	}

	private List<CustomerAddress> addressData() {

		return Arrays.asList(new CustomerAddress("9", "101", "101 line1", "101 line2", "101 city", "P"),
				new CustomerAddress("1", "101", "101 line1-a1", "101 line2-a1", "101 city-a1", "P"),
				new CustomerAddress("2", "105", "105 line1-a1", "105 line2", "105 city-a2", "D"),
				new CustomerAddress("3", "102", "102 line1", "102 line2", "102 city", "P"),
				new CustomerAddress("4", "103", "103 line1-1", "103 line2-1", "103 city-1", "P"),
				new CustomerAddress("5", "103", "103 line1-2", "103 line2-2", "103 city-2", "D"),
				new CustomerAddress("6", "103", "103 line1-3", "103 line2-3", "103 city-3", "P"),
				new CustomerAddress("7", "104", "104 line1", "104 line2", "104 city", "D"));

	}

	private List<Customer> customerData() {
		return Arrays.asList(new Customer("101", "Verizon"), new Customer("102", "IBM"), new Customer("103", "Wipro"),
				new Customer("104", "Morgan"), new Customer("105", "Morgan Stanley"));

	}

}
