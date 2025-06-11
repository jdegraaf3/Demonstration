/*
 * @author Justin DeGraaf
 * email: jdegraaf@ucsd.edu
 * 
 * Description: This file tests to see if I implemented my FileSystem correctly. Using juint it
 * 				will see if each different map produces correct outputs when certain methods are
 * 				performed on each map.
 */

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;
import java.util.*;

public class FileSystemTest {
	
	FileSystem testSystem;
	
	@Before
	public void setup() {
		testSystem = new FileSystem("src/input.txt");
	}
	
	@Test
	public void testAddandFind() {
		
		testSystem.add("mySample.txt", "/home", "02/01/2021");
		
		FileData testFile1 = new FileData("mySample.txt", "/home", "02/01/2021");
		System.out.println(testSystem.findFile("mySample.txt", "/home").toString());
		System.out.println(testSystem.dateMap.keys().toString());
//		assertEquals(testSystem.findFile("mySample.txt", "/home").toString(), testFile1.toString());
	}
	
//	@Test
//	public void testFindFilesInMultDir() {
//		testSystem.add("mySample.txt", "/home", "02/01/2021");
//		testSystem.add("mySample2.txt", "/dif", "02/01/2021");
//		testSystem.add("mySample3.txt", "/dif2", "02/01/2021");
//		testSystem.add("mySample4.txt", "/home", "02/01/2021");
//		
//		testSystem.add("mySample.txt", "/home", "02/01/2021");
//		testSystem.add("mySample2.txt", "/dif", "02/01/2021");
//		testSystem.add("mySample3.txt", "/dif2", "02/01/2021");
//		testSystem.add("mySample4.txt", "/home", "02/01/2021");
//		
//		System.out.println(testSystem.findFilesInMultDir("02/01/2021").toArray().toString());
//			
//		
//	}
}
