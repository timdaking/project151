
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;


public class Character {
	double x;
	double y;
	double maxSpeed;
        boolean moveUp;
        boolean moveDown;
        boolean moveRight;
        boolean moveLeft;
        private double yVelocity;
        private double xVelocity;
        private final double acceleration;
        private double deltaAy;
        private double deltaAx;
        private final double GRAVITY;
        private final double FRICTION;
	
	public Character(double x,double y, double maxSpeed, double acceleration, boolean hasGravity, boolean hasFriction) {
		this.x = x;
		this.y = y;
		this.maxSpeed = maxSpeed;
                this.acceleration = acceleration;
                deltaAy = acceleration;
                deltaAx = acceleration;
                yVelocity = 0;
                xVelocity = 0;
                
                moveUp = false;
                moveDown = false;
                moveRight = false;
                moveLeft = false;
                
                //Sets gravity
                if (hasGravity){
                    GRAVITY = 0.009;
                } else {
                    GRAVITY = 0;
                }
                
                //Sets friction
                if (hasFriction){
                    FRICTION = 0.003;
                } else {
                    FRICTION = 0;
                }
	}
        
        private Graphics2D constructDefaultCharacter(Graphics g){
            Graphics2D g1 = (Graphics2D) g;
            g.setColor(Color.BLUE);
            Rectangle2D rect = new Rectangle2D.Double(getX(), getY(), 20, 20);
            g1.fill(rect);
            return g1;
        }
        
        Graphics2D getGraphic(Graphics g){
            return constructDefaultCharacter(g);
        }
        
        void setX(int x){
            this.x = x;
        }
        
        void setY(int y){
            this.y = y;
        }
        
        double getY(){
            return y;
        }
        
        double getX(){
            return x;
        }
        
        void onKeyAction(){
            deltaAy = acceleration;
            deltaAx = acceleration;
        }
                
        void zeroDeltaAy(){
            deltaAy = 0;
        }
        
        void zeroDeltaAx(){
            deltaAx = 0;
        }
        
        /**
         * Gets the velocity in the y direction. A positive value indicates the character is moving up
         * and a negative value indicates the character is moving down.
         * @return yVelocity the current velocity in the y direction
         */
        double getYVelocity(){
            return yVelocity;
        }
        
        /**
         * Gets the velocity in the x direction. A positive value indicates the character is moving right
         * and a negative value indicates the character is moving left.
         * @return xVelocity the current velocity in the x direction
         */
        double getXVelocity(){
            return xVelocity;
        }
        
        /**
         * Gets speed of character.
         */
        double getSpeed(){
            return Math.hypot(xVelocity, yVelocity);
        }
        
        /**
         * Increases velocity in the x direction by deltaAx
         * @return xVelocity the velocity in the x direction after acceleration
         */
        double accelerateX(){
            if (xVelocity < maxSpeed){
                if (xVelocity + deltaAx < maxSpeed){
                    xVelocity += deltaAx;
                } else {
                   xVelocity = maxSpeed; 
                }
            }
            return xVelocity;
        }
        
        /**
         * Decreases velocity in the x direction by deltaAx
         * @return xVelocity the velocity in the x direction after deceleration
         */
        double decelerateX(){
            if (xVelocity > -maxSpeed){
                if (xVelocity - deltaAx > -maxSpeed){
                    xVelocity -= deltaAx;
                } else {
                    xVelocity = -maxSpeed;
                }
            }
            return xVelocity;
        }
        
        /**
         * Increases velocity in the y direction by deltaAy
         * @return yVelocity the velocity in the y direction after acceleration
         */
        double accelerateY(){
            if (yVelocity < maxSpeed){
                if (yVelocity + deltaAy < maxSpeed){
                    yVelocity += deltaAy;
                } else {
                    yVelocity = maxSpeed;
                }
            }
            return yVelocity;
        }
        
        /**
         * Decreases velocity in the y direction by deltaAy 
         * @return yVelocity the velocity in the y direction after deceleration
         */
        double decelerateY(){
            if (yVelocity > -maxSpeed){
                if (yVelocity - deltaAy > -maxSpeed){
                    yVelocity -= deltaAy;
                } else {
                    yVelocity = -maxSpeed;
                }
            }
            return yVelocity;
        }
        
        /**
         * Applies gravity to character
         * @return yVelocity the velocity in the y direction after gravity is applied
         */
        double decelerateGravity(){
            yVelocity -= GRAVITY;
            return yVelocity;
        }
        
       /**
        * Applies friction to character 
        * @return xVelocity the velocity in the x direction after friction is applied
        */
        double decelerateFriction(){
            if (xVelocity > 0){
                xVelocity -= FRICTION;
            } else if (xVelocity < 0){
                xVelocity += FRICTION;
            }
            return xVelocity;
        }
}
