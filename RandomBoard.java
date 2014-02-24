package game;

import java.util.Random;
import java.util.Stack;

public class RandomBoard extends Board {

	private char[][] visitedCells;
	private Stack pathHistory;
	private Coordinates guideCell;
	public Random seed = new Random();
	
	
	public boolean pathDone()
	{
		return pathHistory.isEmpty();
	}
	
	public void fillBoard() //FILLS BOARD WITH 'X' and ' '
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
	
	public void createExit()
	{
		int borderSelect = seed.nextInt(4);
		int cell=seed.nextInt(dim-2)+1;
		int guideX,guideY;
		switch (borderSelect)
		{
		case 0: //left border
			guideX = 0;
			guideY=cell;
			tab[guideY][guideX]='S';
			guideX++;
			break;
		case 1: //right border
			guideX = dim-2;
			guideY=cell;
			tab[guideY][guideX]='S';
			guideX--;
			break;
		case 2: //up border
			guideY = 0;
			guideX=cell;
			tab[guideY][guideX]='S';
			guideY++;
			break;
		case 3: //down border
			guideY = dim-2;
			guideX=cell;
			tab[guideY][guideX]='S';
			guideY--;
			break;
		}
		
		visitedCells[guideY][guideX]='+';
		
		guideCell = new Coordinates(guideX,guideY);
		pathHistory = new Stack<Coordinates>();
		pathHistory.<Coordinates>push(guideCell);
	}
	
public void createRndmBoard(int dim) {
		
		setDim(dim);
		tab=new char[dim][dim];
		fillBoard();
		fillVisitedCells();
		createExit();
		while(!pathDone())
		{
			int dir = seed.nextInt(4);
			switch(dir)
			{
			case 0: //left
				//TODO <---------------------
				break;
			case 1: //right
				break;
			case 2: //up
				break;
			case 3: //down
				break;
				}
		}
	}
	
}
