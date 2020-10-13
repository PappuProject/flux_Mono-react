package com.verizon.learning;

import java.util.stream.Stream;

public class FunctionalInterfaceDemo {


    public static void main(String[] args) {

        java.util.function.Function<String, Integer> f1 = x -> x.length();
        java.util.function.BiFunction<String, Integer, Integer> f2 = (x, y) -> x.length();
        java.util.function.Predicate<String> f3 = x -> true;
        java.util.function.Consumer<String> f4 = x -> {
            System.out.println(x);
        };
        java.util.function.Supplier<String> f5 = () -> new String("Shakir");


        Stream<String> clients = Stream.of("Nomura", "Verizon", "IBM");




//        java.util.function.Function<String, Integer> f1 = x -> x.length();
//        java.util.function.Function<String, Integer> f1 = x -> x.length();
//        java.util.function.Function<String, Integer> f1 = x -> x.length();
//
        TaxCalculator t1 = (x, y) -> {
            return 19;
        };
        generateTaxSlips(t1);


    }


    /**
     * Higher Order function
     *
     * @param t
     */
    public static void generateTaxSlips(TaxCalculator t) {

//        float f = t.calculateTax("MAHA");
        t.calculateTax("dasd", 32);


    }

}

@FunctionalInterface
interface TaxCalculator {

    //    public float calculateTax(String state);
//    public float calculateTax();
    public float calculateTax(String state, Integer flatRate);

}