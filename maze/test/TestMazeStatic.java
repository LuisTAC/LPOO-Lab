package maze.test;

import static org.junit.Assert.*;
import org.junit.Test;

import maze.logic.Board;
import maze.logic.Coordinate;

public class TestMazeStatic {
	
	public String[] input={"d","d","d", "s", "s", "s", "s", "a", "a", "a", "s", "s", "s", "w", "w", "w", "w", "w", "w", "w", "d","d","d", "d","d","d", "d", "s", "s", "s", "s", "d"};
	public String[] input2={"d","d","d","d","d","d","d", "s", "s", "s", "s", "d"};
	
	@Test
	public void testMoveHero() {
		Board board= new Board();
		board.createDfltBoard();
		assertTrue(board.moveHero("d"));
		assertEquals(new Coordinate(2,1), board.getHero().getCrds());
	}
	
	@Test
	public void testMoveHeroInvalid() {
		Board board= new Board();
		board.createDfltBoard();
		assertFalse(board.moveHero("a"));
		assertEquals(new Coordinate(1,1), board.getHero().getCrds());
	}

	@Test
	public void testGetSword() {
		Board board= new Board();
		board.createDfltBoard(); //hero@ 1,1
		for(int i=0; i<13; i++)
		{
			board.moveHero(input[i]);
		}
		assertEquals(new Coordinate(1,8), board.getHero().getCrds());
		board.update();
		assertTrue(board.getHero().hasSwrd());
		assertEquals(board.getHero().getChr(),'A');
	}
	
	@Test
	public void testKillDragon() {
		Board board= new Board();
		board.createDfltBoard(); //hero@ 1,1
		for(int i=0; i<13; i++)
		{
			board.moveHero(input[i]);
		}
		board.update();
		for(int i=13; i<18; i++)
		{
			board.moveHero(input[i]);
		}
		assertEquals(new Coordinate(1,4), board.getHero().getCrds());
		board.update();
		assertFalse(board.getDrgns()[0].isAlive());
	}

	@Test
	public void testGetOut() {
		Board board= new Board();
		board.createDfltBoard();
		for(int i=0; i<13; i++)
		{
			board.moveHero(input[i]);
		}
		board.update();
		for(int i=13; i<17; i++)
		{
			board.moveHero(input[i]);
		}
		board.update();
		for(int i=17; i<input.length; i++)
		{
			board.moveHero(input[i]);
		}
		assertTrue(board.exitAt(board.getHero().getCrds()));
		board.update();
		assertTrue(board.getEndW());
	}
	
	@Test
	public void testGetOutInvalid() {
		
		Board board=new Board();
		board.createDfltBoard();
		for(int i=0; i<input2.length-1; i++)
		{
			board.moveHero(input2[i]);
		}
		assertEquals(new Coordinate(8,5), board.getHero().getCrds());
		assertFalse(board.moveHero(input2[input2.length-1]));
	}
}
