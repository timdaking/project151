
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Character {

    double x;
    double y;
    double maxSpeed;
    protected BufferedImage bi;
    boolean moveUp;
    boolean moveDown;
    boolean moveRight;
    boolean moveLeft;
    private final int[][] pixelArray;
    private double yVelocity;
    private double xVelocity;
    private final double acceleration;
    private double deltaAy;
    private double deltaAx;
    private final double GRAVITY;
    private final double FRICTION;

    public Character(double x, double y, double maxSpeed, double acceleration, boolean hasGravity, boolean hasFriction, BufferedImage bi) {
        this.x = x;
        this.y = y;
        this.maxSpeed = maxSpeed;
        this.acceleration = acceleration;
        deltaAy = acceleration;
        deltaAx = acceleration;
        yVelocity = 0;
        xVelocity = 0;
        this.bi = bi;
        pixelArray = new int[bi.getWidth()][bi.getHeight()];

        Color myTest = new Color(255, 255, 255);
        int rgb = myTest.getRGB();
        for (int i = 0; i < bi.getHeight(); i++) {
            for (int j = 0; j < bi.getWidth(); j++) {
                if (bi.getRGB(j, i) == 0) {
                    pixelArray[i][j] = 0;
                } else {
                    pixelArray[i][j] = 1;
                    bi.setRGB(j, i, rgb);
                }

            }
        }

        /*
                for (int i = 0; i < pixelArray.length; i++){
                    for (int j = 0; j < pixelArray[i].length; j++){
                        System.out.print(pixelArray[i][j]);
                    }
                    System.out.println();
                }
         */
        moveUp = false;
        moveDown = false;
        moveRight = false;
        moveLeft = false;

        //Sets gravity
        if (hasGravity) {
            GRAVITY = 0.009;
        } else {
            GRAVITY = 0;
        }

        //Sets friction
        if (hasFriction) {
            FRICTION = 0.003;
        } else {
            FRICTION = 0;
        }
    }

    private Graphics2D constructCharacter(Graphics g) {
        Graphics2D g1 = (Graphics2D) g;
        AffineTransform t = new AffineTransform();
        t.translate(x, y);
        t.scale(1, 1);
        g1.drawImage(bi, t, null);
        return g1;
    }

    Graphics2D getGraphic(Graphics g) {
        return constructCharacter(g);
    }

    int[][] getPixelArray() {
        return pixelArray;
    }

    int getWidth() {
        return bi.getWidth();
    }

    int getHeight() {
        return bi.getHeight();
    }

    boolean isDrone() {
        return this instanceof Drone;
    }

    boolean isColliding(Character c) {
        // Case where both x & y coordinates of drone is greater than the colliding character
        if (c.x >= x && c.y >= y && c.x <= x + getWidth() && c.y <= y + getHeight()) {
            return checkPixelOverlap(c.x - x, c.y - y, getHeight(), getWidth(), c, Corner.BOTTOM_RIGHT);
        }

        // Case where x coordinate of drone > x coordinate of colliding character & 
        // y coordinate of drone < y coordinate of colliding character
        if (c.x >= x && c.y <= y && c.x <= x + getWidth() && y <= c.y + getHeight()) {
            return checkPixelOverlap(c.x - x, 0, getWidth(), c.y + c.getHeight() - y, c, Corner.TOP_RIGHT);
        }
        // Case where x coordinate of drone < x coordinate of collinding character &
        // y coordinate of drone < coordinate of colliding character
        if (c.x < x && c.y < y && x < c.x + c.getWidth() && c.y < y + c.getHeight()) {
            return checkPixelOverlap(0, 0, c.x + c.getWidth() - x, c.y + c.getHeight() - y, c, Corner.TOP_LEFT);
        }
        return false;
    }

    /**
     *
     * @param x1 Starting x coordinate of "this" character array
     * @param y1 Starting y coordinate of "this" character array
     * @param x2 Ending x coordinate of character c array
     * @param y2 Ending y coordinate of character c array
     * @param c Colliding character
     * @param co
     * @return
         */
    private boolean checkPixelOverlap(double x1, double y1, double x2, double y2, Character c, Corner co) {
        if (x2 > getWidth()) {
            x2 = getWidth();
        }

        if (y2 > getHeight()) {
            y2 = getHeight();
        }
        for (int i = (int) y1; i < (int) y2; i++) {
            for (int j = (int) x1; j < (int) x2; j++) {
                switch (co) {
                    case BOTTOM_RIGHT:
                        if (pixelArray[i][j] != 0 && c.pixelArray[i - (int) y1][j - (int) x1] != 0) {
                            return true;
                        }
                        break;
                    case TOP_RIGHT:
                        if (pixelArray[i][j] != 0 && c.pixelArray[i + (int) (y - c.y)][j - (int) x1] != 0) {
                            return true;
                        }
                        break;
                    case TOP_LEFT:
                        if (pixelArray[i][j] != 0 && c.pixelArray[i + (int) (y - c.y)][j + (int) (x - c.x)] != 0) {
                            return true;
                        }
                        break;
                }

            }
        }

        return false;
    }

    void setX(int x) {
        this.x = x;
    }

    void setY(int y) {
        this.y = y;
    }

    double getY() {
        return y;
    }

    double getX() {
        return x;
    }

    void resetAcceleration() {
        deltaAy = acceleration;
        deltaAx = acceleration;
    }

    void zeroDeltaAy() {
        deltaAy = 0;
    }

    void zeroDeltaAx() {
        deltaAx = 0;
    }

    /**
     * Gets the velocity in the y direction. A positive value indicates the
     * character is moving up and a negative value indicates the character is
     * moving down.
     *
     * @return yVelocity the current velocity in the y direction
     */
    double getYVelocity() {
        return yVelocity;
    }

    /**
     * Sets the velocity in the y direction
     *
     * @param yVelocity the yVelocity
     */
    void setYVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    /**
     * Gets the velocity in the x direction. A positive value indicates the
     * character is moving right and a negative value indicates the character is
     * moving left.
     *
     * @return xVelocity the current velocity in the x direction
     */
    double getXVelocity() {
        return xVelocity;
    }

    /**
     * Sets the velocity in the x direction
     *
     * @param xVelocity the xVelocity
     */
    void setXVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    /**
     * Gets speed of character.
     */
    double getSpeed() {
        return Math.hypot(xVelocity, yVelocity);
    }

    /**
     * Increases velocity in the x direction by deltaAx
     *
     * @return xVelocity the velocity in the x direction after acceleration
     */
    double accelerateX() {
        if (xVelocity < maxSpeed) {
            if (xVelocity + deltaAx < maxSpeed) {
                xVelocity += deltaAx;
            } else {
                xVelocity = maxSpeed;
            }
        }
        return xVelocity;
    }

    /**
     * Decreases velocity in the x direction by deltaAx
     *
     * @return xVelocity the velocity in the x direction after deceleration
     */
    double decelerateX() {
        if (xVelocity > -maxSpeed) {
            if (xVelocity - deltaAx > -maxSpeed) {
                xVelocity -= deltaAx;
            } else {
                xVelocity = -maxSpeed;
            }
        }
        return xVelocity;
    }

    /**
     * Increases velocity in the y direction by deltaAy
     *
     * @return yVelocity the velocity in the y direction after acceleration
     */
    double accelerateY() {
        if (yVelocity < maxSpeed) {
            if (yVelocity + deltaAy < maxSpeed) {
                yVelocity += deltaAy;
            } else {
                yVelocity = maxSpeed;
            }
        }
        return yVelocity;
    }

    /**
     * Decreases velocity in the y direction by deltaAy
     *
     * @return yVelocity the velocity in the y direction after deceleration
     */
    double decelerateY() {
        if (yVelocity > -maxSpeed) {
            if (yVelocity - deltaAy > -maxSpeed) {
                yVelocity -= deltaAy;
            } else {
                yVelocity = -maxSpeed;
            }
        }
        return yVelocity;
    }

    /**
     * Applies gravity to character
     *
     * @return yVelocity the velocity in the y direction after gravity is
     * applied
     */
    double decelerateGravity() {
        yVelocity -= GRAVITY;
        return yVelocity;
    }

    /**
     * Applies friction to character
     *
     * @return xVelocity the velocity in the x direction after friction is
     * applied
     */
    double decelerateFriction() {
        if (xVelocity > 0) {
            xVelocity -= FRICTION;
        } else if (xVelocity < 0) {
            xVelocity += FRICTION;
        }
        return xVelocity;
    }
}
