package maze.test;

import static org.junit.Assert.*;
import maze.logic.Board;
import maze.logic.Coordinate;

import org.junit.Test;

public class TestMazeMoving {

	public int[] input={3,3,3,3,3,1};
	
	@Test
	public void testMoveDragon() {
		Board board= new Board();
		board.createDfltBoard();
		assertTrue(board.moveDragon(0,3));
		assertEquals(new Coordinate(1,4), board.getDrgn(0).getCrds());
	}
	
	@Test
	public void testMoveDragonInvalid() {
		Board board= new Board();
		board.createDfltBoard();
		assertFalse(board.moveDragon(0,2));
		assertEquals(new Coordinate(1,3), board.getDrgn(0).getCrds());
	}

	@Test
	public void testMoveToSword() {
		Board board= new Board();
		board.createDfltBoard();
		for(int i=0;i<5;i++) {
			board.moveDragon(0,input[i]);
		}
		assertEquals(new Coordinate(1,8), board.getDrgn(0).getCrds());
		board.update();
		assertEquals('F', board.getDrgn(0).getChr());
		assertTrue(board.getDrgn(0).isOnSwrd());
	}
	
	@Test
	public void testMoveFromSword() {
		Board board= new Board();
		board.createDfltBoard();
		for(int i=0;i<5;i++) {
			board.moveDragon(0,input[i]);
		}
		board.update();
		board.moveDragon(0, input[5]);
		assertEquals(new Coordinate(1,7), board.getDrgn(0).getCrds());
		board.update();
		assertEquals('D', board.getDrgn(0).getChr());
		assertFalse(board.getDrgn(0).isOnSwrd());
	}
	
	@Test
	public void testFallAsleep() {
		Board board= new Board();
		board.createDfltBoard();
		assertTrue(board.getDrgn(0).isAwake());
		board.sleepWakeDragon(0);
		assertFalse(board.getDrgn(0).isAwake());
		board.update();
		assertEquals('d', board.getDrgn(0).getChr());
	}
	
	@Test
	public void testFallAsleepOnSword() {
		Board board= new Board();
		board.createDfltBoard();
		for(int i=0;i<5;i++) {
			board.moveDragon(0,input[i]);
		}
		assertEquals(new Coordinate(1,8), board.getDrgn(0).getCrds());
		board.update();
		board.sleepWakeDragon(0);
		board.update();
		assertEquals('f', board.getDrgn(0).getChr());
	}
	
	@Test
	public void testWakeUp() {
		Board board= new Board();
		board.createDfltBoard();
		board.sleepWakeDragon(0);
		board.update();
		board.sleepWakeDragon(0);
		board.update();
		assertTrue(board.getDrgn(0).isAwake());
		assertEquals('D', board.getDrgn(0).getChr());
	}
	
	@Test
	public void testWakeUpOnSword() {
		Board board= new Board();
		board.createDfltBoard();
		for(int i=0;i<5;i++) {
			board.moveDragon(0,input[i]);
		}
		board.update();
		board.sleepWakeDragon(0);
		board.update();
		board.sleepWakeDragon(0);
		board.update();
		assertTrue(board.getDrgn(0).isAwake());
		assertEquals('F', board.getDrgn(0).getChr());
	}
	
	@Test
	public void testHeroAliveWhenDragonAsleep() {
		Board board= new Board();
		board.createDfltBoard();
		board.sleepWakeDragon(0);
		board.update();
		board.moveHero("s");
		assertEquals(new Coordinate(1,2), board.getHero().getCrds());
		board.update();
		assertTrue(board.getHero().isAlive());
		assertFalse(board.moveHero("s"));
		assertEquals(new Coordinate(1,2), board.getHero().getCrds());
		board.moveHero("w");
		assertEquals(new Coordinate(1,1), board.getHero().getCrds());
		assertTrue(board.getHero().isAlive());
	}
}
