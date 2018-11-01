
public class Drone extends Character{
	boolean moveUp;
	boolean moveDown;
	
	public Drone(int x, int y, int s) {
		super (x,y,s);
		
		moveUp = false;
		moveDown = false;
	}
}
