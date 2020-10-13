package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestParallelSorter {

	private static void $sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws InterruptedException {

		List<Integer> data = new ArrayList<Integer>();
		Random r = new Random();
		int totalValues = 10000000;
		for (int i = 0; i < totalValues; i++) {
			data.add(r.nextInt(totalValues));

		}
		System.out.println("Starting sort with Collections.sort");
		//
//		 long start1 = System.currentTimeMillis();
//		 
//		 /**
//		  * Single Threaded
//		  */
//		 Collections.sort(data);
//		 
//		 long end1 = System.currentTimeMillis();
//		 
//		 System.out.println("Time taken for Collections.sort : " + (end1 - start1));

		ParallelSorter<Integer> sorter = new ParallelSorter<Integer>(data, new ReentrantReadWriteLock());

		System.out.println("Using ParallelSorter");
		long start2 = System.currentTimeMillis();

		sorter.sort();

		long end2 = System.currentTimeMillis();
		System.out.println("Time taken for ParallelSorter: " + (end2 - start2));

	}
}
