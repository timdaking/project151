
import javax.swing.JFrame;

public class DroneGame extends JFrame {

    Board b;

    public DroneGame(){
        b = new Board();
        
        Drone p = new Drone(300, 850 / 2, 1.5, 0.05, true, true);
        Airplane a = new Airplane(1200, 500, 100, 0.1, false, false);
        
        b.addCharacter(a);
        b.addCharacter(p);
        
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
