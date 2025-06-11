/*
 * @author Justin DeGraaf
 * email: jdegraaf@ucsd.edu
 * 
 * Description: This file constructs a FileData object. It also contains a method to return the
 *              string representation of the FileData object, thats it!
 */

public class FileData {

    public String name;
    public String dir;
    public String lastModifiedDate;

    // TODO
    public FileData(String name, String directory, String modifiedDate) {
    	this.name = name;
    	this.dir = directory;
    	this.lastModifiedDate = modifiedDate;
    }

    // TODO
    public String toString() {
    	return "{Name: " + this.name + 
    			", Directory: " + this.dir + 
    			", Modified Date: " + this.lastModifiedDate + "}";
    }
    
    public boolean equalsFile(FileData compareTo) {
    	if (this.name.equals(compareTo.name) 
    			&& this.dir.equals(compareTo.dir)
    			&& this.lastModifiedDate.equals(compareTo.lastModifiedDate)){
    		return true;
    	}else {
    		return false;
    	}		
    }
}
