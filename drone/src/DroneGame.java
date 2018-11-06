
import javax.swing.JFrame;

public class DroneGame extends JFrame {

    Board b;

    public DroneGame() {
        b = new Board();
        KeyListener k = new KeyListener(this);
        b.addKeyListener(k);
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
