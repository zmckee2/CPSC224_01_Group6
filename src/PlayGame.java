<<<<<<< HEAD
import sun.jvm.hotspot.memory.Space;

import java.util.Scanner;

public class PlayGame {

	public static void main(String[] args) {
		Hand[] players = new Hand[2];

		players[0].readScore();

		SpacePhase sp = new SpacePhase(players);

//		int numOfPlayers;
//		int currentTurn = 0, currentRound = 0, currentPlayer = 0;
//		String userIn = "nnnnn";
//		Hand[] players;
//		Scanner sysIn = new Scanner(System.in);
//		System.out.println("Welcome to Space Race!");
//		System.out.print("Please enter the desired amount of players: ");
//		numOfPlayers = sysIn.nextInt();
//		sysIn = new Scanner(System.in);
//		System.out.println("Starting game with " + numOfPlayers + " players");
//		players = initPlayers(numOfPlayers);


//		while(currentRound < 10) {
//			System.out.println("\n\nRound " + (currentRound + 1));
//			while(currentPlayer < (numOfPlayers)) {
//				System.out.println("Player " + (currentPlayer + 1) + "'s turn");
//				while(currentTurn < 3 && !userIn.equals("yyyyy")) {
//					for(int i = 0; i < 5; i++) {	//Reroll the dice they decided to change
//						if(userIn.charAt(i) == 'n')
//							players[currentPlayer].reroll(i);
//					}
//
//					System.out.println("Your roll: " + players[currentPlayer]);
//					System.out.print("Select resources to keep (y/n): ");
//					userIn = sysIn.nextLine();
//					currentTurn++;
//				}
//				players[currentPlayer].sort();
//				System.out.println("Your final hand is: " + players[currentPlayer].toString());
//			}
//		}
//		sysIn.close();
=======
/**
 * PlayGame.java
 * This class creates a JFrame to contain the entire game of space race yahtzee.
 * 
 * CPSC 224_01, Spring 2018
 * @author Zach McKee
 * @version 1.5 4/28/2018
 */

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;

public class PlayGame extends JFrame {
	private final int DEFAULT_WIDTH = 900;
	private final int DEFAULT_HEIGHT = 800;
	private JButton play, instructions, exitInstructions, goToBuildInst, goToSpaceInst, backToBuildInst, backToIntroInst, start;
	private JLabel title;
	private JTextArea instructionText;
	private PicturePanel introPanel, instructionPanel;
	private JPanel buttonPanel;
	private JSlider numOfPlayers;
	private Hand[] players;
	
	public static void main(String[] args) {
		new PlayGame();
	}
	
	/**
	 * constructor
	 * The constructor intizalizes the JFrame and centers it in the screen
	 */
	public PlayGame() {
		//Initzilizing the window size and location of the JFrame
		Dimension windowSize = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setSize(windowSize);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Space Race Yahtzee");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		//Centering the Frame in the screen
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		int xPos = (int) ((screenSize.getWidth() / 2) - (windowSize.getWidth() / 2));
		int yPos = (int) ((screenSize.getHeight() / 2) - (windowSize.getHeight() / 2));
		this.setLocation(xPos, yPos);
		createIntroPane();
		
		this.setVisible(true);
	}
	
	/**
	 * initPlayers()
	 * This method creates an array of hands, players, that will be used for the game
	 * @param numOfPlayers
	 */
	private void initPlayers(int numOfPlayers) {
		players = new Hand[numOfPlayers];
		for(int i = 0; i < numOfPlayers; i++) {
			players[i] = new Hand();
		}
	}
	
	/**
	 * createIntroPane()
	 * This method creates the intro screen. It uses a panel to contain it all
	 */
	private void createIntroPane() {
		//Initilizing the intro panel using a background image
		introPanel = new PicturePanel("intro.jpg");
		title = new JLabel("Welcome to Space Race Yahtzee");
		title.setFont(new Font("Helvetica", Font.PLAIN, 40));
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setForeground(Color.WHITE);
		introPanel.setLayout(new GridLayout(2,1));
		introPanel.add(title);
		
		//On the bottom half of the screen there's two buttons,
		//this creates that panel and spaces the buttons properly
		buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.setLayout(new GridLayout(3,5));
		play = new JButton("Play Game");
		play.setToolTipText("Click to play a new game");
		play.addActionListener(new gameButtonListener());
		instructions = new JButton("Instructions");
		instructions.setToolTipText("Click to see the instructions");
		instructions.addActionListener(new gameButtonListener());
		for(int i = 0; i < 6; i++)
			buttonPanel.add(new JLabel());
		buttonPanel.add(play);
		buttonPanel.add(new JLabel());
		buttonPanel.add(instructions);
		for(int i = 0; i < 6; i++)
			buttonPanel.add(new JLabel());
		
		introPanel.add(buttonPanel);
		this.add(introPanel);
	}
	
	/**
	 * createInstructionPane()
	 * This method changes the screen from the introduction screen to the instruction menu
	 */
	private void createInstructionPane() {
		//Initilizing the panel with a background image
		instructionPanel = new PicturePanel("instructions.jpg");
		instructionPanel.setLayout(new BorderLayout());
		//Creating the text field to contain all the instructions
		instructionText = new JTextArea();
		instructionText.setSize(900, 700);
		instructionText.setForeground(Color.white);
		instructionText.setOpaque(false);
		instructionText.setEditable(false);
		instructionText.setFont(new Font("Helvetica",Font.PLAIN, 18));
		writeInstructions();
		instructionPanel.add(instructionText, BorderLayout.CENTER);
		
		//Initilizing the buttons to be used to navigate the instructions screens
		exitInstructions = new JButton("Go back to welcome screen");
		goToBuildInst = new JButton("Go to build phase instructions");
		goToSpaceInst = new JButton("Go to space phase instructions");
		backToBuildInst = new JButton("Back to build phase instructions");
		backToIntroInst = new JButton("Back to general game rules");
		goToBuildInst.addActionListener(new gameButtonListener());
		goToSpaceInst.addActionListener(new gameButtonListener());
		backToBuildInst.addActionListener(new gameButtonListener());
		backToIntroInst.addActionListener(new gameButtonListener());
		
		//Initilizing a panel to contain the buttons and make it look good
		JPanel bottomButtons = new JPanel();
		bottomButtons.setOpaque(false);
		exitInstructions.addActionListener(new gameButtonListener());
		bottomButtons.add(exitInstructions);
		bottomButtons.add(backToIntroInst);
		bottomButtons.add(backToBuildInst);
		bottomButtons.add(goToBuildInst);
		bottomButtons.add(goToSpaceInst);
		instructionPanel.add(bottomButtons, BorderLayout.SOUTH);
		this.add(instructionPanel);
		//Setting the buttons that shouldn't be visable in the first instructions screen to invisible
		introPanel.setVisible(false);
		backToIntroInst.setVisible(false);
		backToBuildInst.setVisible(false);
		goToSpaceInst.setVisible(false);
			
	}
	
	/**
	 * backToIntroPane()
	 * This method switches the instructions screen back to the welcom screen
	 */
	private void backToIntroPane() {
		instructionPanel.setVisible(false);
		introPanel.setVisible(true);
	}
	
	
	/**
	 * writeInstructions()
	 * This method assigns text to the instructions text field from createInstructionPane.
	 * I made a seperate function to make it easier to read
	 */
	private void writeInstructions() {
		instructionText.setText("~~~~Space Race Yahtzee Rules~~~~\n" +
								 "In Space Race Yahtzee you compete with up to four players to get the furthest in space!\n" +
								 "The game is broken up into two sperate parts, named build phase and space phase\n" +
								 "During the build phase you will roll for resources to build your rocket.\n" +
								 "During space phase you will fly your rocket into space with the hopes of getting further than any other player\n" +
								 "Make sure during build phase to complete your rocket, lest you don't want to particitpate in space phase.\n" +
								 "Once both phases have been played, a final winner will be decided based of who flew the furthst in space phase.\n" +
								 "To fly furthest, you will need to build the best rocket you can in build phase and take risks during space phase.");
				
	}
	
	/**
	 * switchToBuildText()
	 * This method changes the instruction text to the build phase instructions
	 */
	private void switchToBuildText() {
		instructionText.setText("In build phase you select resources to keep over three turns to build rocket parts\n" +
								"Each player will have ten turns and three chances to swap resources during each turn\n" +
								"During your ten turns, you need to make sure to build the first tier of each rocket piece to participate in space phase.\n" +
								"Each rocket piece is built using diffrent combinations of the resources.\n" +
								"The combinations are as follows:\n" + 
								 "	Thrusters:     Hull Parts, Electronics, Electronics, Fuel\n" +
								 "	Fuel Reserves: Hull Parts, Electronics, Fuel, Fuel\n" +
								 "	Cockpit:       Hull Parts, Electronics, Glass, Crew Members\n" +
								 "Each rocket part above can be upgraded twice using the same combination as above.\n" +
								 "Upgraded thrusters increase your default distance covered in space phase (below).\n" +
								 "Upgraded fuel reserves give you bonus fuel for each level you upgrade them, 2 for tier 2 and 3 for tier 3.\n" +
								 "Upgraded cockpit will increase your capacity for crew members, 3 in tier 2 and 4 in tier 3\n" +								 
								 "In addition to rocket parts, you can also get more fuel or crew members with the following combinations:\n" +
								 "	Crew Member: Crew Members, Crew Members, Crew Members\n" +
								 "	1 Fuel:      Fuel, Fuel, Fuel\n" +
								 "	2 Fuel:      Fuel, Fuel, Fuel, Fuel\n" +
								 "	3 Fuel:      Fuel, Fuel, Fuel, Fuel, Fuel\n" +
								 "You will also be givin 5 fuel once the fuel reserves are built and one crew member once the cockpit is build\n");
	}
	
	/**
	 * switchToSpaceText()
	 * This method changes the instructions text to the space phase instructions
	 */
	private void switchToSpaceText() {
		instructionText.setText("Placeholder text");
	}
	
	/**
	 * promptForPlayers()
	 * This method changes the intro pane to ask the user for how many players they want to have.
	 */
	private void promptForPlayers() {
		//Changing the label already on introPanel
		title.setText("How many players?");
		introPanel.remove(buttonPanel);
		
		//Creating a new panel to make the bottom half of the screen look better
		JPanel getPlayersPanel = new JPanel();
		getPlayersPanel.setOpaque(false);
		getPlayersPanel.setLayout(new GridLayout(2,1));
		
		//Initilizing the slider to have 4 ticks
		numOfPlayers = new JSlider(1,4,1);
		numOfPlayers.setOpaque(false);
		numOfPlayers.setMajorTickSpacing(1);
		numOfPlayers.setPaintTicks(true);
		numOfPlayers.setPaintLabels(true);
		numOfPlayers.setFont(new Font("Helvetica", Font.BOLD, 25));
		numOfPlayers.setForeground(Color.white);
		start = new JButton("Start game");
		start.addActionListener(new gameButtonListener());
		getPlayersPanel.add(numOfPlayers);
		//Creating a new panel to have on the bottom to make the button centered, but not massive or tiny
		JPanel adjustmentPanel = new JPanel();
		adjustmentPanel.setOpaque(false);
		adjustmentPanel.setLayout(new GridLayout());
		adjustmentPanel.add(new JLabel());
		adjustmentPanel.add(start);
		adjustmentPanel.add(new JLabel());
		getPlayersPanel.add(adjustmentPanel);
		introPanel.add(getPlayersPanel);
	}
	
	/**
	 * switchToSpacePhase()
	 * This method changes the frame to run space phase
	 */
	public void switchToSpacePhase() {
		
	}
	
	/**
	 * switchToBuildPhase()
	 * This method changes the the frame to run build phase
	 */
	private void switchToBuildPhase() {
		
	}
	
	/**
	 * gameButtonListner
	 * This class implements the action listeners for all of the buttons used
	 */
	private class gameButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == instructions)
				createInstructionPane();
			else if (e.getSource() == exitInstructions)
				backToIntroPane();
			else if (e.getSource() == play)
				promptForPlayers();
			else if (e.getSource() == goToBuildInst) {
				switchToBuildText();
				goToBuildInst.setVisible(false);
				goToSpaceInst.setVisible(true);
				backToIntroInst.setVisible(true);
			}
			else if (e.getSource() == backToIntroInst) {
				writeInstructions();
				goToBuildInst.setVisible(true);
				goToSpaceInst.setVisible(false);
				backToIntroInst.setVisible(false);
			}
			else if (e.getSource() == goToSpaceInst) {
				switchToSpaceText();
				goToSpaceInst.setVisible(false);
				backToIntroInst.setVisible(false);
				backToBuildInst.setVisible(true);
			}
			else if (e.getSource() == backToBuildInst) {
				switchToBuildText();
				goToSpaceInst.setVisible(true);
				backToIntroInst.setVisible(true);
				backToBuildInst.setVisible(false);
			}
			else if (e.getSource() == start) {
				initPlayers(numOfPlayers.getValue());
				switchToBuildPhase();
			}
		
		}
>>>>>>> 22a96d89cfe2c340c5a71b184cd181ff166512ff
	}
//
//
//	public static Hand[] initPlayers(int numOfPlayers) {
//		Hand[] players = new Hand[numOfPlayers];
//		for(int i = 0; i < numOfPlayers; i++) {
//			players[i] = new Hand();
//		}
//		return players;
//	}
}
