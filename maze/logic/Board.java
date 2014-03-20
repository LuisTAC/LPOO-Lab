package maze.logic;

import maze.cli.Game;


public class Board{

	protected int dim=10;
	protected char[][] cells;
	protected Hero hero;
	protected Dragon[] drgns;
	protected Sword swrd;
	protected Eagle eagle=null;
	private boolean endW=false, endL=false, endQ=false;
	
	//Constructors
	
	public Board() {}
	public Board(Board board)
	{
		this.dim = board.dim;
		this.cells = board.cells.clone();
		hero = new Hero(board.hero);
		this.setDragons(board.drgns.clone());
		swrd = new Sword(board.swrd);
		this.endW = board.getEndW();
		this.endL = board.getEndL();
		this.endQ = board.getEndQ();
		eagle = new Eagle(board.eagle);
	}
	
	//Non-modifying methods
	
	public boolean getEndW() {
		return endW;
	}
	public boolean getEndL() {
		return endL;
	}
	public boolean getEndQ() {
		return endQ;
	}
	public Hero getHero() {
		return hero;
	}
	public Dragon[] getDrgns() {
		return drgns;
	}
	public Sword getSword() {
		return swrd;
	}
	public char[][] getTab() {
		return cells;
	}
	public char getCell(Coordinate crds) {
		return cells[crds.getY()][crds.getX()];
	}
	
	public void print() {
		for(int i=0;i<dim;i++) {
			for(int j=0;j<dim;j++) {
				if(eagle!=null && eagle.isAlive() && eagleAt(new Coordinate(j,i))) System.out.print(eagle.getChr());
				else {
					int drgnInd=dragonAt(drgns.length, new Coordinate(j,i));
					if(drgnInd!=-1 && drgns[drgnInd].isAlive()) System.out.print(drgns[drgnInd].getChr());
					else if(heroAt(new Coordinate(j,i))) System.out.print(hero.getChr());
					else if(swordAt(new Coordinate(j,i)) && !hero.hasSwrd()) System.out.print(swrd.getChr());
					else System.out.print(getCell(new Coordinate(j,i)));
				}
			}
			System.out.println();
		}
	}
	
	public boolean wallAt(Coordinate crds) { //RETURNS TRUE IF A GIVEN COORDINATE LINKS TO A WALL IN THE MAZE ('X' IN CELLS)
		if(getCell(crds)=='X') return true;
		else return false;
	}
	public boolean exitAt(Coordinate crds) { //RETURNS TRUE IF A GIVEN COORDINATE LINKS TO THE EXIT IN THE MAZE ('S' IN CELLS)
		return cells[crds.getY()][crds.getX()]=='S';
	}
	public int dragonAt(int maxInd, Coordinate crds) { //RETURNS THE INDEX OF A DRAGON IN A GIVEN COORDINATE, IF NONE RETURNS -1
		for(int i=0;i<maxInd; i++)	{
			if(crds.equals(drgns[i].getCrds()))
				return i;
		}
		return -1;
	}
	public boolean heroAt(Coordinate crds) { //RETURNS TRUE IF A GIVEN COORDINATE LINKS TO THE HERO OF THE MAZE
		return hero.getCrds().equals(crds);
	}
	public boolean swordAt(Coordinate crds) { //RETURNS TRUE IF A GIVEN COORDINATE LINKS TO THE HERO OF THE MAZE
		return swrd.getCrds().equals(crds);
	}
	public boolean eagleAt(Coordinate crds) {//RETURNS TRUE IF A GIVEN COORDINATE LINKS TO THE SWORD OF THE MAZE
		return eagle.getCrds().equals(crds);
	}
	public int[] checkDragonsAt(int maxInd, Coordinate crds) { //RETURNS AN ARRAY WITH THE INDEX OF THE DRAGONS NEAR A GIVEN COORDINATE, IF NONE RETURNS AN ARRAY FULL OF -1
		int[] res = {-1,-1,-1,-1};
		int ind=0;
		res[ind]= dragonAt(maxInd, crds.getUp());
		if(res[ind]!=-1) ind++;
		res[ind]= dragonAt(maxInd, crds.getLeft());
		if(res[ind]!=-1) ind++;
		res[ind]= dragonAt(maxInd, crds.getDown());
		if(res[ind]!=-1) ind++;
		res[ind]= dragonAt(maxInd, crds.getRight());
		if(res[ind]!=-1) ind++;
		return res;	
	}
	public boolean checkDragons(int[] indDrgns) { //RETURNS TRUE IF THERE'S ANY DRAGON NEAR A COORDINATE, SHOULD BE USED WITH checkDragonAt(Coordinate)
		if(indDrgns[0]==-1) return false;
		else return true;
	}
	public boolean checkAwakeDragons(int []indDrgns) {//RETURNS TRUE IF THERE'S ANY AWAKE DRAGON NEAR A COORDINATE, SHOULD BE USED WITH checkDragonAt(Coordinate) AND checkDragons(Coordinate)
		for(int i=0;i<indDrgns.length;i++) {
			if(indDrgns[i]==-1) return false;
			if(drgns[indDrgns[i]].isAwake()) return true;
		}
		return false;
	}
	public int drgnAtSwrd() { //RETURNS THE IND OF THE DRAGON THAT IS ON TOP OF THE SWORD (HAS THE SAME COORDINATES AS THE SWORD)
		for(int i=0; i<drgns.length; i++) {
			if(drgns[i].getCrds().equals(swrd.getCrds())) {
				return i;
			}
		}
	return -1;
	}
	public int drgnOnSwrd() { //RETURNS THE IND OF THE DRAGON THAT WAS ON TOP OF THE SWORD (HAS onSword TRUE)
		for(int i=0; i<drgns.length; i++) {
			if(drgns[i].isOnSwrd()) return i;
		}
	return -1;
	}
	
