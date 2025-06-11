import java.util.ArrayList;

/*
 * Write your JUnit tests here
 * Use the formatMaze() method to get nicer JUnit output
 */

import org.junit.Test;
import static org.junit.Assert.*;

public class TestSolvers {

	/* Helper method to compare two mazes */
	public void checkMaze(SearchWorklist wl, Maze startMaze, String[] expected) {
		Square s = MazeSolver.solve(startMaze, wl);
		if(expected == null) { assertNull(s); }
		else {
			ArrayList<Square> sp = startMaze.storePath();
			String actualStr = formatMaze(startMaze.showSolution(sp));
			String expectedStr = formatMaze(expected);
			assertEquals(expectedStr, actualStr);
		}
	}	

	/* Helper method to format String[] output as String */
	public String formatMaze(String[] arr) {
		String result = "";
		for (String s: arr)
			result += "\n"+s;
		return (result+"\n");
	}

	/* Add your own Worklist tests below */

	/* ************** HINT ******************** 
	 * Use the helper methods to create simple
	 * tests that are easier to debug. 
	 */
	
	public String solveMaze(SearchWorklist wl, Maze startMaze) {
		Square s = MazeSolver.solve(startMaze, wl);
		ArrayList<Square> sp = startMaze.storePath();
		return formatMaze(startMaze.showSolution(sp));
	}
	
	@Test
	public void testStackWorklist() {
		
		StackWorklist st = new StackWorklist();
		
		assertEquals(st.isEmpty(), true);
		
		Square s = new Square(5,5,false);
		
		st.add(s);
		assertEquals(st.isEmpty(), false);
		
		st.remove();
		assertEquals(st.isEmpty(), true);
	}
	
	@Test
	public void testQueueWorklist() {
		
		QueueWorklist q = new QueueWorklist();
		
		assertEquals(q.isEmpty(), true);
		
		Square s = new Square(5,5,false);
		
		q.add(s);
		assertEquals(q.isEmpty(), false);
		
		q.remove();
		assertEquals(q.isEmpty(), true);
	}

	@Test
	public void testsFromWriteUp() {
		
		Maze m = new Maze(new String[] {
	            "#___",
	            "__F_",
	            "S##_",
	            "____"});
		Maze m2 = new Maze(new String[] {
	            "#___",
	            "__F_",
	            "S##_",
	            "____"});
		
		String[] StackExpect =
			   {"#___",
				"__F*",
				"S##*",
				"****"};
		 
		String[] QueueExpect =
			    {"#___",
				 "**F_",
				 "S##_",
				 "____"};
	        
		QueueWorklist q = new QueueWorklist();
		StackWorklist s = new StackWorklist();
		
		checkMaze(s, m, StackExpect);
		checkMaze(q, m2, QueueExpect);
	}
	
	@Test
	public void testNoWalls() {
		
		Maze m = new Maze(new String[] {
	            "____",
	            "#_F_",
	            "S#__",
	            "#___"});
		Maze m2 = new Maze(new String[] {
	            "____",
	            "#_F_",
	            "S#__",
	            "#___"});
		
		String[] StackExpect = null;
		 
		String[] QueueExpect = null;
	        
		QueueWorklist q = new QueueWorklist();
		StackWorklist s = new StackWorklist();
		
		checkMaze(s, m, StackExpect);
		checkMaze(q, m2, QueueExpect);
	}
	
	@Test
	public void testDiffOrder() {
		
		Maze m = new Maze(new String[] {
	            "#___",
	            "__F_",
	            "S##_",
	            "____"});
	        
		
		StackWorklist s = new StackWorklist();
		QueueWorklist q = new QueueWorklist();
		
		
		assertEquals(solveMaze(s, m), solveMaze(s, m));
		assertEquals(solveMaze(q, m), solveMaze(q, m));
	}
	
	@Test 
	public void testAmountVisited() {
		
		Maze m = new Maze(new String[] {
	            "F___",
	            "____",
	            "____",
	            "S___"});
		
		StackWorklist s = new StackWorklist();
		QueueWorklist q = new QueueWorklist();
		
		MazeSolver.solve(m, s);
		MazeSolver.solve(m, q);
	}
}



