/**
 * @author Justin DeGraaf
 * email: jdegraaf@ucsd.edu
 * 
 * Description: This class contains one key complex method to perform on a heap. This class uses
 * 				methods from Heap.java along with using the comparator class frequently to
 * 				compare values.
 */

import java.util.Scanner;
import java.util.Comparator;
import java.io.File;
import java.io.FileNotFoundException;

public class ElementFinder {
	
	Comparator<Integer> smallComparator;
	Comparator<Integer> largeComparator;
	static Heap<Integer, Integer> heap;
	
	/**
	 * This method returns the kth largest or kth smallest element in the heap
	 * 
	 * @param filename is the file we want to pull information from to add to our heap
	 * @param K is kth smallest or largest integer value of the heap we want
	 * @param operation is used to tell the method whether we want to find the kth smallest
	 * integer or kth largest integer
	 * @return int is the kth smallest or kth largest integer
	 */
	
	public static int Kth_finder(String filename, int K, String operation) {
		
		Comparator<Integer> smallComparator;
		Comparator<Integer> largeComparator;
		if(operation.equals("largest") || operation.equals("smallest")) {
			smallComparator = new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					return o2 - o1;
				}
				
			};
			
			largeComparator = new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					return o1 - o2;
				}
				
			};
			if(operation.equals("largest")) {
				heap = new Heap<>(smallComparator);
			}else {
				heap = new Heap<>(largeComparator);
			}
		}else {
			return -1;
		}
		
		try {
			File f = new File(filename);
			Scanner sc = new Scanner(f);
			int size = K + 1;
			while(sc.hasNextLine()) {
				String[] data = sc.nextLine().split(" ");
				for(int i = 0; i< data.length; i++) {
					heap.add(Integer.valueOf(data[i]), Integer.valueOf(data[i]));
					if(heap.entries.size() > size) {
						heap.poll();
					}
				}
			}
			sc.close();
		}catch(FileNotFoundException e) {
			return -1;
		}
		
		if(heap.size() == 0) {
			return -1;
		}
		
		heap.poll();
		return heap.peek().key;
		
	}
	
	/* Add any helper methods you find useful */
		
}
