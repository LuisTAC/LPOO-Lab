package game;

public class Piece{
	private Coordinates crd;
	private char chr;
	
	Piece(char chr, int x, int y) {
		this.chr = chr;
		crd = new Coordinates(x,y);
	}
	Piece(Piece piece) {
		this.chr = piece.getChr();
		this.crd = new Coordinates(piece.getCrdnts());
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
	public Coordinates getCrdnts() {
		return crd;
	}
	
	public void setChr(char chr) {
		this.chr = chr;
	}
	public void setCrnts(int x, int y)
	{
		this.crd.setX(x);
		this.crd.setY(y);
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