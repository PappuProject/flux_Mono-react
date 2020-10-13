package com.verizon.learning;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class ParallelSchedulerDemo {

	public static void main(String[] args) throws InterruptedException {

//		ExecutorService service = Executors.newFixedThreadPool(5);
//		service.execute(() -> {
//			
//		});
//	

		int numberOfTasks = 16;
		CountDownLatch cdl = new CountDownLatch(numberOfTasks);
		Scheduler scheduler1 = Schedulers.parallel();

		long start = System.currentTimeMillis();
		for (int i = 0; i < numberOfTasks; i++) {
			final int j = i;
			scheduler1.schedule(new Runnable() {
				@Override
				public void run() {

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					System.out.println("s1 - " + j + " The task is executed in : " + Thread.currentThread().getName()
							+ " - " + Thread.currentThread().isDaemon() + " -- " + Thread.currentThread().hashCode());

					cdl.countDown();
				}
			});
		}

		cdl.await();
		long end = System.currentTimeMillis();
		System.out.println("Total time taken :" + (end - start));

	}

}
