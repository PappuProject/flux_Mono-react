package demo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public class StreamsDemo {

	public static void main(String[] args) {

		System.out.println(Runtime.getRuntime().availableProcessors() + " -- " +

				ForkJoinPool.commonPool().getParallelism());

		List<String> clients = Arrays.asList("Verizon", "BNP Paribas", "IBM", "Morgan Stanley", "JP Morgan", "Nomura");

		long start = System.currentTimeMillis();
		// @formatter:off
				
		List<String> lowerCasedClients = clients
		  .stream()
		  .parallel()
		  .map(x -> {
			  
			  try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  System.out.println(x + " -- " + Thread.currentThread().getName());
			  return x.toLowerCase();
		  })
		  .collect(Collectors.toList());

		// @formatter:on

		long end = System.currentTimeMillis();
		System.out.println("total time taken : " + (end - start));
		lowerCasedClients.forEach(System.out::println);

	}

}
