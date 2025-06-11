
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection; 
import java.util.NoSuchElementException;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class TestLists {

	public static Collection<Object[]> LISTNUMS =
			Arrays.asList(new Object[][] { {"Linked"}, {"Array"} });
	private String listType;

	public TestLists(String listType) {
		super();
		this.listType = listType;
	}

	@Parameterized.Parameters(name = "{0}List")
	public static Collection<Object[]> bags() {
		return LISTNUMS;
	}

	private <E> MyList<E> makeList(E[] contents) {
		switch (this.listType) {
		case "Linked":
			return new LinkedGL<E>(contents);
		case "Array":
			return new ArrayGL<E>(contents);
		}
		return null;
	}

  // Don't change code above this line, it ensures the autograder works as
  // expected


  // I collaborated with Zach Miller
	
  // This is a sample test; you can keep it, change it, or remove it as you like.
  // Note that it uses the method `assertArrayEquals`, which you should use to
  // test equality of arrays in this PA.
	@Test
	public void testSimpleToArray() {
		// Using the generic list to create an Integer list
		Integer[] int_input = {1, 2, 3};
		MyList<Integer> int_s = makeList(int_input);
		assertArrayEquals(int_input, int_s.toArray());
		
		// Using the generic list to create a String list
		String[] string_input = {"a", "b", "c"};
		MyList<String> string_s = makeList(string_input);
		assertArrayEquals(string_input, string_s.toArray());
	}
		
	@Test
	public void testToArray() {
		Integer[] int_input = {1,2,null,3};
		MyList<Integer> int_s = makeList(int_input);
		assertArrayEquals(int_input, int_s.toArray());
		
		String[] string_input = {"a", "b", null, "c"};
		MyList<String> string_s = makeList(string_input);
		assertArrayEquals(string_input, string_s.toArray());
		
		Integer[] int_input2 = {};
		MyList<Integer> int_a = makeList(int_input2);
		Integer[] output = {};
		assertArrayEquals(output, int_a.toArray());
		
		String[] str_input2 = {};
		MyList<String> str_a = makeList(str_input2);
		String[] output2 = {};
		assertArrayEquals(output2, str_a.toArray());
	}
	
	
	@Test
	public void testMultiplyby2() {
		Integer[] int_input = {1, 2, 3};
		Integer[] int_output = {2, 4, 6};
		MyList<Integer> int_s = makeList(int_input);
		int_s.transformAll(new MultiplyBy2());
		assertArrayEquals(int_output, int_s.toArray());
		
		Integer[] int_input2 = {};
		Integer[] int_output2 = {};
		MyList<Integer> int_a = makeList(int_input2);
		int_s.transformAll(new MultiplyBy2());
		assertArrayEquals(int_output2, int_a.toArray());
	}
	
	@Test
	public void testMakeSameLetter() {
		String[] string_input = {"a", "b", "c"};
		MyList<String> string_s = makeList(string_input);
		String[] str_output = {"b", "b", "c"};
		string_s.transformAll(new MakeSameLetter());
		assertArrayEquals(str_output, string_s.toArray());
		
		String[] string_input2 = {};
		MyList<String> string_a = makeList(string_input2);
		String[] str_output2 = {};
		string_a.transformAll(new MakeSameLetter());
		assertArrayEquals(str_output2, string_a.toArray());
	}
	
	@Test
	public void testFavNumberChooser() {
		Integer[] int_input = {1, 2, 3};
		Integer[] int_output = {3};
		MyList<Integer> int_s = makeList(int_input);
		int_s.chooseAll(new FavNumberChooser());
		assertArrayEquals(int_output, int_s.toArray());
		
		Integer[] int_input2 = {};
		Integer[] int_output2 = {};
		MyList<Integer> int_a = makeList(int_input2);
		int_a.transformAll(new MultiplyBy2());
		assertArrayEquals(int_output2, int_a.toArray());
	}
	
	@Test
	public void testChooserAndTransformer() {
		Integer[] int_input = {1, 2, 3};
		Integer[] int_output = {6};
		MyList<Integer> int_s = makeList(int_input);
		int_s.chooseAll(new FavNumberChooser());
		int_s.transformAll(new MultiplyBy2());
		assertArrayEquals(int_output, int_s.toArray());
	}
	
	@Test
	public void testIsEmpty() {
		String[] string_input = {null,null,null};
		MyList<String> string_s = makeList(string_input);
		assertEquals(false, string_s.isEmpty());
		
		Integer[] int_input = {1, 2, 3};
		MyList<Integer> int_s = makeList(int_input);
		assertEquals(false, int_s.isEmpty());
		
		Integer[] int_input2 = {};
		MyList<Integer> int_a = makeList(int_input2);
		assertEquals(true, int_a.isEmpty());
	}
	
	@Test
	public void fixedSize() {
		Integer int_input[] = new Integer[10];
		int_input[0] = 10;
		MyList<Integer> int_a = makeList(int_input);
		assertArrayEquals(int_input, int_a.toArray());
	}
	
	
}