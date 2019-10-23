import java.util.Stack;
import java.util.ArrayList;
import java.util.*;
/*
          Algorithm

1] Initialize an empty stack for storing the unexplored tiles, and In a list to store the Tiles in the path
2] Initialize 2D boolean array "visited" 
3] push the "Beginign Tile" into the stack, and add it the path List
4] While the current tile is not the "Ending Tile":
5] 		while the stack is not empty:
6] pop the first tile in the stack
7] if the tile is not visited and its not a wall:
8]		then visit the 
9]		for each unvisited neighbor of the current tile :
			if the unvisited neighbor  is not a wall:
10]			push the unvisited neighbor into the stack
11]End the process when we find the "Ending Tile"
*/

public class DepthFirstSearchPathFinder implements Pathfinder{
    // memeber fields
	private Maze maze;
	private int  height ;
	private int  width ;
	private Tile start ;
	private Tile end  ;

	public DepthFirstSearchPathFinder(Maze maze){
		this.maze = maze;		 
		this.height =  2 * maze.height +1 ;
		this.width =  2 * maze.width +1 ;
		this.start = maze.getStart(); 
		this.end = maze.getEnd();
	}


	public List<Tile> findPath(){
		List<Tile> path = new ArrayList<Tile>();
		boolean[][] visited = new boolean[width][height];
		Stack<Tile> stack = new Stack<Tile>();
		Tile currentTile ;

		// step 3]  add the "Beginign Tile"
		path.add(start);
		stack.push(start);
	    int[][] SHIFTS = {
		    {0 , 2}, // going right
		    {2, 0}, // going down
		    {0, -2}, // going left
		    {-2, 0} // going up`
		  };

		//step 4] & 5]
		currentTile = start;
		while(currentTile != end){
			while(!stack.isEmpty()){
				currentTile = stack.pop();
				if(!visited[currentTile.x][currentTile.y] && !currentTile.isWall()){
					visited[currentTile.x][currentTile.y] = true;
					//step 9 
					for (int[] shift : SHIFTS) {
						if (canTraverse(currentTile, currentTile.x +shift[0],currentTile.x +shift[1])){
							Tile neighbor = maze.tiles[currentTile.x +shift[0]][currentTile.x + shift[1]];
							stack.push(neighbor);
							path.add(neighbor);
						}


					}
				}
		}
	}
  }

  /*
  Helper function
  Validates a given tile, it ensures it is in the maze and ican
  be "walked on"
*/
	private boolean canTraverse(Tile tile, int x, int y) {
	    return x >= 0 && x < width &&
	  		   y >= 0 && y < height &&
			   !tile.isWall();	
	}
}