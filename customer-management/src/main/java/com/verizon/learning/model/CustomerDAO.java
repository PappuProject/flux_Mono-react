package com.verizon.learning.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CustomerDAO {

    @Autowired
    CustomerRepository customerRepo;

    public Flux<Customer> getAllCustomers() {

        Flux<Customer> customers = customerRepo.findAll();

        return customers;
    }

    public Mono<Customer> getCustomerById(String id) {

        Mono<Customer> monoCustomer = customerRepo.findById(id);

        return monoCustomer;

    }

}
