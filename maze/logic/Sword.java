package maze.logic;

public class Sword extends Piece {
	
	public Sword(Coordinate crds)
	{
		super(crds, 'E');
	}

	public Sword(Sword swrd) {
		super(new Coordinate(swrd.getCrds()),swrd.getChr());
	}

}
