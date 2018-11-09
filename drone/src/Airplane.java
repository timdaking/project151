
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Unknown
 */
public class Airplane extends Character {
    
    Airplane(double x, double y, double maxSpeed, double acceleration, boolean hasGravity, boolean hasFriction) throws IOException {
        super(x, y, maxSpeed, acceleration, hasGravity, hasFriction, ImageIO.read(new File("images/drone.png")));
        moveLeft = true;
    }
    
}
