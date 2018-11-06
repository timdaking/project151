
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Unknown
 */
public class KeyListener extends KeyAdapter {

    Board b;
    Drone p;
    DroneGame dg;

    KeyListener(DroneGame dg) {
        this.dg = dg;
        this.b = dg.getBoard();
        p = b.getDrone();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case 37:
                p.moveLeft = false;
                p.zeroDeltaAx();
                break;
            case 38:
                p.moveUp = false;
                p.zeroDeltaAy();
                break;
            case 39:
                p.moveRight = false;
                p.zeroDeltaAx();
                break;
            case 40:
                p.moveDown = false;
                p.zeroDeltaAy();
                break;
            case 65:
                p.moveLeft = false;
                p.zeroDeltaAx();
                break;
            case 87:
                p.moveUp = false;
                p.zeroDeltaAy();
                break;
            case 68:
                p.moveRight = false;
                p.zeroDeltaAx();
                break;
            case 83:
                p.moveDown = false;
                p.zeroDeltaAy();
                break;
            default:
                break;
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case 27:
                /*
                dg.dispose();
                if (dg.isUndecorated()){
                    dg.setUndecorated(false);
                } else {
                    dg.setUndecorated(true);
                }
                
                dg.setVisible(true);
                break;
                */
                System.exit(0);
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
