package game;

public class Eagle extends Piece {

	private boolean active = false, onLand = false, gotSword=false;
	private Coordinates coorHero;
	private Coordinates coorSwrd; 
	
	Eagle(int x, int y) {
		super('V', x, y);
	}
	
	Eagle(Piece piece) {
		super(piece);
	}

	public boolean getActive() {
		return active;
	}
	public boolean getOnLand() {
		return onLand;
	}
	public Coordinates getCoorHero() {
		return coorHero;
	}
	public boolean getGotSword() {
		return gotSword;
	}
	public Coordinates getCoorSwrd() {
		return coorSwrd;
	}
	public int modX(Coordinates coord)
	{
		return Math.abs(coord.getX()-this.getX());
	}
	public int modY(Coordinates coord)
	{
		return Math.abs(coord.getY()-this.getY());
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	public void setOnLand(boolean onLand) {
		this.onLand = onLand;
	}
	public void setCoorHero(Coordinates coorHero) {
		this.coorHero = coorHero;
	}
	public void setGotSword(boolean gotSword) {
		this.gotSword = gotSword;
	}
	public void setCoorSwrd(Coordinates coorSwrd) {
		this.coorSwrd = coorSwrd;
	}
	
	public void move2Sword() {
		if(modX(coorSwrd)>modY(coorSwrd))
		{
			if(coorSwrd.getX()>this.getX())
			{
				this.moveRght();
			}
			else if(coorSwrd.getX()<this.getX())
			{
				this.moveLft();
			}
		}
		else
		{
			if(coorSwrd.getY()>this.getY())
			{
				this.moveDwn();
			}
			else if(coorSwrd.getY()<this.getY())
			{
				this.moveUp();
			}
		}
	}
	public void move2Hero() {
		if(modX(coorHero)>modY(coorHero))
		{
			if(coorHero.getX()>this.getX())
			{
				this.moveRght();
			}
			else if(coorHero.getX()<this.getX())
			{
				this.moveLft();
			}
		}
		else
		{
			if(coorHero.getY()>this.getY())
			{
				this.moveDwn();
			}
			else if(coorHero.getY()<this.getY())
			{
				this.moveUp();
			}
		}
	}
	public void move()
	{
		if(gotSword) move2Hero();
		else move2Sword();
	}



	
}
