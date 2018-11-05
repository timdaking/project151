
public class Drone extends Character{
        boolean moveUp;
        boolean moveDown;
        boolean moveRight;
        boolean moveLeft;
	
	public Drone(double x, double y, double s, double a, boolean g, boolean f) {
		super (x,y,s,a,g,f);
                
                moveUp = false;
                moveDown = false;
                moveRight = false;
                moveLeft = false;
	}
        
}
