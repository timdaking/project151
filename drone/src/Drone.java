
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;


public class Drone extends Character{
        Graphics2D g;
	
	public Drone(double x, double y, double s, double a, boolean g, boolean f) {
		super (x,y,s,a,g,f);
	}
        
        /**
         * Constructs the drone
         * @param g the graphics
         * @return g1 the fully constructed drone
         */
        private Graphics2D constructDrone(Graphics g){
            Graphics2D g1 = (Graphics2D) g;
            g.setColor(Color.BLUE);
            Rectangle2D rect = new Rectangle2D.Double(getX(), getY(), 20, 20);
            g1.fill(rect);
            return g1;
        }
        
        @Override
        Graphics2D getGraphic(Graphics g){
            return constructDrone(g);
        }
        
}
