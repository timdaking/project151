
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements MouseListener {

	boolean ingame = true;
	private int BOARD_WIDTH;
	private int BOARD_HEIGHT;
	private Set<Character> characters;
	private Drone d;
	private long startTime;
	private int gamesPlayed;
	private int gamesWon;

	public Board() {
		addMouseListener();

		characters = new HashSet<>();
		characters = Collections.synchronizedSet(characters);
		startTime = System.currentTimeMillis();
		gamesPlayed = 0;
		gamesWon = 0;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		Character c;

		synchronized (characters) {
			Iterator<Character> it = characters.iterator();
			while (it.hasNext()) {
				c = it.next();

				// Removes characters that are off screen
				if (c.x < -650) {
					it.remove();
				}

				if (c.isDrone()) {
					// Prevents drone from going out of bounds on the left
					if (c.x < -70) {
						c.moveLeft = false;
						c.setXVelocity(0);
					}

					// Prevents drone from going out of bounds on the right
					if (c.x > BOARD_WIDTH - 125) {
						c.moveRight = false;
						c.setXVelocity(0);
					}

					// Prevents drone from going out of bounds on the top
					if (c.y < -80) {
						c.moveUp = false;
						c.setYVelocity(0);
					}

					// Prevents drone from going out of bounds on the bottom
					if (c.y > BOARD_HEIGHT - 115) {
						c.moveDown = false;
						c.setYVelocity(0);
					}
				}

				// Applies gravity and friction to all characters
				g = c.getGraphic(g);
				c.y -= c.decelerateGravity();
				c.x += c.decelerateFriction();

				if (c.moveUp) {
					c.y -= c.accelerateY();
					c.resetAcceleration();
				}

				if (c.moveDown) {
					c.y -= c.decelerateY();
					c.resetAcceleration();
				}

				if (c.moveRight) {
					c.x += c.accelerateX();
					c.resetAcceleration();
				}

				if (c.moveLeft) {
					c.x += c.decelerateX();
					c.resetAcceleration();
				}
			}
		}

		Font small = new Font("Helvetica", Font.BOLD, 24);
		g.setColor(Color.black);
		g.setFont(small);
		g.drawString((System.currentTimeMillis() - startTime) / 1000 - 2 + " seconds", 10, 20);
		g.drawString("Collisions: " + CollisionMonitorThread.collisions, BOARD_WIDTH / 2 - 100, 20);

		if (((System.currentTimeMillis() - startTime) / 1000 - 2) > 90) {
			gamesPlayed++;
			if (CollisionMonitorThread.collisions < 2)
				gamesWon++;
			CollisionMonitorThread.collisions = 0;
			startTime = System.currentTimeMillis() + 2;
			removeAllCharactersButDrone();
		}
		g.drawString("Games Won: " + gamesWon + " / " + gamesPlayed, BOARD_WIDTH - 200, 20);
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	private void addMouseListener() {
		addMouseListener(this);
	}
        
        void removeAllCharactersButDrone(){
            synchronized (characters){
                Iterator<Character> it = characters.iterator();
                Character c;
                
                while (it.hasNext()){
                    c = it.next();
                    if (!c.isDrone()){
                        it.remove();
                    }
                }
            }
        }

	void addCharacter(Character c) {
		if (c.isDrone()) {
			d = (Drone) c;
		}
		characters.add(c);
	}

	Set<Character> getCharacters() {
		return characters;
	}

	Drone getDrone() {
		return d;
	}

	void setDimension(Dimension d) {
		BOARD_WIDTH = d.width;
		BOARD_HEIGHT = d.height;
		setBackgroundPicture();
	}

	private void setBackgroundPicture() {
		JLabel background;
		setSize(BOARD_WIDTH, BOARD_HEIGHT);
		setLayout(null);
		ImageIcon img = new ImageIcon("images/background.png");
		background = new JLabel("", img, JLabel.CENTER);
		background.setBounds(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
		add(background);
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mouseClicked(MouseEvent e) {

	}

}// end of class
