package maze.test;

import static org.junit.Assert.*;

import maze.logic.Board;

import org.junit.Test;

public class TestMazeEagle {

	@Test
	public void testNullEagle() {
		Board board= new Board();
		board.createDfltBoard();
		assertNull(board.getEagle());
	}
	
	//TODO ...
}
