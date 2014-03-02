package game;

import java.util.Scanner;


public class Game {

	private static Board mainBoard;
	private static Board backupBoard;
	
	public static Scanner scanner = new Scanner( System.in );
	
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

	public static void makeAMove() {
		System.out.print("(2,4,6,8 to move; 0 to quit):");
		String input = Game.scanner.nextLine();
		System.out.println();
		if(!mainBoard.moveHero(input)) {
			System.out.println("Invalid input!");
			Game.scanner.nextLine();
		}
		else
		{
			if(mainBoard.getEndQ()) return;
			mainBoard.checkDragonGlobal();
			while(!mainBoard.moveDrgn()) {}
			mainBoard.checkDragonGlobal();
		}
	}
	
	public static void play()
	{
		mainBoard.printBoard();
		while(!mainBoard.getEndW() && !mainBoard.getEndL())
		{
			makeAMove();
			if(mainBoard.getEndQ()) break;
			mainBoard.printBoard();
		}
		if(mainBoard.getEndW()) System.out.println("You've found the exit!\n");
		else if(mainBoard.getEndQ()) {
			System.out.println("You've given up. Returning to main menu\n");
			return;
		}
		else if(mainBoard.getEndL()) {
			System.out.println("You're dead! Game Over!\nTry again?(y for yes or anything else for no):");
			String input = scanner.nextLine();
			if(input.equals("y")||input.equals("Y")) {
				mainBoard= new Board(backupBoard);
				play();
			}
		}
	}
	
	public static void dfltLvl() {
		
		mainBoard.createDfltBoard();
		play();
	}
	
	public static void rndmLvl() {
		
		System.out.print("Please select the size of the board you want to play in (odd number):");
		
		String input = scanner.nextLine();
		int size = Integer.parseInt(input);
		System.out.println();
		if(size%2==0)
		{
			size++;
			System.out.println("Not an odd number! Switching to "+size);
		}
		BoardBuilder builder = new BoardBuilder();
		builder.build(size);
		mainBoard = builder.getProduct();
		backupBoard = new Board(mainBoard);
		play();
	}

}