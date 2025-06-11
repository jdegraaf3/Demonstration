public class LinkedGL<E> implements MyList<E> {

    class Node {
        E value;
        Node next;

        public Node(E value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    Node front;
    int size;
	

    public LinkedGL(E[] contents) {
        // Fill in this constructor
    	this.front = new Node(null, null);
    	this.size = 0;
    	for(E e : contents) {
    		add(e);
    	}
    }

    // Fill in all methods
    // I collaborated with Zach Miller
    
    void add(E e) {
    	Node temp = this.front;
    	while(temp.next != null) {
    		temp = temp.next;
    	}
    	temp.next = new Node (e, null);
    	size += 1;
    }
    
     void prepend(E e) {
    	 this.front.next = new Node(e, this.front.next);
     }
     
 	Node get(int index) {
 		Node temp = this.front.next;
 		for(int i = 0; i < index; i += 1) {
 			temp = temp.next;
 		}
 		return temp;
 	}
   /* 
  	void insert(int index, E e) {
  		if(this.size >= this.elements.length) {
  	      expandCapacity();
  	    }
  	    for(int i = this.size; i > index; i--){
  	      this.elements[i] = this.elements[i - 1];
  	    }
  	    this.elements[index] = e; // why must this come after loop?
  	    this.size += 1;
  		  
  	    return;
  	}
  	*/
  	
  	void remove(int index) {
  		
  		if(index > 0) {
  			get(index-1).next = get(index+1);
  		}
  		else {
  			this.front.next = get(index+1);
  		}
  		size -= 1;
  		
  	  }

  	
	/*void expandCapacity() {
		E[] temp = new E[this.elements.length * 2];
		for (int i = 0; i < this.elements.length; i++) {
			temp[i] = this.elements[i];
		}
		this.elements = temp;
	}
	
  	int getCapacity() {
  		return this.elements.length;
  	}
  	*/
  	
  	
  	@SuppressWarnings("unchecked")
  	public E[] toArray() {
  		E[] copy = (E[])(new Object[this.size]);
  		for(int i = 0; i < this.size; i+=1) {
  			copy[i] = this.get(i).value;
  		}
  		return copy;
    }
    
  	@SuppressWarnings("unchecked")
    public void transformAll(MyTransformer mt) {
    	Node temp = this.front;
    	while (temp.next != null) {
    		temp = temp.next;
    		temp.value = (E)mt.transformElement(temp.value);
    	}
    }
    
  	@SuppressWarnings("unchecked")
    public void chooseAll(MyChooser mc) {
  	
  		for(int i = 0; i < this.size; i++) {
  			if(mc.chooseElement(this.get(i).value) == false || this.get(i).value == null) {
  				//if (this.size > 1) {
  					this.remove(i);
  					i -= 1;
  					
  				
  			}
  		}
  	}
    
    public boolean isEmpty() {
		if (this.size == 0) {
			return true;
		}
		return false;
	}

}