	//Modifying methods
	
 	public void setDim(int dim) {
		this.dim = dim;
	}
	public void setHero(Hero hero) {
		this.hero=hero;
	}
	public void setDragons(Dragon[] dragons) {
		drgns = new Dragon[dragons.length];
		for(int i = 0;i<drgns.length;i++)
		{
			drgns[i]= new Dragon(dragons[i]);
		}
	}
	public void setSword(Sword sword) {
		this.swrd=sword;
	}
	
	public void createDfltBoard() {
		cells = new char[][]
				{{'X','X','X','X','X','X','X','X','X','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ','X','X',' ','X',' ','X',' ','X'},
                {'X',' ','X','X',' ','X',' ','X',' ','X'},
                {'X',' ','X','X',' ','X',' ','X',' ','X'},
                {'X',' ',' ',' ',' ',' ',' ','X',' ','S'},
                {'X',' ','X','X',' ','X',' ','X',' ','X'},
                {'X',' ','X','X',' ','X',' ','X',' ','X'},
                {'X',' ','X','X',' ',' ',' ',' ',' ','X'},
                {'X','X','X','X','X','X','X','X','X','X'}};
		hero = new Hero(new Coordinate(1,1));
		drgns = new Dragon[1];
		drgns[0]= new Dragon(new Coordinate(1,3));
		swrd = new Sword(new Coordinate(1,8));
		eagle=null;
		endW=false;
		endL=false;
		endQ=false;
		
	}

	public void activateEagle() {
		eagle= new Eagle(hero.getCrds());
		eagle.setCoorHero(new Coordinate(hero.getCrds()));
		eagle.setCoorSwrd(new Coordinate(swrd.getCrds()));
	}
	
