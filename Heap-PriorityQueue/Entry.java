/**
 * @author CSE12 Instructors
 * email: jdegraaf@ucsd.edu
 * 
 * Description: This class sets up entries to be used by Heap.java. This class also contains a
 * 				couple simple methods that can be used on entries within the heap.
 */
public class Entry<K, V> {
	K key; // aka the priority
	V value;
	
	// Constructor initializing the instance variables for an entry
	public Entry(K k, V v) {
		this.key = k;
		this.value = v;
	}
	
	/**
	 * @return String is a string representation of the entry key and value
	 */
	public String toString() {
		return key + ": " + value;
	}
	
	/**
	 * @return K is the key of the entry that we want to ge
	 */
	public K getKey() {
		return key;
	}
}