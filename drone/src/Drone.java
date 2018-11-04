
public class Drone extends Character{
        boolean moveUp;
        boolean moveDown;
        boolean moveRight;
        boolean moveLeft;
	
	public Drone(double x, double y, double s, double a) {
		super (x,y,s,a);
                
                moveUp = false;
                moveDown = false;
                moveRight = false;
                moveLeft = false;
	}
        
}
