package game;

import java.util.Scanner;


public class Game {

	public static Scanner scanner = new Scanner( System.in );
	
	public static void main(String[] args) {
		while(true) {
			System.out.print("Select an option:\n[1]Default Level\n[2]Random Level\n[0]Exit\n>");
			String input = scanner.nextLine();
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

	public static void dfltLvl() {
		
		Board jogo = new Board();
		jogo.createDfltBoard();
		jogo.printBoard();
		while(!jogo.getEndW() && !jogo.getEndL())
		{
			System.out.println();
			jogo.makeAMove();
			jogo.printBoard();
		}
		if(jogo.getEndW()) System.out.println("You've found the exit!\n");
		else if(jogo.getEndL()) {
			System.out.println("You're dead! Game Over!\nTry again?(y for yes or anything else for no)");
			String input = scanner.nextLine();
			if(input.equals("y")||input.equals("Y")) dfltLvl();
		}
	}
	
	public static void rndmLvl() {
		
		System.out.println("You have selected the random level! \n");
		System.out.println("Please select the dimension of the board you pretend to play in: \n");
		
		String input = scanner.nextLine();
		int dimension = Integer.parseInt(input);
	}

}