package game;

import java.util.Random;
import java.util.Stack;

public class Board{

	protected int dim=10;
	protected char[][] tab;
	private Hero hero;
	private Dragon drgn;
	private boolean endW=false, endL=false;
	
	public boolean getEndW() {
		return endW;
	}
	public boolean getEndL() {
		return endL;
	}
	public Being getHero() {
		return hero;
	}
	public Being getDrgn() {
		return drgn;
	}

	public void setDim(int dim) {
		this.dim = dim;
	}
	
	public boolean atExit(int x, int y) {
		if(tab[y][x] == 'S')
		{
			return true;
		}
		return false;
	}
	public void atSwrd()
	{
		if(tab[hero.getY()][hero.getX()] == 'E')
		{
			hero.setChr('A');
			tab[hero.getY()][hero.getX()] = ' ';
			hero.setHasSwrd();
		}
	}
	public boolean atDragon()
	{
		if(hero.getX()-1==drgn.getX() && hero.getY()==drgn.getY())
		{
			return true;
		}
		if(hero.getX()+1==drgn.getX() && hero.getY()==drgn.getY())
		{
			return true;
		}
		if(hero.getX()==drgn.getX() && hero.getY()-1==drgn.getY())
		{
			return true;
		}
		if(hero.getX()==drgn.getX() && hero.getY()+1==drgn.getY())
		{
			return true;
		}
		else return false;
			

	}
	
	public void createDfltBoard() {
		tab = new char[][]
				{{'X','X','X','X','X','X','X','X','X','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ','X','X',' ','X',' ','X',' ','X'},
                {'X',' ','X','X',' ','X',' ','X',' ','X'},
                {'X',' ','X','X',' ','X',' ','X',' ','X'},
                {'X',' ',' ',' ',' ',' ',' ','X',' ','S'},
                {'X',' ','X','X',' ','X',' ','X',' ','X'},
                {'X',' ','X','X',' ','X',' ','X',' ','X'},
                {'X','E','X','X',' ',' ',' ',' ',' ','X'},
                {'X','X','X','X','X','X','X','X','X','X'}};
		hero = new Hero('H',1,1);
		drgn = new Dragon('D',1,3);
	}
	
	
	public void printBoard() {
		
		for(int i=0; i<dim; i++)
		{
			for(int j=0; j<dim; j++)
			{
				if(drgn.getAlive() && drgn.getX()==j&&drgn.getY()==i) System.out.print(drgn.getChr());
				else if(hero.getAlive() && hero.getX()==j && hero.getY()==i) System.out.print(hero.getChr());
				else System.out.print(tab[i][j]);
			}
			System.out.println();
		}
		
	}

	public void makeAMove() {
		System.out.print("(2,4,6,8 to move):");
		String input = Game.scanner.nextLine();
		if(!moveHero(input)) {
			System.out.println("Invalid input!\n");
		}
		else
		{
			while(!moveDrgn())
			{}
			if(atDragon())
			{
				if(!hero.getHasSwrd())
				{
					endL=true;
					hero.setAlive();
				}
				else if(hero.getHasSwrd())
				{
					drgn.setAlive();
				}
			}
			else if(drgn.getAlive()) 
			{
				//COND TO MAKE DRAGON = 'F' WHEN ON TOP OF SWORD
				if(!drgn.getOnSwrd() && tab[drgn.getY()][drgn.getX()]=='E')
				{
					drgn.setOnSwrd(true);
					drgn.setChr('F');
				}
				else if(drgn.getOnSwrd() && tab[drgn.getY()][drgn.getX()]!= 'E')
				{
					drgn.setOnSwrd(false);
					drgn.setChr('D');
				}

			}
		}
		

	}
	public boolean moveHero(String dir) {
		switch(dir) {
		case "2":
			if(tab[hero.getY()+1][hero.getX()] != 'X')
			{
				if(atExit(hero.getX(),hero.getY()+1))
				{
					if (hero.getHasSwrd())
					{
						endW = true;
						hero.moveDwn();
						return true;
					}
					return false;
				}
				hero.moveDwn();
				atSwrd();
				return true;
			}
			return false;
			
		case "4":
			if(tab[hero.getY()][hero.getX()-1] != 'X')
			{
				if(atExit(hero.getX()-1,hero.getY()))
				{
					if (hero.getHasSwrd())
					{
						endW = true;
						hero.moveLft();
						return true;
					}
					return false;
				}
				hero.moveLft();
				atSwrd();
				return true;
			}
			return false;
		case "6":
			if(tab[hero.getY()][hero.getX()+1] != 'X')
			{
				if(atExit(hero.getX()+1,hero.getY()))
				{
					if (hero.getHasSwrd())
					{
						endW=true;
						hero.moveRght();
						return true;
					}
					return false;
				}
				hero.moveRght();
				atSwrd();
				return true;
			}
			return false;
		case "8":
			if(tab[hero.getY()-1][hero.getX()] != 'X')
			{
				if(atExit(hero.getX(),hero.getY()-1))
				{
					if (hero.getHasSwrd())
					{
						endW=true;
						hero.moveUp();
						return true;
					}
					return false;
				}
				hero.moveUp();
				atSwrd();
				return true;
			}
			return false;
		default: return false;
		}
	}
	public boolean moveDrgn()
	{
		Random dirGen = new Random();
		int num = dirGen.nextInt(5);
		
		switch(num)
		{
		case 0:
			return true;
		case 1:
			if(tab[drgn.getY()][drgn.getX()-1] != 'X')
			{
				drgn.moveLft();
				return true;
			}
			return false;
		case 2:
			if(tab[drgn.getY()][drgn.getX()+1] != 'X')
			{
				drgn.moveRght();
				return true;
			}
			return false;
		case 3:
			if(tab[drgn.getY()-1][drgn.getX()] != 'X')
			{
				drgn.moveUp();
				return true;
			}
			return false;
		case 4:
			if(tab[drgn.getY()+1][drgn.getX()] != 'X')
			{
				drgn.moveDwn();
				return true;
			}
			return false;
			default: return false;
		}
	}
}


	