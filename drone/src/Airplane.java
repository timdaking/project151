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
    
    Airplane(double x, double y, double maxSpeed, double acceleration, boolean hasGravity, boolean hasFriction) {
        super(x, y, maxSpeed, acceleration, hasGravity, hasFriction);
        moveLeft = true;
    }
    
}
