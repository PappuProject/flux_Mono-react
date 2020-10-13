package com.verizon.learning;

import java.util.List;
import java.util.stream.Collectors;

public class MapAndFlatMapDemo {

    public static void main(String[] args) {

        List<Customer> customers = DataBuilder.customerWithAddressesData();

        /**
         * List<String> (the name of the customers)
         */
        List<String> customerNames = customers
                .stream()
                .map(x -> x.getName())
                .map(x -> x.toLowerCase())
                .collect(Collectors.toList());

        System.out.println(customerNames);
        /**
         * List<CustomerAddress> (all the addresses of all the customers):
         */

//        List<List<CustomerAddress>> allAddresses = customers
//                .stream()
//                .map(x -> x.getAddresses())
//                .collect(Collectors.toList());

        List<CustomerAddress> allAddresses = customers
                .stream()
                .flatMap(x -> x.getAddresses().stream())
                .collect(Collectors.toList());

        for (CustomerAddress allAddress : allAddresses) {
            System.out.println(allAddress.getCity());
        }


//        for(Customer c : customers) {
//            System.out.println(c);
//            System.out.println("****");
//            for(CustomerAddress a : c.getAddresses()) {
//                System.out.println(a.getCity() + " -- " + a.getLine1());
//            }
//            System.out.println("-------------------");
//        }
//


    }
}
