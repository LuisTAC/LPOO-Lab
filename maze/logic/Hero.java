package maze.logic;

public class Hero extends Piece {
	
	private boolean alive=true;
	private boolean hasSwrd=false;
	
	public Hero(Coordinate crds)
	{
		super(crds, 'H');
	}
	public Hero(Hero hero)
	{
		super(new Coordinate(hero.getCrds()), hero.getChr());
		this.setAlive(hero.isAlive());
		this.setHasSwrd(hero.hasSwrd());
	}
	
	public boolean isAlive()
	{
		return alive;
	}
	public boolean hasSwrd()
	{
		return hasSwrd;
	}
	
	public void setAlive(boolean alive)
	{
		this.alive = alive;
	}
	public void setHasSwrd(boolean hasSwrd)
	{
		this.hasSwrd = hasSwrd;
	}
}
