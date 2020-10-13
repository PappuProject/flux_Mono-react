package com.verizon.learning;

import java.util.Arrays;
import java.util.List;


public class DataBuilder {


    public static List<CustomerAddress> addressData() {

        return Arrays.asList(new CustomerAddress("9", "101", "101 line1", "101 line2", "101 city", "P"),
                new CustomerAddress("1", "101", "101 line1-a1", "101 line2-a1", "101 city-a1", "P"),
                new CustomerAddress("2", "105", "105 line1-a1", "105 line2", "105 city-a2", "D"),
                new CustomerAddress("3", "102", "102 line1", "102 line2", "102 city", "P"),
                new CustomerAddress("4", "103", "103 line1-1", "103 line2-1", "103 city-1", "P"),
                new CustomerAddress("5", "103", "103 line1-2", "103 line2-2", "103 city-2", "D"),
                new CustomerAddress("6", "103", "103 line1-3", "103 line2-3", "103 city-3", "P"),
                new CustomerAddress("7", "104", "104 line1", "104 line2", "104 city", "D"));

    }

    public static List<Customer> customerData() {
        // @formatter:off

        return Arrays.asList(new Customer("101", "Verizon"),
                new Customer("102", "IBM"),
                new Customer("103", "Wipro"),
                new Customer("104", "Morgan"),
                new Customer("105", "Morgan Stanley")
        );

        // @formatter:on

    }

    public static List<Customer> customerWithAddressesData() {
        // @formatter:off

        return Arrays.asList(new Customer("101", "Verizon", addressData().subList(0, 2)),
                new Customer("102", "IBM", addressData().subList(3, 4)),
                new Customer("103", "Wipro", addressData().subList(4, 7)),
                new Customer("104", "Morgan", addressData().subList(7, 8)),
                new Customer("105", "Morgan Stanley", addressData().subList(2, 3))
        );

        // @formatter:on

    }


}
