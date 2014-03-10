package game;

import java.util.Scanner;
import java.util.Random;


public class Game {

	private static Board mainBoard;
	private static Board backupBoard;
	public static Random seed = new Random();
	
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

	public static void makeAMoveMovingDragon() {
		System.out.print("(w,a,s,d to move; q to quit):");
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
			if(mainBoard.drgns[0].getAlive()) {
				while(!mainBoard.moveDrgn(0)) {}
				mainBoard.checkDragonGlobal();
			}
		}
	}
	
	public static void makeAMoveStaticDragon() {
		System.out.print("(w,a,s,d to move; q to quit):");
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
		}
	}
	
	public static void makeAMoveMixDragon() {

		System.out.print("(w,a,s,d to move; q to quit):");
		String input = Game.scanner.nextLine();
		System.out.println();
		if(!mainBoard.moveHero(input)) {
			System.out.println("Invalid input!");
			Game.scanner.nextLine();
		}
		else
		{
			for(int i =0;i<mainBoard.drgns.length;i++)
			{
				mainBoard.checkDragonGlobal();
				boolean changeDragon = seed.nextBoolean();
				if(changeDragon)
				{
					if(mainBoard.drgns[i].getAwake())
					{
						mainBoard.drgns[i].setAwake(false);
					}
					else mainBoard.drgns[i].setAwake(true);
				}
				if(mainBoard.getEndQ()) return;
				if(mainBoard.drgns[i].getAlive())
				{
					if(mainBoard.drgns[i].getAwake())
					{
						while(!mainBoard.moveDrgn(i)) {}
						mainBoard.checkDragonGlobal();
					}	
				}
			}
		}
	}
	
	public static void play(String drgnType)
	{
		mainBoard.printBoard();
		while(!mainBoard.getEndW() && !mainBoard.getEndL())
		{
			switch(drgnType)
			{
			case "1":
				makeAMoveStaticDragon();
				break;
			case "2":
				makeAMoveMovingDragon();
				break;
			case "3":
				makeAMoveMixDragon();
				break;
			}
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
				play(drgnType);
			}
		}
	}
	
	public static void dfltLvl() {
		
		mainBoard.createDfltBoard();
		play("2");
	}
	
	public static void rndmLvl() {
		
		System.out.print("Please select the size of the board you want to play in (odd number, >=10):");
		
		String input = scanner.nextLine();
		int size = Integer.parseInt(input);
		System.out.println();
		if(size<10)
		{
			System.out.println(size + " < 10. Switching to 11");
			size=11;
		}
		if(size%2==0)
		{
			size++;
			System.out.println("Not an odd number! Switching to "+size);
		}
		BoardBuilder builder = new BoardBuilder();
		builder.build(size);
		mainBoard = builder.getProduct();
		backupBoard = new Board(mainBoard);
		System.out.println("Please select the type of dragon movement you want: ");
		System.out.println("[1]Static dragon\n[2]Random moving dragon\n[3]Mix moving dragon");
		while(true)
		{
			input = scanner.nextLine();
			if(input.equals("1") || input.equals("2") || input.equals("3")) break;
			else
			{
				System.out.println("Invalid input!");
			}
		}
		play(input);
	}

}