	public boolean moveHero(String dir) {
		switch(dir) {
		case "w":
			if(!wallAt(hero.getCrds().getUp())) {
				if(!exitAt(hero.getCrds().getUp())) {
					if(dragonAt(drgns.length, hero.getCrds().getUp())==-1 || !drgns[dragonAt(drgns.length, hero.getCrds().getUp())].isAlive()) {
						hero.moveUp();	
						return true;
					}
				}
				else if(hero.hasSwrd()) {
					hero.moveUp();	
					return true;
				}
			}
			return false;
		case "a":
			if(!wallAt(hero.getCrds().getLeft())) {
				if(!exitAt(hero.getCrds().getLeft())) {
					if(dragonAt(drgns.length, hero.getCrds().getLeft())==-1 || !drgns[dragonAt(drgns.length, hero.getCrds().getLeft())].isAlive()) {
						hero.moveLeft();	
						return true;
					}
				}
				else if(hero.hasSwrd()) {
					hero.moveLeft();	
					return true;
				}
			}
			return false;
		case "s":
			if(!wallAt(hero.getCrds().getDown())) {
				if(!exitAt(hero.getCrds().getDown())) {
					if(dragonAt(drgns.length, hero.getCrds().getDown())==-1 || !drgns[dragonAt(drgns.length, hero.getCrds().getDown())].isAlive()) {
						hero.moveDown();	
						return true;
					}
				}
				else if(hero.hasSwrd()) {
					hero.moveDown();	
					return true;
				}
			}
			return false;
		case "d":
			if(!wallAt(hero.getCrds().getRight())) {
				if(!exitAt(hero.getCrds().getRight())) {
					if(dragonAt(drgns.length, hero.getCrds().getRight())==-1 || !drgns[dragonAt(drgns.length, hero.getCrds().getRight())].isAlive()) {
						hero.moveRight();	
						return true;
					}
				}
				else if(hero.hasSwrd()) {
					hero.moveRight();	
					return true;
				}
			}
			return false;
		case "q":
			endQ=true;
			return true;
		case "e":
			activateEagle();
			return true;
		default:
			return false;	
		}
	}
	public boolean moveDragon(int indDrgn) { //MOVES THE DRAGON WITH INDEX i AND RETURNS TRUE IF THAT MOVE IS POSSIBLE
		if(! drgns[indDrgn].isAwake()) return true;
		int dir = Game.seed.nextInt(5);
		switch(dir) {
		case 0: //STAY
			return true;
		case 1: //UP
			if(!wallAt(drgns[indDrgn].getCrds().getUp()) && !exitAt(drgns[indDrgn].getCrds().getUp()) && dragonAt(drgns.length, drgns[indDrgn].getCrds().getUp())==-1) {
				drgns[indDrgn].moveUp();
				return true;				
			}
		case 2: //LEFT
			if(!wallAt(drgns[indDrgn].getCrds().getLeft()) && !exitAt(drgns[indDrgn].getCrds().getLeft()) && dragonAt(drgns.length, drgns[indDrgn].getCrds().getLeft())==-1) {
				drgns[indDrgn].moveLeft();
				return true;				
			}
		case 3: //DOWN
			if(!wallAt(drgns[indDrgn].getCrds().getDown()) && !exitAt(drgns[indDrgn].getCrds().getDown()) && dragonAt(drgns.length, drgns[indDrgn].getCrds().getDown())==-1) {
				drgns[indDrgn].moveDown();
				return true;				
			}
		case 4: //RIGHT
			if(!wallAt(drgns[indDrgn].getCrds().getRight()) && !exitAt(drgns[indDrgn].getCrds().getRight()) && dragonAt(drgns.length, drgns[indDrgn].getCrds().getRight())==-1) {
				drgns[indDrgn].moveRight();
				return true;				
			}
		}
		return false;
	}
	public void moveEagle() {
		if(eagle != null && eagle.isAlive()) {
			eagle.move();
			eagle.setOnLand(false);
			eagle.setChr(Character.toUpperCase(eagle.getChr()));
			if(eagle.gotSword()) swrd.setCrds(new Coordinate(eagle.getCrds()));	
		}
	}
	
	public void killDragons(int[] indDrgns) { //KILLS THE DRAGONS WITH INDEX IN indDrgns
		for(int i=0;i<indDrgns.length;i++) {
			if(indDrgns[i]==-1) return;
			drgns[indDrgns[i]].setAwake(false);
			drgns[indDrgns[i]].setAlive(false);
		}
	}
	
