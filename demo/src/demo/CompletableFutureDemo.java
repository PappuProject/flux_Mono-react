package demo;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {

	public static void main(String[] args) throws InterruptedException {

		CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {

			// service call 1
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("This task is executing in : " + Thread.currentThread().getName());
			return "api-result-1";
		});

		/**
		 * Blocking / Non-Blocking ?
		 * 
		 */
		cf.handle((x, y) -> {

			if (y == null) {
				System.out.println("x : " + x);
			} else {
				System.out.println(y.getMessage());
			}
			// service call 2
			return x + " api-result-2 ";

		}).whenComplete((x, y) -> {
			System.out.println(x);
		});

		System.out.println("In main.... ");

		Thread.sleep(10000);

	}
}
