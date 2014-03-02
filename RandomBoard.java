package game;

import java.util.Random;
import java.util.Stack;

public class RandomBoard extends Board {

	private char[][] visitedCells;
	private Stack<Coordinates> pathHistory;
	private int guideX, guideY;
	public Random seed = new Random();
	
	public boolean pathDone()
	{
		return pathHistory.isEmpty();
	}
	
	public void fillBoard() //FILLS BOARD WITH 'X' AND ' '
	{
		for(int i=0; i<dim; i++)
		{
			for(int j=0; j<dim; j++)
			{
				if(i%2 != 0 && j%2!=0)
				{
					tab[i][j] = ' ';
				}
				else tab[i][j] = 'X';
			}
		}
	}
	
	public void fillVisitedCells() //CREATES AND FILLS THE VISITED CELLS ARRAY
	{
		visitedCells = new char[(dim-1)/2][(dim-1)/2];
		
		for(int i=0; i<visitedCells.length;i++)
		{
			for(int j=0; j<visitedCells.length;j++)
			{
				visitedCells[i][j]='.';
			}
		}
	}
		
	public boolean checkVisitedCells(int x,int y) //TRUE IF A GIVEN CELL HAS NOWHERE TO GO TO
	{
		if(x!=0) {
			if(visitedCells[y][x-1]!='+') return false; //left
		}
		if(x!=((dim-1)/2)-1) {
			if(visitedCells[y][x+1]!='+') return false; //right
		}
		if(y!=0) {
			if(visitedCells[y-1][x]!='+') return false; //up
		}
		if(y!=((dim-1)/2)-1) {
			if(visitedCells[y+1][x]!='+') return false; //down
		}
		return true;
	}
	
/*	public void printVisitedCells() //TODO SÓ PARA TESTES! APAGAR!
	{
		System.out.println(" 01234567890");
		for(int i=0;i<visitedCells.length;i++)
		{
			System.out.print(i);
			for(int j=0;j<visitedCells[i].length;j++)
			{
				System.out.print(visitedCells[i][j]);
			}
			System.out.println();	
		}
	}
*/
	
 	public void createExit() //CREATES THE EXIT IN A RANDOM BORDER AND THE "GUIDECELL" NEXT TO IT
	{
		int borderSelect = seed.nextInt(4);
		int cell=seed.nextInt((dim-1)/2);
		switch (borderSelect)
		{
		case 0: //left border
			guideX = 0;
			guideY=cell;
			tab[guideY*2+1][guideX]='S';
			break;
		case 1: //right border
			guideX = ((dim-1)/2)-1;
			guideY=cell;
			tab[guideY*2+1][guideX*2+2]='S';
			break;
		case 2: //up border
			guideY = 0;
			guideX=cell;
			tab[guideY][guideX*2+1]='S';
			break;
		case 3: //down border
			guideY = ((dim-1)/2)-1;
			guideX=cell;
			tab[guideY*2+2][guideX*2+1]='S';
			break;
		default:
			guideX=guideY=1;
			break;
		}
		
		visitedCells[guideY][guideX]='+';
		
		pathHistory = new Stack<Coordinates>();
		pathHistory.<Coordinates>push(new Coordinates(guideX,guideY));
	}
	
	public boolean goLeft()
	{
		if(visitedCells[guideY][guideX-1]!='+')
		{
			tab[(guideY*2)+1][(guideX*2)+1-1]=' ';
			guideX--;
			visitedCells[guideY][guideX]='+';
			pathHistory.push(new Coordinates(guideX,guideY));
			return true;
		}
		return false;
	}
	public boolean goRight()
	{
		if(visitedCells[guideY][guideX+1]!='+')
		{
			tab[(guideY*2)+1][(guideX*2)+1+1]=' ';
			guideX++;
			visitedCells[guideY][guideX]='+';
			pathHistory.push(new Coordinates(guideX,guideY));
			return true;
		}
		return false;
	}
	public boolean goUp()
	{
		if(visitedCells[guideY-1][guideX]!='+')
		{
			tab[(guideY*2)+1-1][(guideX*2)+1]=' ';
			guideY--;
			visitedCells[guideY][guideX]='+';
			pathHistory.push(new Coordinates(guideX,guideY));
			return true;
		}
		return false;
	}
	public boolean goDown()
	{
		if(visitedCells[guideY+1][guideX]!='+')
		{
			tab[(guideY*2)+1+1][(guideX*2)+1]=' ';
			guideY++;
			visitedCells[guideY][guideX]='+';
			pathHistory.push(new Coordinates(guideX,guideY));
			return true;
		}
		return false;
	}
	
	public boolean createHero()
	{
		int x = seed.nextInt(dim-2)+1; //[1,dim-2]
		int y = seed.nextInt(dim-2)+1;
		if(tab[y][x]==' ')
		{
			hero = new Hero(x,y);
			return true;
		}
		return false;
	}
	public boolean createDragon()
	{
		int x = seed.nextInt(dim-2)+1; //[1,dim-2]
		int y = seed.nextInt(dim-2)+1;
		if(tab[y][x]==' ' && (x!=hero.getX() || y!=hero.getY()))
		{
			drgn = new Dragon(x,y);
			if(atDragon()) return false;
			return true;
		}
		return false;
	}	
	public boolean createSword()
	{
		int x = seed.nextInt(dim-2)+1; //[1,dim-2]
		int y = seed.nextInt(dim-2)+1;
		if(tab[y][x]==' ' && (x!=hero.getX() || y!=hero.getY())
				&& (x!=drgn.getX() || y!=drgn.getY()))
		{
			tab[y][x]='E';
			return true;
		}
		return false;
	}
	public void createRndmBoard(int dim) {
		
		setDim(dim);
		tab=new char[dim][dim];
		fillBoard();
		fillVisitedCells();
		createExit();
		while(!pathDone())
		{
			/*//TODO SÓ PARA TESTES! APAGAR
			System.out.println("X: "+guideX+" Y: "+guideY);
			printVisitedCells();
			System.out.println();
			printBoard();
			System.out.println();
			//^*/
			if(!checkVisitedCells(guideX,guideY))
			{
				int dir = seed.nextInt(4);
				switch(dir)
				{
				case 0: //left
					if(guideX!=0)
					{
						if(!goLeft()) continue;
					}
					break;
				case 1: //right
					if(guideX!=((dim-1)/2)-1)
					{
						if(!goRight()) continue;
					}
					break;
				case 2: //up
					if(guideY!=0)
					{
						if(!goUp()) continue;
					}
					break;
				case 3: //down
					if(guideY!=((dim-1)/2)-1)
					{
						if(!goDown()) continue;
					}
					break;
				}
			}
			else
			{
				guideX=pathHistory.peek().getX();
				guideY=pathHistory.peek().getY();
				pathHistory.pop();
			}
		}
		while(!createHero()){}
		while(!createDragon()){}
		while(!createSword()){}
	}
}
