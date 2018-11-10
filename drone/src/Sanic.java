
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class Sanic extends Character {
    Sanic(double x,double y, double maxSpeed, double acceleration, boolean hasGravity, boolean hasFriction) throws IOException{
        super(x, y, maxSpeed, acceleration, hasGravity, hasFriction, ImageIO.read(new File("images/sanic.png")));
        moveLeft = true;
    }
    

}
