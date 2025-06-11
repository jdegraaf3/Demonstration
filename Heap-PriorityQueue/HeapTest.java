/**
 * @author Justin DeGraaf/CSE12 Instructors
 * email: jdegraaf@ucsd.edu
 * 
 * Description: This class tests the Heap.java class. It uses JUnit to compare answers to expected
 * 				outputs that we put in manually. In this class we test each method within the
 * 				Heap.java class to make sure everything functions properly.
 */

//import static org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.Comparator;

import org.junit.Test;

/**
 * HeapTest class implements tester that will test the methods from heap file
 */
public class HeapTest {

	@Test
	public void testAddAndSize() {
		Comparator<Integer> maxHeapComparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		};
		Heap<Integer, String> heap = new Heap<Integer, String>(maxHeapComparator);
		heap.add(19, "");
		heap.add(50, "10");
		heap.add(30, "10");
		heap.add(15, "10");
		heap.add(20, "10");
		heap.add(10, "10");
		heap.add(5, "");
		heap.add(2, "");
		assertEquals(8, heap.entries.size());
	}
	
	@Test
	public void testPoll() {
		Comparator<Integer> maxHeapComparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		};
		Heap<Integer, String> heap = new Heap<Integer, String>(maxHeapComparator);
		heap.add(19, "");
		heap.add(50, "10");
		heap.add(30, "10");
		heap.add(15, "10");
		heap.add(20, "10");
		heap.add(10, "10");
		heap.add(5, "");
		heap.add(2, "");
		assertEquals(heap.poll().key == 50, true);
		assertEquals(heap.poll().key == 30, true);
		assertEquals(6, heap.entries.size());
	}
	
	
	@Test
	public void testPeek() {
		Comparator<Integer> maxHeapComparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		};
		Heap<Integer, String> heap = new Heap<Integer, String>(maxHeapComparator);
		heap.add(19, "");
		heap.add(50, "10");
		heap.add(30, "10");
		heap.add(15, "10");
		heap.add(20, "10");
		heap.add(10, "10");
		heap.add(5, "");
		heap.add(2, "");
		assertEquals(heap.peek().key == 50, true);
	}
	
	@Test
	public void testToArray() {
		Comparator<Integer> maxHeapComparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		};
		Heap<Integer, String> heap = new Heap<Integer, String>(maxHeapComparator);
		heap.add(19, "");
		heap.add(50, "10");
		heap.add(30, "10");
		heap.add(15, "10");
		heap.add(20, "10");
		heap.add(10, "10");
		heap.add(5, "");
		heap.add(2, "");
		assertArrayEquals(heap.toArray().toArray(), heap.entries.toArray());
	}
	
	@Test
	public void testEmpty() {
		Comparator<Integer> maxHeapComparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		};
		Heap<Integer, String> heap = new Heap<Integer, String>(maxHeapComparator);
		heap.add(19, "");
		heap.add(50, "10");
		heap.add(30, "10");
		heap.add(15, "10");
		heap.add(20, "10");
		heap.add(10, "10");
		heap.add(5, "");
		heap.add(2, "");
		assertEquals(heap.isEmpty(), false);
		
		Heap<Integer, String> heap2 = new Heap<Integer, String>(maxHeapComparator);
		assertEquals(heap2.isEmpty(), true);
	}
	
	@Test
	public void testParentLeftRight() {
		Comparator<Integer> maxHeapComparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		};
		Heap<Integer, String> heap = new Heap<Integer, String>(maxHeapComparator);
		heap.add(19, "");
		heap.add(50, "10");
		heap.add(30, "10");
		heap.add(15, "10");
		heap.add(20, "10");
		heap.add(10, "10");
		heap.add(5, "");
		heap.add(2, "");
		assertEquals(heap.parent(4), 1);
		assertEquals(heap.parent(5), 2);
		assertEquals(heap.parent(0), 0);
		assertEquals(heap.left(4), 9);
		assertEquals(heap.left(5), 11);
		assertEquals(heap.left(0), 1);
		assertEquals(heap.right(4), 10);
		assertEquals(heap.right(5), 12);
		assertEquals(heap.right(0), 2);
		
	}
	
	@Test
	public void testSwap() {
		Comparator<Integer> maxHeapComparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		};
		Heap<Integer, String> heap = new Heap<Integer, String>(maxHeapComparator);
		heap.add(19, "");
		heap.add(50, "10");
		heap.swap(0, 1);
		assertEquals(heap.peek().key == 19, true);
	}
	
	@Test
	public void testBubbleUpandDown() {
		Comparator<Integer> maxHeapComparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		};
		Heap<Integer, String> heap = new Heap<Integer, String>(maxHeapComparator);
		heap.add(19, "");
		heap.add(50, "10");
		heap.add(30, "10");
		heap.add(15, "10");
		heap.add(20, "10");
		heap.poll();
		assertEquals(heap.peek().key == 30, true);
		heap.add(10, "10");
		heap.add(5, "");
		heap.add(2, "");
		heap.add(1000, "10");
		assertEquals(heap.peek().key == 1000, true);
	}
	
	@Test
	public void testExitsAndGreater() {
		Comparator<Integer> maxHeapComparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		};
		Heap<Integer, String> heap = new Heap<Integer, String>(maxHeapComparator);
		heap.add(19, "");
		heap.add(50, "10");
		assertEquals(heap.existsAndGreater(0, 1), true);
		assertEquals(heap.existsAndGreater(1, 0), false);
	}
	
	@Test
	public void testToString() {
		Comparator<Integer> maxHeapComparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		};
		Heap<Integer, String> heap = new Heap<Integer, String>(maxHeapComparator);
		heap.add(19, "");
		heap.add(50, "10");
		ArrayList<String> output = new ArrayList<String>();
		output.add("50" + ": " + "10");
		output.add("19" + ": " + "");
		assertEquals(heap.toString(), output.toString());
	}
}