
public class Drone extends Character{
        boolean moveUp;
        boolean moveDown;
        boolean moveRight;
        boolean moveLeft;
	
	public Drone(int x, int y, int s) {
		super (x,y,s);
                
                moveUp = false;
                moveDown = false;
                moveRight = false;
                moveLeft = false;
	}
        
}
