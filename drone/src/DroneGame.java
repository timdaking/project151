import javax.swing.JFrame;

public class DroneGame extends JFrame {
	public DroneGame()
	{
		add(new Board());
	    setTitle("DroneGame");
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setSize(500,500);
	    setLocationRelativeTo(null);
	    setVisible(true);
	    setResizable(false);
	}
	public static void main(String[] args)
	{
		new DroneGame();
	}
}
