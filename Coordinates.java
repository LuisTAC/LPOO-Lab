package game;

public class Coordinates 
{
	private int x,y;
	
	Coordinates(int x, int y)
	{
		this.setX(x);
		this.setY(y);
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	
}
