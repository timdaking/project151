
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author dpham123
 */
class Airplane extends Character {

    Airplane(double x, double y, double maxSpeed, double acceleration, boolean hasGravity, boolean hasFriction) throws IOException {
        super(x, y, maxSpeed, acceleration, hasGravity, hasFriction, ImageIO.read(new File("images/airplane.png")));
        moveLeft = true;
    }

}
