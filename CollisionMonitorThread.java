
import java.util.Iterator;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Unknown
 */
class CollisionMonitorThread implements Runnable {
    private final Thread t;
    private final Board b;
    private Set<Character> characters;
    private final Drone d;
    private volatile boolean shouldStop = false;
    
    CollisionMonitorThread(Board b){
        t = new Thread(this, "Collision Monitor Thread");
        this.b = b;
        d = b.getDrone();
    }
    
    void start(){
        t.start();
    }
    
    void stop(){
        shouldStop = true;
    }
    
    @Override
    public void run(){
        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();
        int animationDelay = 5;
        long time = System.currentTimeMillis();
        
        while(!shouldStop){
            characters = b.getCharacters();
            Character c;
            
            synchronized(characters){
                Iterator<Character> it = characters.iterator();
                
                while (it.hasNext()){
                    c = it.next();
                    
                    if (!c.isDrone() && c.isColliding(d)){
                        it.remove();
                        // Add scoreboard stuff here
                    }
                }
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
}
