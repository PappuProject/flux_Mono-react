package com.verizon.learning;

import java.util.List;

public class MapAndFlatMapDemo {

    public static void main(String[] args) {

        List<Customer> customers = DataBuilder.customerWithAddressesData();

        for(Customer c : customers) {
            System.out.println(c);
            System.out.println("****");
            for(CustomerAddress a : c.getAddresses()) {
                System.out.println(a.getCity() + " -- " + a.getLine1());
            }
            System.out.println("-------------------");
        }



    }
}
