package com.verizon.learning;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FluxDemo {

    public static void main(String[] args) {


        List<String> values = new ArrayList<String>();
        Collections.sort(values);


        Flux<Integer> intFlux = Flux.range(0, 10);

        intFlux
                .log()
                .subscribe();
        // same thing as above


        // Create flux of string with 5 strings provided to .just function
        Flux<String> fluxOfStrings = Flux.just("");

        // put atleast 5 client names
        List<String> clients = Arrays.asList("", "");
        //Flux<String> fluxOfStrings2 = Flux.fromIterable(<< Some list over here>> )

        //
        //Flux<String> fluxOfString3 = Flux.fromArray(array)

        Flux<Long> longFlux = Flux.interval(Duration.ofSeconds(1));


    }


}
