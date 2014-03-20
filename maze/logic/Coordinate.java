package maze.logic;

public class Coordinate {
	
	private int x,y;
	
	//Constructors
	
	public Coordinate(int x, int y)
	{
		this.setX(x);
		this.setY(y);
	}
	public Coordinate(Coordinate crds) {
		this.setX(crds.getX());
		this.setY(crds.getY());
	}

	//Non-modifying methods
	
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public Coordinate getUp()
	{
		return new Coordinate(x,y-1);
	}
	public Coordinate getDown()
	{
		return new Coordinate(x,y+1);
	}
	public Coordinate getLeft()
	{
		return new Coordinate(x-1, y);
	}
	public Coordinate getRight()
	{
		return new Coordinate(x+1,y);
	}
	
	public boolean equals(Object other) {
		if(other instanceof Coordinate) {
			return this.x==((Coordinate)other).x && this.y==((Coordinate)other).y;
		}
		else return false;
	}
	
	//Modifying methods
	
	public void setX(int x)
	{
		this.x = x;
	}
	public void setY(int y)
	{
		this.y = y;
	}
	
	
	
	
}
