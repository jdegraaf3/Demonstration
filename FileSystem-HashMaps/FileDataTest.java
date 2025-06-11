/*
 * @author Justin DeGraaf
 * email: jdegraaf@ucsd.edu
 * 
 * Description: This class tests my FileData object to make sure it is correctly implemented.
 * 				I use junit to compare expected outputs with my toString method in FileData.java
 * 				to make sure my FileData objects are correct.
 */

import static org.junit.Assert.*;
import org.junit.*;

public class FileDataTest {
	FileData testFile1 = new FileData("name.txt", "/home", "05/14/2023");
	
	@Test
	public void testFileData(){
		assertEquals("{Name: name.txt, Directory: /home, Modified Date: 05/14/2023}", 
				testFile1.toString());
	}
}
