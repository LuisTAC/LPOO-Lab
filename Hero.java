package game;

public class Hero extends Being{

	private boolean hasSwrd = false;
	
	Hero(int x, int y) {
		super('H', x, y);
	}
	

	public boolean getHasSwrd() {
		return hasSwrd;
	}

	public void setHasSwrd() {
		this.hasSwrd = true;
	}

}
