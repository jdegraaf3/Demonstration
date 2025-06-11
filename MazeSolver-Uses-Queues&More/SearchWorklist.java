/*
 * Class to implement SearchWorklist as a Stack and a Queue.
 * You can use any built-in Java collections for this class.
 */

import java.util.*;

class StackWorklist implements SearchWorklist {
	
	ArrayList<Square> squareQueue = new ArrayList<Square>();
	
	public void add(Square c) {
		squareQueue.add(c);
	}
	
	public Square remove() {
		return squareQueue.remove(squareQueue.size()-1);
	}
	
	public boolean isEmpty() {
		return squareQueue.isEmpty();
	}
	
}

class QueueWorklist implements SearchWorklist {
	
	ArrayList<Square> squareQueue = new ArrayList<Square>();
	
	public void add(Square c) {
		squareQueue.add(0, c);
	}
	
	public Square remove() {
		return squareQueue.remove(squareQueue.size()-1);
	}
	
	public boolean isEmpty() {
		return squareQueue.isEmpty();
	}

}

public interface SearchWorklist {
	void add(Square c);
	Square remove();
	boolean isEmpty();
}
