public class ArrayGL<E> implements MyList<E> {

    E[] elements;
    int size;

    public ArrayGL(E[] initialElements) {
        this.elements = initialElements;
        this.size = initialElements.length;
    }

    // Fill in all required methods here
    // I collaborated with Zach Miller
    
    void add(E e) {
    	if (this.size > this.elements.length - 1) {
			expandCapacity();
		}
		this.elements[this.size] = e;
		size+=1;
    }
    
    int size() {
    	return this.size;
    }
    
 	E get(int index) {
 		if (index < this.elements.length
 			&& index >= 0) {
 			return this.elements[index];
 		}
 		return null;
 	}
    
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
  	
  	void remove(int index) {
  		for(int i = index; i < this.size - 1; i++){
  	      this.elements[i] = this.elements[i + 1];
  	    }
  		  
  	    this.elements[size] = null;
  	    this.size -= 1;
  		  
  	    return;
  	  }

  	@SuppressWarnings("unchecked")
	void expandCapacity() {
		E[] temp = (E[]) new Object[this.elements.length * 2];
		for (int i = 0; i < this.elements.length; i++) {
			temp[i] = this.elements[i];
		}
		this.elements = temp;
	}
	
  	int getCapacity() {
  		return this.elements.length;
  	}
  	
  	@SuppressWarnings("unchecked")
    public E[] toArray() {
    	E[] copy = (E[])(new Object[this.size]);
    	for(int i = 0; i < this.size; i +=1) {
    		copy[i] = elements[i];
    	}
    	return copy;
    }
    
  	@SuppressWarnings("unchecked")
    public void transformAll(MyTransformer mt) {
    	for(int i = 0; i < this.size; i +=1) {
    		elements[i] = (E) mt.transformElement(elements[i]);
    	}
	}
  	
  	
    
  	@SuppressWarnings("unchecked")
    public void chooseAll(MyChooser mc) {
  		int newLength = 0;
  		for (E i : this.elements) {
  			if(mc.chooseElement(i) == true && i != null) {
  				newLength++;
  			}
  		}
  		E[] newList = (E[])(new Object[newLength]);
  		int itemCount = 0;
  		for (E i: this.elements) {
  			if (mc.chooseElement(i) == true && i != null) {
  				newList[itemCount] = i;
  				itemCount++;
  			}
  		}
  		this.size = itemCount;
  		this.elements = newList;
	}
    
  
	public boolean isEmpty() {
		if (this.size == 0) {
			return true;
		}
		return false;
	}
}