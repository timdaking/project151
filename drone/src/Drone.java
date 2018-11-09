import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Drone extends Character{
	
	public Drone(double x, double y, double s, double a, boolean g, boolean f) throws IOException {
		super (x, y, s, a, g, f, ImageIO.read(new File("images/drone.png")));
	}
        
}
