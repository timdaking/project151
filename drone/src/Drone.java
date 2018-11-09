
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class Drone extends Character{
        Graphics2D g;
	
	public Drone(double x, double y, double s, double a, boolean g, boolean f) throws IOException {
		super (x, y, s, a, g, f, ImageIO.read(new File("images/drone.png")));
	}
        
        /**
         * Constructs the drone
         * @param g the graphics
         * @return g1 the fully constructed drone
         */
        private Graphics2D constructDrone(Graphics g){
            Graphics2D g1 = (Graphics2D) g;
            AffineTransform t = new AffineTransform();
            t.translate(x,y);
            t.scale(1,1);
            g1.drawImage(bi, t, null);
            return g1;
        }
        
        @Override
        Graphics2D getGraphic(Graphics g){
            return constructDrone(g);
        }
        
}
