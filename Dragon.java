package game;

public class Dragon extends Piece{

	private boolean alive = true;
	private boolean awake = true;
	private boolean onSwrd = false;
	
	Dragon(int x, int y) {
		super('D', x, y);
	}
	Dragon(Dragon drg) {
		super(drg.getChr(),drg.getX(),drg.getY());
	}
	
	public boolean getAlive()
	{
		return alive;
	}
	public boolean getOnSwrd() {
		return onSwrd;
	}
	public boolean getAwake() {
		return awake;
	}
	public void setAlive(boolean alive)
	{
		if(!alive) this.setCrnts(-1,-1);
		this.alive=alive;
	}
	public void setOnSwrd(boolean onSwrd) {
		this.onSwrd = onSwrd;
	}
	public void setAwake(boolean awake) {
		if(!awake)
		{
			setChr(Character.toLowerCase(getChr()));
		}
		else setChr(Character.toUpperCase(getChr()));
		
		this.awake = awake;

	}

}
