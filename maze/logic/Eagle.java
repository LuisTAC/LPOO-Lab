package maze.logic;


public class Eagle extends Piece {

	private boolean alive = true,
			onLand = false,
			gotSword=false,
			returned=false;
	private Coordinate coorHero;
	private Coordinate coorSwrd; 
	
	//Constructors	
	
	Eagle(Coordinate crds) {
		super(crds, 'V');
	}
	Eagle(Eagle eagle) {
		super(new Coordinate(eagle.getCrds()),eagle.getChr());
		this.setAlive(eagle.isAlive());
		this.setOnLand(eagle.isOnLand());
		this.setGotSword(eagle.gotSword());
		this.setReturned(eagle.isReturned());
		this.setCoorHero(new Coordinate(eagle.getCoorHero()));
		this.setCoorSwrd(new Coordinate(eagle.getCoorSwrd()));
	}
	
	//Non-modifying methods
	
	public boolean isAlive() {
		return alive;
	}
	public boolean isOnLand() {
		return onLand;
	}
	public boolean gotSword() {
		return gotSword;
	}
	public boolean isReturned() {
		return returned;
	}
	public Coordinate getCoorHero() {
		return coorHero;
	}
	public Coordinate getCoorSwrd() {
		return coorSwrd;
	}
	public int modX(Coordinate crds)
	{
		return Math.abs(crds.getX()-this.getX());
	}
	public int modY(Coordinate crds)
	{
		return Math.abs(crds.getY()-this.getY());
	}
	
	public boolean isAtSword() { //RETURNS TRUE IF EAGLE HAS ARRIVED AT SWORD
		return this.getCrds().equals(coorSwrd);
	}
	public boolean isAtHero() { //RETURNS TRUE IF EAGLE HAS ARRIVED AT SWORD
		return this.getCrds().equals(coorHero);
	}
	
	//Modifying methods
	
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	public void setOnLand(boolean onLand) {
		this.onLand = onLand;
	}
	public void setGotSword(boolean gotSword) {
		this.gotSword = gotSword;
	}
	public void setReturned(boolean returned) {
		this.returned = returned;
	}
	public void setCoorHero(Coordinate coorHero) {
		this.coorHero = coorHero;
	}
	public void setCoorSwrd(Coordinate coorSwrd) {
		this.coorSwrd = coorSwrd;
	}
	
		//Eagle movement
	
	public void move2Sword()
	{
		if(modX(coorSwrd)<modY(coorSwrd))
		{
			if(coorSwrd.getY()>this.getCrds().getY())
			{
				this.moveDown();
			}
			else if(coorSwrd.getY()<this.getCrds().getY())
			{
				this.moveUp();
			}
		}
		else if(coorSwrd.getX()>this.getCrds().getX())
		{
			this.moveRight();
		}
		else if(coorSwrd.getX()<this.getCrds().getX())
		{
			this.moveLeft();
		}
	}
	public void move2Hero()
	{
		if(modX(coorHero)>modY(coorHero))
		{
			if(coorHero.getX()>this.getCrds().getX())
			{
				this.moveRight();
			}
			else if(coorHero.getX()<this.getCrds().getX())
			{
				this.moveLeft();
			}
		}
		else if(coorHero.getY()>this.getCrds().getY())
		{
			this.moveDown();
		}
		else if(coorHero.getY()<this.getCrds().getY())
		{
			this.moveUp();
		}
	}
	public void move()
	{
		if(gotSword)
		{
			this.move2Hero();
		}
		else this.move2Sword();
	}
	
}
