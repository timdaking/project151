
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dpham123
 */
public class SpawnerThread implements Runnable {
    private final Thread t;
    private final Board b;
    private volatile boolean shouldStop = false;
    private final boolean sanicMode;
    
    SpawnerThread(Board b, boolean sanicMode){
        t = new Thread(this, "Spawner Thread");
        this.b = b;
        this.sanicMode = sanicMode;
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
                Logger.getLogger(SpawnerThread.class.getName()).log(Level.SEVERE, null, ex);
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
        // Normal mode spawning
        if (d < 2 && i < 800){
            Airplane a = new Airplane(1200 + 50 * d, 100 + i * d, d, 0.005 * d, false, false);
            b.addCharacter(a);
        } else if (i < 50 && d < 4){
            Airplane ma = new Airplane(1200 + 25 * d, 100 + i * d, d, 0.00005 * d, false, false);
            b.addCharacter(ma);
        } else if (i < 10){
            Airplane a = new Airplane(1200 + 25 * d, 100 + i * d, d, 0.005 * d, false, false);
            b.addCharacter(a);
        }
        
        // Sanic mode spawning
        if (sanicMode && d < 5 && i < 500){
            Sanic s = new Sanic(1200 + 25 * d, 100 + i * d, d, 0.005 * d, false, false);
            b.addCharacter(s);
        } else if (sanicMode && d < 10 && i < 100){
            for (int j = 0; j < 12; j++){
                Sanic s = new Sanic(1200 + 25 * j, 50 + 50 * j, d + 5, 0.0005 * (j + 1), false, false);
                b.addCharacter(s);
            }
        }
        
        // Airplane fleet spawn
        if (sanicMode && d < 5 && i < 50){
            int k = 0;
            for (int j = 0; j < 12; j++){
                if (j > 6){
                    --k;
                } else {
                    k = j + 1;
                }
                Airplane a = new Airplane(1200 + 25 * j, 50 + 50 * j, k, 0.0005 * (k + 1), false, false);
                b.addCharacter(a);
            }
        }
    }
    
}
