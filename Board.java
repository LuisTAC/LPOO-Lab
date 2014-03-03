package game;

public class Board{

	protected int dim=10;
	protected char[][] tab;
	protected Hero hero;
	protected Dragon drgn;
	protected Sword swrd;
	private boolean endW=false, endL=false, endQ=false;
	
	Board() {}
	Board(Board board)
	{
		this.dim = board.dim;
		this.tab = board.tab.clone();
		hero = new Hero(board.hero);
		drgn = new Dragon(board.drgn);
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
	public Dragon getDrgn() {
		return drgn;
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
	public void setDragon(Dragon dragon) {
		this.drgn=dragon;
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
	
	public void checkDragonGlobal()
	{
		if(atDragon())
		{
			if(!hero.getHasSwrd() && drgn.getAwake())
			{
				endL=true;
				hero.setAlive(false);
			}
			else if(hero.getHasSwrd())
			{
				drgn.setAlive(false);
			}
		}
		else if(drgn.getAlive()) 
		{
			//COND TO MAKE DRAGON = 'F' WHEN ON TOP OF SWORD
			if(!drgn.getOnSwrd() && drgn.getY()==swrd.getY() && drgn.getX()==swrd.getX())
			{
				drgn.setOnSwrd(true);
				drgn.setChr('F');
			}
			else if(drgn.getOnSwrd() && (drgn.getY()!=swrd.getY() || drgn.getX()!=swrd.getX()))
			{
				drgn.setOnSwrd(false);
				drgn.setChr('D');
			}
		}
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
		drgn = new Dragon(1,3);
		swrd = new Sword(1,8);
	}
		
	public void printBoard() {
		
		for(int i=0; i<dim; i++)
		{
			for(int j=0; j<dim; j++)
			{
				if(drgn.getAlive() && drgn.getX()==j&&drgn.getY()==i) System.out.print(drgn.getChr());
				else if(hero.getAlive() && hero.getX()==j && hero.getY()==i) System.out.print(hero.getChr());
				else if(!hero.getHasSwrd() && swrd.getX()==j && swrd.getY()==i) System.out.print(swrd.getChr());
				else System.out.print(tab[i][j]);
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
			if(tab[hero.getY()+1][hero.getX()] != 'X' && (hero.getY()+1!=drgn.getY() || hero.getX()!=drgn.getX()))
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
			if(tab[hero.getY()][hero.getX()-1] != 'X' && (hero.getY()!=drgn.getY() || hero.getX()-1!=drgn.getX()))
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
			if(tab[hero.getY()][hero.getX()+1] != 'X' && (hero.getY()!=drgn.getY() || hero.getX()+1!=drgn.getX()))
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
			if(tab[hero.getY()-1][hero.getX()] != 'X' && (hero.getY()-1!=drgn.getY() || hero.getX()!=drgn.getX()))
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
		int num = Game.seed.nextInt(5);
		
		switch(num)
		{
		case 0:
			return true;
		case 1:
			if(tab[drgn.getY()][drgn.getX()-1] != 'X' && tab[drgn.getY()][drgn.getX()-1] != 'S')
			{
				drgn.moveLft();
				return true;
			}
			return false;
		case 2:
			if(tab[drgn.getY()][drgn.getX()+1] != 'X' && tab[drgn.getY()][drgn.getX()+1] != 'S')
			{
				drgn.moveRght();
				return true;
			}
			return false;
		case 3:
			if(tab[drgn.getY()-1][drgn.getX()] != 'X' && tab[drgn.getY()-1][drgn.getX()] != 'S')
			{
				drgn.moveUp();
				return true;
			}
			return false;
		case 4:
			if(tab[drgn.getY()+1][drgn.getX()] != 'X' && tab[drgn.getY()+1][drgn.getX()] != 'S')
			{
				drgn.moveDwn();
				return true;
			}
			return false;
			default: return false;
		}
	}

}


	