package demo;

import java.util.concurrent.CountDownLatch;

public class ThreadStateBlockedDemo {

	public static void main(String[] args) throws InterruptedException {
		
		Counter counter = new Counter();
		
		CountDownLatch latch = new CountDownLatch(50);
//
//		for (int i = 0; i < 50; i++) {
//			Thread t1 = new Thread() {
//				public void run() {
//					counter.increment();
//					latch.countDown();
//				};
//			};
//			t1.start();
//		}
//
//		latch.await();
//		System.out.println(counter.getCount());

		
		
		Thread t1 = new Thread() {
			public void run() {
				counter.increment();
			}
		};
		
		t1.start();
		Thread t2 = new Thread() {
			public void run() {
				counter.increment();
			}
		};
		Thread.sleep(100);
		t2.start();
		
		
		new Thread() {
			public void run() {
				while (true) {

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					System.out.println("T2 : " + t2.getState().name());

				}
			};

		}.start();
		
		
		
	}

}

class Counter {

	private int count;

	public synchronized void increment() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		count++;
	}

	public synchronized int getCount() {
		return this.count;
	}

}
