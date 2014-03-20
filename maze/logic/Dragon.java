package maze.logic;

public class Dragon extends Piece {

	private boolean alive = true;
	private boolean awake = true;
	private boolean onSwrd = false;
		
	public Dragon(Coordinate crds) {
		super(crds, 'D');
	}
	public Dragon(Dragon drg) {
		super(new Coordinate(drg.getCrds()),drg.getChr());
		this.setAlive(drg.isAlive());
		this.setAwake(drg.isAwake());
		this.setOnSwrd(drg.isOnSwrd());
	}
	
	public boolean isAlive() {
		return alive;
	}
	public boolean isAwake() {
		return awake;
	}
	public boolean isOnSwrd() {
		return onSwrd;
	}
		
	public void setAlive(boolean alive) {
		this.alive=alive;
	}
	public void setAwake(boolean awake) {
		this.awake=awake;
	}
	public void setOnSwrd(boolean onSwrd) {
		this.onSwrd=onSwrd;
	}
	
	
}
