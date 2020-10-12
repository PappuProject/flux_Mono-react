package demo;

public class Demo {

	public static void main(String[] args) throws InterruptedException {

		// 
		new Thread() {

			{
				setDaemon(true);
			}

			@Override
			public void run() {
				System.out.println("Thread : " + Thread.currentThread().getName() + " Started...");
				new Thread() {
					{
						setDaemon(false);
					}

					public void run() {
						System.out.println("Thread : " + Thread.currentThread().getName() + " -- "
								+ Thread.currentThread().isDaemon());

						try {
							Thread.sleep(7000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					};
				}.start();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println("Thread : " + Thread.currentThread().getName());

			}
		}.start();

		Thread.sleep(2000);
		System.out.println("Thread : " + Thread.currentThread().getName());

	}

}
