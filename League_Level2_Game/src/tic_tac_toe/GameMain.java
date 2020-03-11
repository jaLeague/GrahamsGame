package tic_tac_toe;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameMain extends JLayeredPane implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// int[] states = new int[4];

	static final int MENU = 1;
	static final int CHOOSE = 2;
	static final int PLAY = 3;
	static final int END = 4;

	Font[] fonts = new Font[2];

	Random random = new Random();
	private int state;

	public BufferedImage image;

	public boolean needImage = true;
	public boolean gotImage = false;

	JFrame frame;
	JLabel background;

	String playerName;

	JButton[] tacbuttons = new JButton[9];
	String[] memes = new String[5];

	JButton playButton;
	JButton hiButton;

	JButton easyButton;
	JButton hardButton;

	JLabel instructions;

	Computer computer;

	GameMain(JFrame frame) {
		setLayout(null);
		memes[0] = "https://i.imgflip.com/3phog8.jpg";
		memes[1] = "https://i.imgflip.com/3ow3hu.jpg";
		memes[2] = "https://i.imgflip.com/3q28xl.jpg";
		memes[3] = "https://i.imgflip.com/3q235k.gif";
		memes[4] = "https://i.imgflip.com/3q2dll.jpg";

		fonts[0] = new Font("Avenir Next", Font.PLAIN, 42);
		fonts[1] = new Font("Avenir Next", Font.PLAIN, 22);

		

		setState(MENU);
		this.frame = frame;

		loadImage();
		createVisuals();

	}
	
	void setState (int newState) {
		state = newState;
		if (state==MENU) {computer = new Computer();}
		else if (state==CHOOSE) {System.out.println("CHOOSE");}
		else if (state==PLAY) {System.out.println("PLAY");}
		else if (state==END) {System.out.println("END");}

	}

	void loadImage() {
		background = new JLabel();
		background.addMouseListener(this);
		ImageIcon icon = new ImageIcon(getClass().getResource("Background.jpg"));
		setPreferredSize(new Dimension(Startup.WIDTH, Startup.HEIGHT));
		background.setIcon(icon);
		background.setBounds(0, 0, Startup.WIDTH, Startup.HEIGHT);
	}

	// Build the UI according to the state //
	void createVisuals() {
		this.removeAll();
		addBackground();
		if (state == MENU) {
			createPlayButtonForeground();
		} else if (state == CHOOSE) {
			createModeButtonForeground();
		} else if (state == PLAY) {
			createGameForeground();
		}

		moveToBack(background);
	}

	void addBackground() {
		this.add(background);
	}

	void createPlayButtonForeground() {

		playButton = new JButton("Play");
		hiButton = new JButton("hi wack yo toe");
		instructions = new JLabel(
				"<html>Basically you click play, choose the mode and then you<br/>click the buttons then once you do that you need <br/> to get 3 in a row.</html>");

		instructions.setForeground(Color.white);

		playButton.setBounds(25, 150, 350, 50);
		hiButton.setBounds(25, 210, 350, 50);
		instructions.setBounds(25, 400, 400, 200);
		playButton.addMouseListener(this);
		hiButton.addMouseListener(this);
		this.add(playButton);
		this.add(hiButton);
		this.add(instructions);

//			moveToFront(playButton);
//			moveToFront(hiButton);
//			moveToFront(instructions);


	}

	void createModeButtonForeground() {

		easyButton = new JButton("uwu mode (wholesome mode)");
		hardButton = new JButton("u can't win xd");

		easyButton.setBounds(25, 150, 350, 50);
		hardButton.setBounds(25, 210, 350, 50);
		easyButton.addMouseListener(this);
		hardButton.addMouseListener(this);
		this.add(easyButton);
		this.add(hardButton);

//			moveToFront(easyButton);
//			moveToFront(hardButton);


	}

	void createGameForeground() {
		int x = 0;
		int y = 90;
		tacbuttons = new JButton[9];
		int width = Startup.WIDTH / 3;
		int xBound = 0;

		// Button //
		for (int i = 0; i < 9; i++) {

			JButton clickedButton = new JButton();
			clickedButton.addMouseListener(this);
			xBound = x * width;
			clickedButton.setBounds(xBound + 40, y, 50, 50);
			tacbuttons[i] = clickedButton;
			this.add(clickedButton);
//				moveToFront(clickedButton);

			if (x >= 2) {
				x = 0;
				y += 55;
			} else {
				x++;
			}

		}

	}

	void loadImage(String imageFile) {
		if (needImage) {
			try {
				image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
				gotImage = true;
			} catch (Exception e) {
				System.out.println(e);
			}
			needImage = false;
		}
	}

//	String getCurrentDirectory() {
//		String directory = System.getProperty("user.dir");
//		return directory;
//	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("click");
		
		if (e.getSource()==background) {System.out.println("source is background");}
		
		
		if (e.getSource().equals(playButton)) {
			System.out.println("source is play button");
			setState(CHOOSE);
			createVisuals();
		} 
		else if (e.getSource().equals(hiButton)) {
			System.out.println("source is hi button");
			// Meme Button Method //
			JOptionPane.showMessageDialog(null, "ur toe is wacked");
			JOptionPane.showMessageDialog(null,
					"here sum random memes for ur agony DISCLAMER: MEME MIGHT BE REPEATED AND IM NOT RESPONSIBLE");

			Desktop d = Desktop.getDesktop();

			int randomImageNumber = random.nextInt(memes.length);

			try {
				d.browse(new URI(memes[randomImageNumber]));
				// https://i.imgflip.com/3phog8.jpg
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} 
		else if (e.getSource().equals(easyButton)) {
			System.out.println("source is easy button");
			// uwu mode uwu //
			computer.setMode(1);

			// Reset the button mouse listeners
//			easyButton.removeMouseListener(this);
//			hiButton.removeMouseListener(this);

			setState(PLAY);

			createVisuals();
		}
		else if (e.getSource().equals(hardButton)) {
			System.out.println("source is hard button");
			JOptionPane.showMessageDialog(null, "Computer won ur bad!");
		}

		// check if one of those x or o

		if (state == PLAY) {
			for (int i = 0; i < tacbuttons.length; i++) {
				JButton g = tacbuttons[i];

				if (e.getSource() == g) {
System.out.println("They clicked button "+i);
					// check if this place haven't already been taken
					if (!computer.placeTaken(i)) {
						g.setText("X");

						// change the turn to the computer

						computer.update(i, Computer.PLAYER);
						if (computer.restart) {
							setState(MENU);
							createVisuals();
						} else {
							// random
							int[] plays = computer.getTable();

							while (!computer.isFull() && !computer.restart) {
								int randInt = random.nextInt(9);

								if (plays[randInt] == 0) {
									tacbuttons[randInt].setText("O");

									computer.update(randInt, Computer.COMPUTER);
									break;
								}
							}
							if (computer.restart) {
								setState(MENU);
								createVisuals();
							} else {
								moveToBack(background);
							}
						}
					}
					else {
						System.out.println("computer thinks this is taken");
						moveToBack(background);
					}
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
