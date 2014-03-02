package game;

public class Dragon extends Being{

	private boolean onSwrd = false;
	
	Dragon(int x, int y) {
		super('D', x, y);
	}

	public boolean getOnSwrd() {
		return onSwrd;
	}
	public void setOnSwrd(boolean onSwrd) {
		this.onSwrd = onSwrd;
	}

}
