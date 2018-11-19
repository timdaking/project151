
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DroneGame extends JFrame implements ActionListener {

    Board b;
    private JPanel panel;
    private JButton sanic;
    private JButton normal;
    private LayoutManager lm;

    public DroneGame(){
        b = new Board();
        b.setDoubleBuffered(true);
        Drone p;
        
        try {
            p = new Drone(300, 850 / 2, 1.5, 0.05, true, true);
            b.addCharacter(p);
        } catch (IOException ex) {
            Logger.getLogger(DroneGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        KeyListener k = new KeyListener(this);
        b.addKeyListener(k);
        setUp();
        
    }
    
    final void setUp(){
        setTitle("Drone Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        lm = getContentPane().getLayout();
        getContentPane().setLayout(new GridLayout(2,1));
        panel = new JPanel();
        normal = new JButton("Normal");
        sanic = new JButton("Sanic");
        normal.setFont(new Font ("Arial", Font.PLAIN, 100));
        sanic.setFont(new Font ("Arial", Font.PLAIN, 100));
        normal.addActionListener(this);
        sanic.addActionListener(this);
        
        panel.add(normal);
        panel.add(sanic);
        add(panel);
        
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
        b.setDimension(getContentPane().getSize());
        b.setDimension(getSize());
        setResizable(false);
        
    }
    
    Board getBoard(){
        return b;
    }

    public static void main(String[] args) {
        new DroneGame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SpawnerThread bct;
        CollisionMonitorThread cmt;
        if (e.getSource() == normal) {
            bct = new SpawnerThread(b, false);
        } else {
            bct = new SpawnerThread(b, true);
        }
        cmt = new CollisionMonitorThread(b);
        remove(panel);
        getContentPane().setLayout(lm);
        add(b);
        revalidate();
        b.requestFocus();
        bct.start();
        cmt.start();
        
    }
}
