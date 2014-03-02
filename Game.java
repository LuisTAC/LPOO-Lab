package game;

import java.util.Scanner;


public class Game {

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

	public static void play(Board jogo)
	{
		jogo.printBoard();
		while(!jogo.getEndW() && !jogo.getEndL())
		{
			jogo.makeAMove();
			if(jogo.getEndQ()) break;
			jogo.printBoard();
		}
		if(jogo.getEndW()) System.out.println("You've found the exit!\n");
		else if(jogo.getEndQ()) {
			System.out.println("You've given up. Returning to main menu\n");
			return;
		}
		else if(jogo.getEndL()) {
			System.out.println("You're dead! Game Over!\nTry again?(y for yes or anything else for no)");
			String input = scanner.nextLine();
			if(input.equals("y")||input.equals("Y")) dfltLvl();
		}
	}
	
	public static void dfltLvl() {
		
		Board jogo = new Board();
		jogo.createDfltBoard();
		play(jogo);
	}
	
	public static void rndmLvl() {
		
		System.out.print("Please select the size of the board you want to play in (odd number):");
		
		String input = scanner.nextLine();
		int size = Integer.parseInt(input);
		if(size%2==0)
		{
			size++;
			System.out.println("Not an odd number! Switching to "+size);
		}
		RandomBoard jogo= new RandomBoard();
		jogo.createRndmBoard(size);
		play(jogo);
	}

}