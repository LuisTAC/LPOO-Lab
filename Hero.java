package game;

public class Hero extends Piece{

	private boolean alive = true;
	private boolean hasSwrd = false;
	
	Hero(int x, int y) {
		super('H', x, y);
	}
	Hero(Hero hero) {
		super('H',hero.getX(),hero.getY());
	}
	
	public boolean getAlive()
	{
		return alive;
	}
	public boolean getHasSwrd() {
		return hasSwrd;
	}

	public void setAlive(boolean alive)
	{
		this.alive=alive;
	}
	public void setHasSwrd() {
		this.hasSwrd = true;
	}

}
