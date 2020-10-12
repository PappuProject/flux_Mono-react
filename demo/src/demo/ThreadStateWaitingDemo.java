package demo;

import java.util.ArrayList;
import java.util.List;

public class ThreadStateWaitingDemo {

	public static void main(String[] args) {

		MyBoundedDataStructure ds = new MyBoundedDataStructure(3);
		ds.put("v1");
		ds.put("v2");
		ds.put("v3");

		Thread t1 = new Thread() {
			public void run() {

				ds.put("v4");
			};
		};
		
		t1.start();
		
		new Thread() {
			public void run() {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(ds.take());
			};
		}.start();

		new Thread() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("t1 : " + t1.getState().name());
				}
			};
		}.start();

	}

}

class MyBoundedDataStructure {

	private List<String> values = new ArrayList<>();

	private int size;

	public MyBoundedDataStructure(int size) {
		this.size = size;
	}

	public synchronized void put(String value) {
		if (values.size() == size) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		values.add(value);
	}

	public synchronized String take() {
		String value = values.remove(0);
		this.notify();
		return value;
	}

}
