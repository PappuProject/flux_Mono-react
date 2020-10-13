package com.verizon.learning.web.util;

import com.verizon.learning.model.Customer;
import com.verizon.learning.model.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
public class DataBuider implements CommandLineRunner {


    @Autowired
    CustomerRepository customerRepo;


    @Override
    public void run(String... args) throws Exception {
        initialize();
    }

    private void initialize() {

        List<Customer> customers = this.customerData();

        customerRepo
                .deleteAll()
                .thenMany(Flux.fromIterable(customers))
                .flatMap(x -> {
                    Mono<Customer> newlyAddedCustomer = customerRepo.save(x);
                    return newlyAddedCustomer;
                })
                .thenMany(customerRepo.findAll())
                .subscribe(x -> {
                    System.out.println(x);
                });


//
//        customerRepo
//                .deleteAll();
////
////        Customer c = new Customer();
////        Mono<Customer> newlyAddedCustomer = customerRepo.save(c);
//
//        Flux<Customer> f = Flux.fromIterable(customers)
//                .flatMap(x -> {
//
//                    Mono<Customer> newlyAddedCustomer = customerRepo.save(x);
//                    return newlyAddedCustomer;
////                    return x;
//                });
//
//        Flux<Customer> allNewlyInsertedCustomers =
//                customerRepo.findAll();
//
//        allNewlyInsertedCustomers
//                .subscribe(x -> {
//                            System.out.println(x);
//                        }
//                );


    }

    private List<Customer> customerData() {
        return Arrays.asList(new Customer("101", "Verizon"),
                new Customer("102", "IBM"),
                new Customer("103", "Wipro"),
                new Customer("104", "Morgan"),
                new Customer("105", "Morgan Stanley")
        );

    }


}
