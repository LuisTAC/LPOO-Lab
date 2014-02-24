package game;

public class Hero extends Being{

	private boolean hasSwrd = false;
	
	Hero(char chr, int x, int y) {
		super(chr, x, y);
	}
	

	public boolean getHasSwrd() {
		return hasSwrd;
	}

	public void setHasSwrd() {
		this.hasSwrd = true;
	}

}
