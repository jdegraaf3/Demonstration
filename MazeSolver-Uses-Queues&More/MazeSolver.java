/* Class to implement a Maze solver */

public abstract class MazeSolver {
	
	public static Square solve(Maze maze, SearchWorklist wl) {
		/* Complete this method */
		
		while(wl.isEmpty() == false) {
			wl.remove();
		}
		
		//int visitCount = 0;
		maze.start.visit();
		wl.add(maze.start);
		
		while(wl.isEmpty() != true) {
			Square current = wl.remove();
			current.visit();
			if(current == maze.finish) {
				//System.out.print(visitCount+ "\n");
				return current;
			}
			
			//going North
			if ((current.getRow()-1) >= 0) {
				if((maze.contents[current.getRow()-1][current.getCol()].isVisited() != true)
					&& (maze.contents[current.getRow()-1][current.getCol()].getIsWall() != true)) {
					
					maze.contents[current.getRow()-1][current.getCol()].setPrevious(current);
					maze.contents[current.getRow()-1][current.getCol()].visit();
					//visitCount +=1;
					wl.add(maze.contents[current.getRow()-1][current.getCol()]);
					
				}
			}
			
			//going South
			if ((current.getRow()+1) < maze.rows) {
				if((maze.contents[current.getRow()+1][current.getCol()].isVisited() != true)
					&& (maze.contents[current.getRow()+1][current.getCol()].getIsWall() != true)) {
				
					maze.contents[current.getRow()+1][current.getCol()].setPrevious(current);
					maze.contents[current.getRow()+1][current.getCol()].visit();
					//visitCount +=1;
					wl.add(maze.contents[current.getRow()+1][current.getCol()]);
				}
			}
			
			//going East
			if ((current.getCol()+1) < maze.cols) {
				if((maze.contents[current.getRow()][current.getCol()+1].isVisited() != true)
					&& (maze.contents[current.getRow()][current.getCol()+1].getIsWall() != true)) {
				
					maze.contents[current.getRow()][current.getCol()+1].setPrevious(current);
					maze.contents[current.getRow()][current.getCol()+1].visit();
					//visitCount +=1;
					wl.add(maze.contents[current.getRow()][current.getCol()+1]);
				}
			}
			
			//going West
			if ((current.getCol()-1) >= 0){
				if((maze.contents[current.getRow()][current.getCol()-1].isVisited() != true)
					&& (maze.contents[current.getRow()][current.getCol()-1].getIsWall() != true)) {
				
					maze.contents[current.getRow()][current.getCol()-1].setPrevious(current);
					maze.contents[current.getRow()][current.getCol()-1].visit();
					//visitCount +=1;
					wl.add(maze.contents[current.getRow()][current.getCol()-1]);
				}
			}
			
		}
		return null;
	}

	/* Add any helper methods you want */
}
