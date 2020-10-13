package demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceDemo {

	public static void main(String[] args) {

		ExecutorService service = Executors.newCachedThreadPool();

		for (int i = 0; i < 5; i++) {
			final int j = i;
			service.execute(new Runnable() {
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

		service.shutdown();

	}

}
