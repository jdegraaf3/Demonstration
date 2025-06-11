/*
 * @author Justin DeGraaf
 * email: jdegraaf@ucsd.edu
 * 
 * Description: This file creates a hash map called HashMap. It implements methods from the
 * 				interface DefaultMap.java which also contain all the method descriptions of the
 * 				methods lacking description in the file. I decided to create a hash map that uses
 * 				separate chaining thus using the buckets instance variable and not entries.
 *
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.LinkedList;
import java.util.Collections;

public class MyHashMap<K, V> implements DefaultMap<K, V> {
	public static final double DEFAULT_LOAD_FACTOR = 0.75;
	public static final int DEFAULT_INITIAL_CAPACITY = 16;
	public static final String ILLEGAL_ARG_CAPACITY = "Initial Capacity must be non-negative";
	public static final String ILLEGAL_ARG_LOAD_FACTOR = "Load Factor must be positive";
	public static final String ILLEGAL_ARG_NULL_KEY = "Keys must be non-null";
	
	private double loadFactor;
	private int capacity;
	private int size;

	// Use this instance variable for Separate Chaining conflict resolution
	private List<HashMapEntry<K, V>>[] buckets;  
	
	// Use this instance variable for Linear Probing
	//private HashMapEntry<K, V>[] entries; 	

	public MyHashMap() {
		this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
	}
	
	/**
	 * 
	 * @param initialCapacity the initial capacity of this MyHashMap
	 * @param loadFactor the load factor for rehashing this MyHashMap
	 * @throws IllegalArgumentException if initialCapacity is negative or loadFactor not
	 * positive
	 */
	@SuppressWarnings("unchecked")
	public MyHashMap(int initialCapacity, double loadFactor) throws IllegalArgumentException {
		this.loadFactor = loadFactor;
		this.capacity = initialCapacity;
		this.size = 0;
		
		if(initialCapacity < 0) {
			throw new IllegalArgumentException(ILLEGAL_ARG_LOAD_FACTOR);
		}
		else {
			this.loadFactor = loadFactor;
		}
		
		if(loadFactor <= 0) {
			throw new IllegalArgumentException(ILLEGAL_ARG_CAPACITY);
		}
		else {
			this.capacity = initialCapacity;
		}

		// if you use Separate Chaining
		buckets = (List<HashMapEntry<K, V>>[]) new List<?>[capacity];
		for(int i = 0; i < capacity; i++) {
			List<HashMapEntry<K, V>> bucket = new LinkedList<HashMapEntry<K, V>>();
			buckets[i] = bucket;
		}

		// if you use Linear Probing
		//entries = (HashMapEntry<K, V>[]) new HashMapEntry<?, ?>[initialCapacity];
	}
	
	/**
	 * Adds the specified key, value pair to this DefaultMap
	 * Note: duplicate keys are not allowed
	 * 
	 * @return true if the key value pair was added to this DefaultMap
	 * @throws IllegalArgument exception if the key is null
	 */
	@Override
	public boolean put(K key, V value) throws IllegalArgumentException {
		
		if(key == null) {
			throw new IllegalArgumentException(ILLEGAL_ARG_NULL_KEY);
		}
		if(this.containsKey(key) == true) {
			return false;
		}
		List<HashMapEntry<K, V>> bucket;
		int index = this.getIndex(key);
		if(buckets[index] != null) {
			bucket = buckets[index];
		}
		else {
			bucket = new LinkedList<HashMapEntry<K, V>>();
		}
		HashMapEntry<K, V> entry = new HashMapEntry<K, V>(key, value);
		bucket.add(entry);
		this.size++;
		if(size >= loadFactor * capacity) {
			expandCapacity();
		}
		return true;
	}
	
	/**
	 * Replaces the value that maps to the key if it is present
	 * @param key The key whose mapped value is being replaced
	 * @param newValue The value to replace the existing value with
	 * @return true if the key was in this DefaultMap
	 * @throws IllegalArgument exception if the key is null
	 */
	@Override
	public boolean replace(K key, V newValue) throws IllegalArgumentException {
		if(key == null) {
			throw new IllegalArgumentException(ILLEGAL_ARG_NULL_KEY);
		}
		int index = getIndex(key);
		List<HashMapEntry<K, V>> bucket = buckets[index];
		HashMapEntry<K, V> entry = new HashMapEntry<K, V>(key, newValue);
		if(containsKey(key) == false) {
			return false;
		}
		bucket.set(0, entry);
		return true;
	}
	
	/**
	 * Remove the entry corresponding to the given key
	 * 
	 * @return true if an entry for the given key was removed
	 * @throws IllegalArgument exception if the key is null
	 */
	@Override
	public boolean remove(K key) throws IllegalArgumentException {
		if(key == null) {
			throw new IllegalArgumentException(ILLEGAL_ARG_NULL_KEY);
		}
		if(containsKey(key) == false) {
			return false;
		}
		int index = getIndex(key);
		List<HashMapEntry<K, V>> bucket = buckets[index];
		bucket.remove(0);
		size--;
		return true;
	}
	
	/**
	 * Adds the key, value pair to this DefaultMap if it is not present,
	 * otherwise, replaces the value with the given value
	 * @throws IllegalArgument exception if the key is null
	 */
	@Override
	public void set(K key, V value) throws IllegalArgumentException {
		if(key == null) {
			throw new IllegalArgumentException(ILLEGAL_ARG_NULL_KEY);
		}
		if(containsKey(key)) {
			replace(key, value);
		}
		put(key, value);
	}
	
	/**
	 * @return the value corresponding to the specified key, null if key doesn't 
	 * exist in hash map
	 * @throws IllegalArgument exception if the key is null
	 */
	@Override
	public V get(K key) throws IllegalArgumentException {
		if(key == null) {
			throw new IllegalArgumentException(ILLEGAL_ARG_NULL_KEY);
		} 
		if(this.containsKey(key) == false) {
			return null;
		}
		int index = getIndex(key);
		List<HashMapEntry<K, V>> bucket = buckets[index];
		if(bucket != null) {
			for(HashMapEntry<K, V> entry : bucket) {
				if(entry.getKey().equals(key)) {
					return entry.value;
				}
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @return The number of (key, value) pairs in this DefaultMap
	 */
	@Override
	public int size() {return size;}
	
	/**
	 * 
	 * @return true iff this.size() == 0 is true
	 */
	@Override
	public boolean isEmpty() {return this.size() == 0;}
	
	/**
	 * @return true if the specified key is in this DefaultMap
	 * @throws IllegalArgument exception if the key is null
	 */
	@Override
	public boolean containsKey(K key) throws IllegalArgumentException {
		if(key == null) {
			throw new IllegalArgumentException(ILLEGAL_ARG_NULL_KEY);
		}
		int index = getIndex(key);
		List<HashMapEntry<K, V>> bucket = buckets[index];
		try {
			for(HashMapEntry<K, V> entry : bucket) {
				if(entry.getKey().equals(key)) {
					return true;
				}
			}
		}catch(Exception e) {
			return false;
		}
		return false;
	}
	
	/**
	 * 
	 * @return an array containing the keys of this DefaultMap. If this DefaultMap is 
	 * empty, returns array of length zero. 
	 */
	@Override
	public List<K> keys() {
		List<K> keyList = new LinkedList<K>();
		try {
			for(List<HashMapEntry<K, V>> bucket : buckets) {
				if(bucket != null && !bucket.isEmpty()) {
					keyList.add(bucket.get(0).getKey());
				}
			}
		}catch(Exception e) {
			return keyList;
		}
		keyList.removeAll(Collections.singleton(null));
		return keyList;
	}
	
	/* Expands the capacity of the hash table if the load factor is exceeded.
	 * Expands the capacity by *2.
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void expandCapacity() {
		capacity *=2;
		List<HashMapEntry<K, V>>[] hashData = buckets.clone();
		buckets = (List<HashMapEntry<K, V>>[]) new List<?>[capacity];
		for(int i = 0; i < capacity; i++) {
			List<HashMapEntry<K, V>> bucket = new LinkedList<HashMapEntry<K, V>>();
			buckets[i] = bucket;
		}
		size = 0;
		for(int i = 0; i< hashData.length; i++) {
			if(hashData != null) {
				for(HashMapEntry<K, V> entry : hashData[i]) {
					if(entry != null) {
						put(entry.getKey(), entry.getValue());
					}
				}
			}
		}
	}
	
	/**
     * This is a helper method to get the index of a key
     * @param key is the key we want to get an index of
     * @return integer value of the index of the key
	 */
	private int getIndex(K key) {
		int index = (Objects.hashCode(key)) % capacity;
		if(index < 0) {
			return index * -1;
		}
		return index;
	}
	
	// Constructor for entries within our hashMap
	private static class HashMapEntry<K, V> implements DefaultMap.Entry<K, V> {
		
		K key;
		V value;
		
		private HashMapEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}
		
		@Override
		public void setValue(V value) {
			this.value = value;
		}
	}
}
