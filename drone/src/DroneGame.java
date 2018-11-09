
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class DroneGame extends JFrame {

    Board b;

    public DroneGame(){
        b = new Board();
        
        Drone p;
        try {
            p = new Drone(300, 850 / 2, 1.5, 0.05, true, true);
            b.addCharacter(p);
        } catch (IOException ex) {
            Logger.getLogger(DroneGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        KeyListener k = new KeyListener(this);
        b.addKeyListener(k);

        BoardControllerThread bct = new BoardControllerThread(b);
        bct.start();
        add(b);
        setTitle("DroneGame");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
    
    Board getBoard(){
        return b;
    }

    public static void main(String[] args) {
        new DroneGame();
    }
}
