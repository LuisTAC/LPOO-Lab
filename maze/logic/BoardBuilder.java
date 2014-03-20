package maze.logic;

import java.util.Stack;

import maze.cli.Game;

public class BoardBuilder {

	private Board prod = new Board();
	private char[][] visitedCells;
	private Stack<Coordinate> pathHistory;
	private int guideX, guideY;

	public Board getProduct()
	{
		return prod;
	}
	public boolean pathDone()
	{
		return pathHistory.isEmpty();
	}
	
	public void fillBoard() //FILLS BOARD WITH 'X' AND ' '
	{
		for(int i=0; i<prod.dim; i++)
		{
			for(int j=0; j<prod.dim; j++)
			{
				if(i%2 != 0 && j%2!=0)
				{
					prod.cells[i][j] = ' ';
				}
				else prod.cells[i][j] = 'X';
			}
		}
	}
	public void fillVisitedCells() //CREATES AND FILLS THE VISITED CELLS ARRAY
	{
		visitedCells = new char[(prod.dim-1)/2][(prod.dim-1)/2];
		
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
		if(x!=((prod.dim-1)/2)-1) {
			if(visitedCells[y][x+1]!='+') return false; //right
		}
		if(y!=0) {
			if(visitedCells[y-1][x]!='+') return false; //up
		}
		if(y!=((prod.dim-1)/2)-1) {
			if(visitedCells[y+1][x]!='+') return false; //down
		}
		return true;
	}
	/*public void fillBlankBoard() //TODO [TESTING]
	{
		for(int i=1;i<prod.dim-1;i++)
		{
			for(int j=1;j<prod.dim-1;j++)
			{
				prod.cells[i][j]=' ';
			}
		}
	}*/

  	public void createExit() //CREATES THE EXIT IN A RANDOM BORDER AND THE "GUIDECELL" NEXT TO IT
	{
		int borderSelect = Game.seed.nextInt(4);
		int cell=Game.seed.nextInt((prod.dim-1)/2);
		switch (borderSelect)
		{
		case 0: //left border
			guideX = 0;
			guideY=cell;
			prod.cells[guideY*2+1][guideX]='S';
			break;
		case 1: //right border
			guideX = ((prod.dim-1)/2)-1;
			guideY=cell;
			prod.cells[guideY*2+1][guideX*2+2]='S';
			break;
		case 2: //up border
			guideY = 0;
			guideX=cell;
			prod.cells[guideY][guideX*2+1]='S';
			break;
		case 3: //down border
			guideY = ((prod.dim-1)/2)-1;
			guideX=cell;
			prod.cells[guideY*2+2][guideX*2+1]='S';
			break;
		default:
			guideX=guideY=1;
			break;
		}
		
		visitedCells[guideY][guideX]='+';
		
		pathHistory = new Stack<Coordinate>();
		pathHistory.<Coordinate>push(new Coordinate(guideX,guideY));
	}
	
	public boolean goLeft()
	{
		if(visitedCells[guideY][guideX-1]!='+')
		{
			prod.cells[(guideY*2)+1][(guideX*2)+1-1]=' ';
			guideX--;
			visitedCells[guideY][guideX]='+';
			pathHistory.push(new Coordinate(guideX,guideY));
			return true;
		}
		return false;
	}
	public boolean goRight()
	{
		if(visitedCells[guideY][guideX+1]!='+')
		{
			prod.cells[(guideY*2)+1][(guideX*2)+1+1]=' ';
			guideX++;
			visitedCells[guideY][guideX]='+';
			pathHistory.push(new Coordinate(guideX,guideY));
			return true;
		}
		return false;
	}
	public boolean goUp()
	{
		if(visitedCells[guideY-1][guideX]!='+')
		{
			prod.cells[(guideY*2)+1-1][(guideX*2)+1]=' ';
			guideY--;
			visitedCells[guideY][guideX]='+';
			pathHistory.push(new Coordinate(guideX,guideY));
			return true;
		}
		return false;
	}
	public boolean goDown()
	{
		if(visitedCells[guideY+1][guideX]!='+')
		{
			prod.cells[(guideY*2)+1+1][(guideX*2)+1]=' ';
			guideY++;
			visitedCells[guideY][guideX]='+';
			pathHistory.push(new Coordinate(guideX,guideY));
			return true;
		}
		return false;
	}
	
	public boolean createHero()
	{
		int x = Game.seed.nextInt(prod.dim-2)+1; //[1,dim-2]
		int y = Game.seed.nextInt(prod.dim-2)+1;
		if(prod.cells[y][x]==' ')
		{
			prod.hero = new Hero(new Coordinate(x,y));
			return true;
		}
		return false;
	}
	public boolean createDragon(int i)	{
		int x = Game.seed.nextInt(prod.dim-2)+1; //[1,dim-2]
		int y = Game.seed.nextInt(prod.dim-2)+1;
		if(prod.cells[y][x]==' ' && (x!=prod.hero.getX() || y!=prod.hero.getY())
				&& (x!=prod.swrd.getX() || y!=prod.swrd.getY()))
		{
			for(int j=0;j<i;j++) {
				if(prod.dragonAt(i,new Coordinate(x,y))!=-1) return false;
			}
			prod.drgns[i] = new Dragon(new Coordinate(x,y));
			if(prod.checkDragons(prod.checkDragonsAt(i+1, prod.hero.getCrds()))) return false;
			return true;
		}
		return false;
	}	
	public boolean createSword()
	{
		int x = Game.seed.nextInt(prod.dim-2)+1; //[1,dim-2]
		int y = Game.seed.nextInt(prod.dim-2)+1;
		if(prod.cells[y][x]==' ' && (x!=prod.hero.getX() || y!=prod.hero.getY()))
		{
			prod.swrd = new Sword(new Coordinate(x,y));
			return true;
		}
		return false;
	}
		
	public void build(int dim) {
		
		prod.setDim(dim);
		prod.cells=new char[dim][dim];
		fillBoard();
		fillVisitedCells();
		createExit();
		while(!pathDone())
		{
			if(!checkVisitedCells(guideX,guideY))
			{
				int dir = Game.seed.nextInt(4);
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
		//fillBlankBoard();//TODO [TESTING]
		while(!createHero()){}
		while(!createSword()){}
		prod.drgns=new Dragon[(prod.dim/10)*2]; //TODO [TESTING] 
		for(int i =0;i<prod.drgns.length;i++)
		{
			while(!createDragon(i)){}
		}
	}
	
}
