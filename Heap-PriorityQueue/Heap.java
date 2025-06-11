/**
 * @author Justin DeGraaf
 * email: jdegraaf@ucsd.edu
 * 
 * Description: This class is my implementation of a heap. This class contains the methods provided
 * 				in PriorityQueue.java along with additional ones that perform different tasks on
 * 				a heap, more specifically a priority queue. This class uses comparable objects so
 * 				we can compare entries within the heap and maintain a proper heap structure.
 */
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Comparator;
public class Heap<K extends Comparable<? super K>, V> implements PriorityQueue<K, V>{
	
	public List<Entry<K, V>> entries;
	public Comparator<K> comparator;
	
	// Constructor that initializes our instance variables
	public Heap(Comparator comparator) {
		this.comparator = comparator;
		entries = new ArrayList<Entry<K, V>>();	
	}
	
	/**
	 * This function compares two key values and returns an int value accordingly
	 * 
	 * @param a is the key of first entry we want to compare
	 * @param b is the key of the second entry we want to compare
	 * @return int is negative if a>b, positive if a<b, and 0 if a=b
	 */
	public int compare(K a, K b) {
		return this.comparator.compare(a, b);
	}
	
	/**
	 * This function adds an Entry into the heap
	 * @param k is the key of the new entry we want to add
	 * @param v is the value of the new entry we want to add
	 */
	@Override
	public void add(K k, V v) {
		entries.add(new Entry(k, v));
		if(size() > 1) {
			bubbleUp(entries.size() - 1);
		}
	}
	
	/**
	 * This function removes the top entry of the heap and returns it
	 * 
	 * @return Entry<K, V> was the top entry of the heap
	 */
	@Override
	public Entry<K, V> poll(){
		if(size() == 0) {
			throw new NoSuchElementException();
		}else if(size() == 1) {
			return entries.remove(0);
		}
		Entry<K, V> output = entries.get(0);
		entries.set(0, entries.remove(size() - 1));
		bubbleDown(0);
		return output;
	}
	
	/**
	 * This function looks at what is on top of our heap
	 * 
	 * @return Entry<K, V> is the current top entry of the heap
	 */
	@Override
	public Entry<K, V> peek(){
		if(entries.size() == 0) {
			throw new NoSuchElementException();
		}
		return entries.get(0);
	}
	
	/**
	 * This function shows us our current heap represented as an array
	 * 
	 * @return List<Entry<K, V>> is the representation of our heap as a array
	 */
	@Override
	public List<Entry<K, V>> toArray(){
		return entries;
	}
	
	/**
	 * This function tells us if our heap is empty or not
	 * 
	 * @return boolean is true if the heap is empty and false is it isn't
	 */
	@Override
	public boolean isEmpty() {return entries.isEmpty();}
	
	/**
	 * This function gives the parent index of the index given
	 * 
	 * @param index is the index of an entry within our heap that we want to find the parent of
	 * @return int is the index of the parent of the index given
	 */
	public int parent(int index) {
		if(index > size()) {
			throw new NoSuchElementException("Invalid Index");
		}else if(index == 0) {
			return 0;
		}
		return (index -1) / 2;
	}
	
	/**
	 * This function gives the index of left child of the index given
	 * 
	 * @param index is the index of an entry within our heap that we want to find the left child of
	 * @return int is the index of the left child of the index given
	 */
	public int left(int index) {
		if(index > size()) {
			throw new NoSuchElementException("Invalid Index");
		}
		return (2*index) + 1;
	}
	
	/**
	 * This function gives the index of right child of the index given
	 * 
	 * @param index is the index of an entry within our heap that we want to find the right
	 * child of
	 * @return int is the index of the right child of the index given
	 */
	public int right(int index) {
		if(index > size()) {
			throw new NoSuchElementException("Invalid Index");
		}
		return (2*index) + 2;
	}
	
	/**
	 * This function swaps two entries within the heap
	 * 
	 * @param i1 is the index of the first entry in the heap we want to swap
	 * @param i2 is the index of the second entry in the heap we want to swap
	 */
	public void swap(int i1, int i2) {
		try {
			Entry<K, V> temp = entries.get(i1);
			entries.set(i1, entries.get(i2));
			entries.set(i2, temp);
		}catch (Exception e) {
			return;
		}
	}
	
	/**
	 * This is a recursive function to move an entry up the tree to maintain a
	 * proper heap structure
	 * 
	 * @param index of the entry we want to bubbleUp
	 */
	public void bubbleUp(int index) {
		if(index > size()) {
			return;
		}
		Entry<K, V> parent = this.entries.get(parent(index));
		Entry<K, V> child = this.entries.get(index);
		if(this.compare(child.key, parent.key) > 0) {
			swap(index, parent(index));
			bubbleUp(parent(index));
		}else {
			return;
		}
	}
	
	/**
	 * This is a recursive function to move an entry down the tree to maintain a
	 * proper heap structure
	 * 
	 * @param index of the entry we want to bubbleDown
	 */
	public void bubbleDown(int index) {
		if(index > size()) {
			return;
		}
		if(left(index) > size()) {
			return;
		}
		if(existsAndGreater(right(index), left(index))) {
			if(existsAndGreater(right(index), index)) {
				swap(right(index), index);
				bubbleDown(right(index));
			}
		}else {
			if(existsAndGreater(left(index), index)) {
				swap(left(index), index);
				bubbleDown(left(index));
			}
		}
	}
	
	/**
	 * This function compares two exits and returns a value accordingly
	 * 
	 * @param index1 is index of the first entry we want to compare
	 * @param index2 is index of the second entry we want to compare
	 * @return boolean is true if index1>index2, is false otherwise
	 */
	public boolean existsAndGreater(int index1, int index2) {
		try {
			int result = this.compare(entries.get(index1).key, entries.get(index2).key);
			if(result > 0) {
				return true;
			}else {
				return false;
			}
		}catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * This function gives us the size of the heap
	 * 
	 * @return int is the size of the curret heap
	 */
	public int size() {return entries.size();}
	
	/**
	 * This function gives us our current heap as a string
	 * 
	 * @return String is a string representation of the heap in an array form
	 */
	public String toString() {
		ArrayList<String> stringList = new ArrayList<>();
		for(Entry<K, V> entry : entries) {
			stringList.add(entry.toString());
		}
		return stringList.toString();
	}
}
