
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author dham123
 */
class MilitaryAirplane extends Character {
    MilitaryAirplane(double x, double y, double maxSpeed, double acceleration, boolean hasGravity, boolean hasFriction) throws IOException{
        super(x, y, maxSpeed, acceleration, hasGravity, hasFriction, ImageIO.read(new File("images/airplane_military.png")));
        moveLeft = true;
    }
    
}
