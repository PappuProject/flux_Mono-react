package demo;

public class DemoToHari {
	
	public static void main(String[] args) {
		
		// main thread
		
		MyOwnComponentLikeFlux f = new MyOwnComponentLikeFlux();
		
		f.process();
	}

}

class MyOwnComponentLikeFlux {
	
	
	public void process() {
		
		new Thread() {
			@Override
			public void run() {
				
				// Thread -0
			}
		}.start();
	
		
	}
	
}
