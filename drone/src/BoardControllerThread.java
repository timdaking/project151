
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dpham123
 */
public class BoardControllerThread implements Runnable {
    private final Thread t;
    private Board b;
    private volatile boolean shouldStop = false;
    
    BoardControllerThread(Board b){
        t = new Thread(this, "Spawner Thread");
        this.b = b;
    }
    
    void start(){
        t.start();
    }
    
    void stop(){
        shouldStop = true;
    }
    
    @Override
    public void run() {
        Random rand = new Random();
        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();
        int animationDelay = 5;
        long time = System.currentTimeMillis();
        
        while (!shouldStop){
            b.repaint();
            try {
                spawnAirplane(0.05 + (10 - 0.01) * rand.nextDouble(), rand.nextInt(100000) + 1);
            } catch (IOException ex) {
                Logger.getLogger(BoardControllerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                time += animationDelay;
                Thread.sleep(Math.max(0, time
                        - System.currentTimeMillis()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void spawnAirplane(double d, int i) throws IOException{
        if (d < 2 && i < 800){
            Airplane a = new Airplane(1200 + 50 * d, 100 + i * d, d, 0.005 * d, false, false);
            b.addCharacter(a);
        } else if (i < 20){
            Airplane a = new Airplane(1200 + 25 * d, 100 + i * d, d, 0.005 * d, false, false);
            b.addCharacter(a);
        }
    }
    
}
