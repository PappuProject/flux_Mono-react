package com.verizon.learning;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ParallelFluxDemo {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("The number of cores is : "
                + Runtime.getRuntime().availableProcessors());

        List<String> clients =
                Arrays.asList("Verizon",
                        "BNP Paribas",
                        "IBM",
                        "Morgan Stanley",
                        "JP Morgan",
                        "Nomura");


        Flux<String> cFlux = Flux.fromIterable(clients);

        CountDownLatch cdl = new CountDownLatch(6);
        long start = System.currentTimeMillis();
        cFlux
                .parallel()
                .runOn(Schedulers.elastic())
                .map(x -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(x + " being transformed in : " + Thread.currentThread().getName());
                    cdl.countDown();
                    return x.toLowerCase();
                })
                .subscribe();

        cdl.await();
        long end = System.currentTimeMillis();

        System.out.println("The time taken is :" + (end - start));

    }

}
