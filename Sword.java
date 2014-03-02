package game;

public class Sword extends Piece{

	Sword(int x, int y) {
		super('E', x, y);
	}
	Sword(Sword swrd) {
		super('E',swrd.getX(),swrd.getY());
	}
}
