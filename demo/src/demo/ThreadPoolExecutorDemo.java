package demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {

	public static void main(String[] args) {

		ThreadPoolExecutor tpe = new ThreadPoolExecutor(5, 20, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<>(300));

		for (int i = 0; i < 5; i++) {
			final int j = i;
			tpe.execute(new Runnable() {
				@Override
				public void run() {

					System.out.println(j + " the task is executing in : " + Thread.currentThread().getName());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			});
		}

		tpe.shutdown();
		// @formatter:on

	}

}
