package game;

public class Board{

	protected int dim=10;
	protected char[][] tab;
	protected Hero hero;
	protected Dragon[] drgns;
	protected Sword swrd;
	private boolean endW=false, endL=false, endQ=false;
	
	Board() {}
	Board(Board board)
	{
		this.dim = board.dim;
		this.tab = board.tab.clone();
		hero = new Hero(board.hero);
		this.setDragons(board.drgns);
		swrd = new Sword(board.swrd);
		this.endW = board.getEndW();
		this.endL = board.getEndL();
		this.endQ = board.getEndQ();
	}
	
	public boolean getEndW() {
		return endW;
	}
	public boolean getEndL() {
		return endL;
	}
	public boolean getEndQ() {
		return endQ;
	}
	public Hero getHero() {
		return hero;
	}
	public Dragon[] getDrgns() {
		return drgns;
	}
	public Sword getSword() {
		return swrd;
	}
	public char[][] getTab() {
		return tab;
	}
	
	public void setDim(int dim) {
		this.dim = dim;
	}
	public void setHero(Hero hero) {
		this.hero=hero;
	}
	public void setDragons(Dragon[] dragons) {
		drgns = new Dragon[dragons.length];
		for(int i = 0;i<drgns.length;i++)
		{
			drgns[i]= new Dragon(dragons[i]);
		}
	}
	public void setSword(Sword sword) {
		this.swrd=sword;
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
		if(hero.getY()==swrd.getY() && hero.getX()==swrd.getX())
		{
			hero.setChr('A');
			hero.setHasSwrd();
		}
	}
	public int[] atDragon(int maxInd) //RETURNS AN ARRAY WITH INDEX OF DRAGONS NEAR HERO; IF NONE RETURNS AN ARRAY FULL OF -1
	{
		int[] res={-1,-1,-1,-1};
		int ind=0;
		for(int i=0;i<maxInd;i++)
		{			
			if(hero.getX()-1==drgns[i].getX() && hero.getY()==drgns[i].getY())
			{
				res[ind]=i;
				ind++;
			}
			if(hero.getX()+1==drgns[i].getX() && hero.getY()==drgns[i].getY())
			{
				res[ind]=i;
				ind++;
			}
			if(hero.getX()==drgns[i].getX() && hero.getY()-1==drgns[i].getY())
			{
				res[ind]=i;
				ind++;
			}
			if(hero.getX()==drgns[i].getX() && hero.getY()+1==drgns[i].getY())
			{
				res[ind]=i;
				ind++;
			}
		}
		return res;
	}
	public boolean checkAtDragon(int[] drgs) //RETURNS TRUE IF THERE'S ANY DRAGON NEAR HERO
	{
		for(int i=0;i<drgs.length;i++)
		{
			if(drgs[i]!=-1) return true;
		}
		return false;
	}
	public boolean checkAwakeDragons(int[] drgs) //RETURNS TRUE IF THERE?S ANY AWAKE DRAGON NEAR HERO
	{
		for(int i=0;i<drgs.length;i++)
		{
			if(drgs[i]!=-1)
			{
				if(drgns[drgs[i]].getAwake()) return true;
			}
			else return false;
		}
		return false;
	}
	public void killDragons(int[] drgs)
	{
		for(int i=0;i<drgs.length;i++)
		{
			if(drgs[i]!=-1) drgns[drgs[i]].setAlive(false);
			else break;
		}
	}
	
