
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
    static final int BOARD_WIDTH = 1000;
    static final int BOARD_HEIGHT = 1000;
    int x = 0;
    BufferedImage img;
//String message = "Click Board to Start";
    private Thread animator;
    Drone p;

    public Board() {
        addKeyListener(new TAdapter());
        addMouseListener(this);
        setFocusable(true);
        d = new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
        p = new Drone(BOARD_WIDTH - 850, BOARD_HEIGHT / 2, 1.5, 0.1);
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

        if (p.moveUp == true) {
            p.onKeyAction();
            p.y -= p.accelerate();
        }

        if (p.moveDown == true) {
            p.onKeyAction();
            p.y -= p.decelerate();
        }

        if (p.moveRight == true) {
            p.onKeyAction();
            p.x += p.accelerate();
            
        }

        if (p.moveLeft == true) {
            p.onKeyAction();
            p.x -= p.accelerate();
        }
        
        if (!(p.moveUp || p.moveDown || p.moveLeft || p.moveRight)){
            p.offKeyAction();
        }
        p.y -= p.decelerateGravity();
        System.out.println(p.getCharacterSpeed());

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

    private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();

            switch (key) {
                case 37:
                    p.moveLeft = false;
                    break;
                case 38:
                    p.moveUp = false;
                    break;
                case 39:
                    p.moveRight = false;
                    break;
                case 40:
                    p.moveDown = false;
                    break;
                case 65:
                    p.moveLeft = false;
                    break;
                case 87:
                    p.moveUp = false;
                    break;
                case 68:
                    p.moveRight = false;
                    break;
                case 83:
                    p.moveDown = false;
                    break;
                default:
                    break;
            }
            
        }

        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            switch (key) {
                case 37:
                    p.moveLeft = true;
                    break;
                case 38:
                    p.moveUp = true;
                    break;
                case 39:
                    p.moveRight = true;
                    break;
                case 40:
                    p.moveDown = true;
                    break;
                case 65:
                    p.moveLeft = true;
                    break;
                case 87:
                    p.moveUp = true;
                    break;
                case 68:
                    p.moveRight = true;
                    break;
                case 83:
                    p.moveDown = true;
                    break;
                default:
                    break;
            }
            
            
        }
    }

    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

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
