package maze.cli;

import java.util.Random;
import java.util.Scanner;

import maze.logic.Board;
import maze.logic.BoardBuilder;

public class Game {

	private static Board mainBoard = new Board();
	// private static Board backupBoard; // TODO final -> retry
	public static Scanner scanner = new Scanner( System.in );
	public static Random seed = new Random();
	
	public static void main(String[] args) {
		while(true) {
			System.out.print("Select an option:\n[1]Default Level\n[2]Random Level\n[0]Exit\n>");
			String input = scanner.nextLine();
			System.out.println();
			switch(input) {
			case "1":dfltLvl();
				break;
			case "2":rndmLvl();
				break;
			case "0": return;
			default:
				System.out.println("Invalid input!");
				break;
			}
		}
	}
	
	public static void rndmLvl() {
		System.out.print("Please select the size of the board you want to play in (odd number, >=10):");
		String input = scanner.nextLine();
		int size = Integer.parseInt(input);
		System.out.println();
		if(size<10) { //TODO [TESTING] 
			System.out.println(size + " < 10. Switching to 11\n");
			size=11;
		}
		if(size%2==0) {
			size++;
			System.out.println("Not an odd number! Switching to "+size + "\n");
		}
		BoardBuilder builder = new BoardBuilder();
		builder.build(size);
		mainBoard = builder.getProduct();
		// backupBoard = new Board(mainBoard);// TODO final -> retry
		System.out.println("Please select the type of dragon movement you want: ");
		System.out.println("[1]Static dragon\n[2]Random moving dragon\n[3]Mix moving dragon");
		while(true) {
			input = scanner.nextLine();
			if(input.equals("1") || input.equals("2") || input.equals("3")) break;
			else
			{
				System.out.println("Invalid input!\n");
			}
		}
		play(input);
	}
	public static void dfltLvl() {
		mainBoard.createDfltBoard();
		playMovingDragon();
	}
	
	public static void play(String type) {
		switch(type) {
		case "1":
			playStaticDragon();
			break;
		case "2":
			playMovingDragon();
			break;
		case "3":
			playMixDragon();
			break;
		}
	}
	
	public static String getDirection() { //ASKS THE USER FOR A DIRECTION TO MOVE THE HERO AND RETURNS IT
		System.out.print("(w,a,s,d to move; q to quit):");
		String input = scanner.nextLine();
		System.out.println();
		return input;
	}
	
	//methods for static dragon
	
	public static boolean makeAMoveStaticDragon() {
		String input=getDirection();
		if(!mainBoard.moveHero(input)) {
			System.out.println("Invalid input!");
			scanner.nextLine();
			return false;
		}
		return true;
	}
	public static void playStaticDragon() {
		mainBoard.print();
		System.out.println();
		while(!mainBoard.getEndL() && !mainBoard.getEndQ() && !mainBoard.getEndW()) {
			if(makeAMoveStaticDragon()) {
				if(mainBoard.getEndQ()) break;
				mainBoard.moveEagle();
				mainBoard.update();
			}
			mainBoard.print();
			System.out.println();
		}
		endGame();
	}
	
	//methods for moving dragon
	
	public static void moveDragons() {
		for(int i=0;i<mainBoard.getDrgns().length; i++) {
			while(!mainBoard.moveDragon(i)){}
		}
	}
	
	public static boolean makeAMoveMovingDragon() {
		String input=getDirection();
		if(!mainBoard.moveHero(input)) {
			System.out.println("Invalid input!");
			scanner.nextLine();
			return false;
		}
		else {
			mainBoard.update();
			if(mainBoard.getEndL() || mainBoard.getEndQ() || mainBoard.getEndW()) return true;
			moveDragons();
			return true;
		}
	}	
	public static void playMovingDragon() {
		mainBoard.print();
		System.out.println();
		while(!mainBoard.getEndL() && !mainBoard.getEndQ() && !mainBoard.getEndW()) {
			if(makeAMoveMovingDragon()) {
				if(mainBoard.getEndQ()) break;
				mainBoard.moveEagle();
				mainBoard.update();
			}
			mainBoard.print();
			System.out.println();
		}
		endGame();
	}
	
	//methods for mix dragon
	
	public static boolean makeAMoveMixDragon() {
		String input=getDirection();
		if(!mainBoard.moveHero(input)) {
			System.out.println("Invalid input!");
			scanner.nextLine();
			return false;
		}
		else {
			mainBoard.update();
			if(mainBoard.getEndL() || mainBoard.getEndQ() || mainBoard.getEndW()) return true;
			moveDragons();
			mainBoard.sleepWakeDragons();
			return true;
		}
	}
	public static void playMixDragon() {
		mainBoard.print();
		System.out.println();
		while(!mainBoard.getEndL() && !mainBoard.getEndQ() && !mainBoard.getEndW()) {
			if(makeAMoveMixDragon()) {
				if(mainBoard.getEndQ()) break;
				mainBoard.moveEagle();
				mainBoard.update();
			}
			mainBoard.print();
			System.out.println();
		}
		endGame();
	}
	
	public static void endGame() {
		if(mainBoard.getEndL()) {
			System.out.println("You're DEAD! Game Over\n");
		}
		else if(mainBoard.getEndQ()) {
			System.out.println("You have given up. Returning to main menu\n");
		}
		else if(mainBoard.getEndW()) {
			System.out.println("You WON!\n");
		}
	}
	
}