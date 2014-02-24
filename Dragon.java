package game;

public class Dragon extends Being{

	private boolean onSwrd = false;
	
	Dragon(char chr, int x, int y) {
		super(chr, x, y);
	}

	public boolean getOnSwrd() {
		return onSwrd;
	}
	public void setOnSwrd(boolean onSwrd) {
		this.onSwrd = onSwrd;
	}

}
