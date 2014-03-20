package maze.logic;

public class Piece {

	private Coordinate crds;
	private char chr;
	
	public Piece(Coordinate crds,char chr)
	{
		this.setCrds(crds);
		this.setChr(chr);
	}

	public Coordinate getCrds()
	{
		return crds;
	}
	public char getChr()
	{
		return chr;
	}
	public int getX() {
		return this.getCrds().getX();
	}
	public int getY() {
		return this.getCrds().getY();
	}
		
	public void setCrds(Coordinate crds)
	{
		this.crds = crds;
	}
	public void setChr(char chr)
	{
		this.chr = chr;
	}
	
	public void moveUp()
	{
		this.setCrds(crds.getUp());
	}
	public void moveDown()
	{
		this.setCrds(crds.getDown());
	}
	public void moveLeft()
	{
		this.setCrds(crds.getLeft());
	}
	public void moveRight()
	{
		this.setCrds(crds.getRight());
	}
	
}