	public void sleepWakeDragons() { //RANDOMLY DECIDES IF A DRAGON SHOULD SWITCH AWAKE STATE
		for(int i=0;i<drgns.length; i++) {
			boolean change =Game.seed.nextBoolean();
			if(change) {
				if(drgns[i].isAwake()) {
					drgns[i].setAwake(false);
					drgns[i].setChr(Character.toLowerCase(drgns[i].getChr()));
				}
				else {
					drgns[i].setAwake(true);
					drgns[i].setChr(Character.toUpperCase(drgns[i].getChr()));
				}
			}
		}
	}
	
	public void update() { //UPDATES THE BOARD CHECKING DRAGONS, SWORD, EAGLE, EXIT...
		
		//HERO AND EXIT OR SWORD
		
		if(exitAt(hero.getCrds())) { // HERO'S AT THE EXIT -> WIN
			endW=true;
		}
		if(swordAt(hero.getCrds())) { //HERO'S AT THE SWORD -> ARM HERO
			if(eagle==null || !eagle.gotSword()) {
				hero.setHasSwrd(true);
				hero.setChr('A');
				swrd.setCrds(new Coordinate(0,0));
			}
		}
		
		//DRAGONS AND SWORD
		
		int inddrgnS=drgnOnSwrd();
		if(inddrgnS!=-1 && !swrd.getCrds().equals(drgns[inddrgnS].getCrds())) { //A DRAGON WAS ON TOP OF SWORD -> 'D'
			drgns[inddrgnS].setOnSwrd(false);
			drgns[inddrgnS].setChr('D');
		}
		inddrgnS=drgnAtSwrd();
		if(inddrgnS!=-1) { //A DRAGON IS ON TOP OF SWORD -> 'F'
			drgns[inddrgnS].setOnSwrd(true);
			if(drgns[inddrgnS].isAwake()) drgns[inddrgnS].setChr('F');
			else drgns[inddrgnS].setChr('f');
		}
		
		//DRAGONS AND HERO
		
		int[] inddrgnsH =checkDragonsAt(drgns.length, hero.getCrds());
		if(checkDragons(inddrgnsH)) { //THERE'S A DRAGON NEAR HERO
			if(hero.hasSwrd()) { //HERO HAS SWORD -> KILL DRAGONS
				killDragons(inddrgnsH);
			}
			else if(checkAwakeDragons(inddrgnsH)) { //THERE ARE AWAKE DRAGONS NEAR HERO -> KILL HERO
				hero.setAlive(false);
				endL=true;
			}
		}
		
		//EAGLE
		
		if(eagle!=null) {
			
			//EAGLE AND SWORD
			if(eagle.isAtSword()) { //EAGLE ON TOP OF SWORD
				eagle.setOnLand(true);
				eagle.setChr(Character.toLowerCase(eagle.getChr()));
				eagle.setGotSword(true);
			}
			//EAGLE AND 'HOME'
			if(eagle.isAtHero() && eagle.gotSword()) { //EAGLE ON 'HOME'
				eagle.setOnLand(true);
				eagle.setChr(Character.toLowerCase(eagle.getChr()));
				eagle.setReturned(true);
			}
			//EAGLE AND HERO
			if(hero.getCrds().equals(eagle.getCrds()) && eagle.isReturned()) { //HERO IS ON EAGLE -> GET HER BACK
				eagle.setOnLand(false);
				eagle.setGotSword(false);
				eagle.setAlive(false);
			}
			//DRAGONS AND EAGLE
			if(eagle.isOnLand()) {
				int[] inddrgnsE =checkDragonsAt(drgns.length, eagle.getCrds());
				if(checkDragons(inddrgnsE)) { //THERE'S A DRAGON NEAR EAGLE
					if(checkAwakeDragons(inddrgnsE)) { //THERE ARE AWAKE DRAGONS NEAR EAGLE -> KILL EAGLE
						eagle.setAlive(false);
					}
				}
				else {
					inddrgnS = dragonAt(drgns.length,eagle.getCrds());
					if(inddrgnS!= -1 && drgns[inddrgnS].isAwake()) {
						eagle.setAlive(false);
					}
				}
			}	
		}
		
	}
}


	