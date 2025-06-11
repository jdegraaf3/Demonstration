/**
 * @author CSE12 Instructors
 * email: jdegraaf@ucsd.edu
 * 
 * Description: This class is just an interface containing given methods from CSE12 instructors.
 * 				These will be methods implemented in Heap.java that are used for a priority queue.
 */

import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Comparator;

public interface PriorityQueue<K, V>{
	void add(K k, V v);

	Entry<K, V> poll();

	Entry<K, V> peek();

	List<Entry<K, V>> toArray();

	boolean isEmpty();

}
