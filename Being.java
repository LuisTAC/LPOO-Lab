package game;

public class Being {
	private Coordinates crd;
	private char chr;
	private boolean alive = true;
	
	Being(char chr, int x, int y) {
		this.chr=chr;
		this.crd.setX(x);
		this.crd.setY(y);
	}

	public int getX() {
		return crd.getX();
	}
	public int getY() {
		return crd.getY();
	}
	public char getChr() {
		return chr;
	}
	public boolean getAlive()
	{
		return alive;
	}
	
	public void setChr(char chr) {
		this.chr = chr;
	}
	public void setCrnts(int x, int y)
	{
		this.crd.setX(x);
		this.crd.setY(y);
	}
	public void setAlive()
	{
		alive=false;
	}
	
 	public void moveDwn() {
		crd.setY(crd.getY()+1);
	}
	public void moveLft() {
		crd.setX(crd.getX()-1);
	}
	public void moveRght() {
		crd.setX(crd.getX()+1);
	}
	public void moveUp() {
		crd.setY(crd.getY()-1);
	}
	
}