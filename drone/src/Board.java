
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;

public class Board extends JPanel implements Runnable, MouseListener {

    boolean ingame = true;
    private Dimension d;
    static final int BOARD_WIDTH = 10000;
    static final int BOARD_HEIGHT = 10000;
    int x = 0;
    BufferedImage img;
//String message = "Click Board to Start";
    private Thread animator;
    Drone p;

    public Board() {
        addMouseListener(this);
        setFocusable(true);
        d = new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
        
        // Initializes drone and adds it to HashSet
        p = new Drone(300, 850 / 2, 1.5, 0.05, true, true);
        setBackground(Color.black);

        /*         
             try {
                img = ImageIO.read(this.getClass().getResource("mount.jpg"));
            } catch (IOException e) {
                 System.out.println("Image could not be read");
            // System.exit(1);
            }
         */
        if (animator == null || !ingame) {
            animator = new Thread(this);
            animator.start();
        }

        setDoubleBuffered(true);
    }

    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.white);
        g.fillRect(0, 0, d.width, d.height);
//g.fillOval(x,y,r,r);

        g.setColor(Color.blue);
        Graphics2D g1 = (Graphics2D) g;
        Rectangle2D rect = new Rectangle2D.Double(p.x, p.y, 20, 20);
        g1.fill(rect);
        
        // Takes care of drone movement based on user input
        if (p.moveUp == true) {
            p.onKeyAction();
            p.y -= p.accelerateY();
        }

        if (p.moveDown == true) {
            p.onKeyAction();
            p.y -= p.decelerateY();
        }

        if (p.moveRight == true) {
            p.onKeyAction();
            p.x += p.accelerateX();
        }

        if (p.moveLeft == true) {
            p.onKeyAction();
            p.x += p.decelerateX();
        }
        
        // Applies gravity and friction to the drone
        p.y -= p.decelerateGravity();
        p.x += p.decelerateFriction();

        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);
        g.setColor(Color.black);
        g.setFont(small);
        //g.drawString(message, 10, d.height-60);

        if (ingame) {

            // g.drawImage(img,0,0,200,200 ,null);
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    
    Drone getDrone(){
        return p;
    }

    public void mousePressed(MouseEvent e) {
        /*
        int x = e.getX();
        int y = e.getY();
        */

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

    }

    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();
        int animationDelay = 5;
        long time
                = System.currentTimeMillis();
        while (true) {//infinite loop
            // spriteManager.update();
            repaint();
            try {
                time += animationDelay;
                Thread.sleep(Math.max(0, time
                        - System.currentTimeMillis()));
            } catch (InterruptedException e) {
                System.out.println(e);
            }//end catch
        }//end while loop

    }//end of run

}//end of class