	public void checkDragonGlobal()
	{
		int[] indDrgns=atDragon(drgns.length);
		if(checkAtDragon(indDrgns))
		{
			if(!hero.getHasSwrd() && checkAwakeDragons(indDrgns))
			{
				endL=true;
				hero.setAlive(false);
			}
			else if(hero.getHasSwrd())
			{
				killDragons(indDrgns);
			}
		}
		for(int i=0;i<drgns.length;i++)
		{
			if(drgns[i].getAlive()) 
			{
				//COND TO MAKE DRAGON = 'F' WHEN ON TOP OF SWORD
				if(!drgns[i].getOnSwrd() && drgns[i].getY()==swrd.getY() && drgns[i].getX()==swrd.getX())
				{
					if(!hero.getHasSwrd())
					{
						drgns[i].setOnSwrd(true);
						drgns[i].setChr('F');
					}
				}
				else if(drgns[i].getOnSwrd() && (drgns[i].getY()!=swrd.getY() || drgns[i].getX()!=swrd.getX()))
				{
					drgns[i].setOnSwrd(false);
					drgns[i].setChr('D');
				}
			}
		}
	}
	public boolean checkDragonAt(int x, int y) //CHECKS IF THERE'S A DRAGON AT GIVEN COORDINATES
	{
		for(int i =0;i<drgns.length;i++)
		{
			if(drgns[i].getX()==x && drgns[i].getY() ==y) return true;
		}
		return false;
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
                {'X',' ','X','X',' ',' ',' ',' ',' ','X'},
                {'X','X','X','X','X','X','X','X','X','X'}};
		hero = new Hero(1,1);
		drgns = new Dragon[1];
		drgns[0]= new Dragon(1,3);
		swrd = new Sword(1,8);
	}
		
	public void printBoard() {
		
		for(int i=0; i<dim; i++)
		{
			for(int j=0; j<dim; j++)
			{
				boolean drgPrint=false;
				for(int k=0;k<drgns.length;k++)
				{
					if(drgns[k].getAlive() && drgns[k].getX()==j&&drgns[k].getY()==i)
					{
						System.out.print(drgns[k].getChr());
						drgPrint=true;
						break;
					}
				}
				if(hero.getAlive() && hero.getX()==j && hero.getY()==i) System.out.print(hero.getChr());
				else if(!drgPrint && !hero.getHasSwrd() && swrd.getX()==j && swrd.getY()==i) System.out.print(swrd.getChr());
				else if(!drgPrint)System.out.print(tab[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		
	}

	public boolean moveHero(String dir) {
		switch(dir) {
		case "0":
			endQ=true;
			return true;
		case "2":
			if(tab[hero.getY()+1][hero.getX()] != 'X')
			{
				for(int i=0;i<drgns.length;i++)
				{
					if (hero.getY()+1==drgns[i].getY() && hero.getX()==drgns[i].getX())
					{
						if(drgns[i].getAlive())
							return false;
					}
				}
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
				for(int i=0;i<drgns.length;i++)
				{
					if (hero.getY()==drgns[i].getY() && hero.getX()-1==drgns[i].getX())
					{
						if(drgns[i].getAlive())
							return false;
					}
				}
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
				for(int i=0;i<drgns.length;i++)
				{
					if (hero.getY()==drgns[i].getY() && hero.getX()+1==drgns[i].getX())
					{
						if(drgns[i].getAlive())
							return false;
					}
				}
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
				for(int i=0;i<drgns.length;i++)
				{
					if (hero.getY()-1==drgns[i].getY() && hero.getX()==drgns[i].getX())
					{
						if(drgns[i].getAlive())
							return false;
					}
				}
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
	public boolean moveDrgn(int i)
	{
		int num = Game.seed.nextInt(5);
		
		switch(num)
		{
		case 0:
			return true;
		case 1:
			if(tab[drgns[i].getY()][drgns[i].getX()-1] != 'X' && tab[drgns[i].getY()][drgns[i].getX()-1] != 'S')
			{
				if(checkDragonAt(drgns[i].getX()-1,drgns[i].getY())) return false;
				drgns[i].moveLft();
				return true;
			}
			return false;
		case 2:
			if(tab[drgns[i].getY()][drgns[i].getX()+1] != 'X' && tab[drgns[i].getY()][drgns[i].getX()+1] != 'S')
			{
				if(checkDragonAt(drgns[i].getX()+1,drgns[i].getY())) return false;
				drgns[i].moveRght();
				return true;
			}
			return false;
		case 3:
			if(tab[drgns[i].getY()-1][drgns[i].getX()] != 'X' && tab[drgns[i].getY()-1][drgns[i].getX()] != 'S')
			{
				if(checkDragonAt(drgns[i].getX(),drgns[i].getY()-1)) return false;
				drgns[i].moveUp();
				return true;
			}
			return false;
		case 4:
			if(tab[drgns[i].getY()+1][drgns[i].getX()] != 'X' && tab[drgns[i].getY()+1][drgns[i].getX()] != 'S')
			{
				if(checkDragonAt(drgns[i].getX(),drgns[i].getY()+1)) return false;
				drgns[i].moveDwn();
				return true;
			}
			return false;
			default: return false;
		}
	}

}


	