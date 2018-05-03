import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;




public class BuildPhase extends PicturePanel{
	
	// fields for array of Hands, current Hand during turn, and number of players
	Hand[] players;
	Hand curHand;
	int numPlayers;
	
	int roundNum;
	int activePlayerNum;
	int rollNum;
	boolean waitingForBuildButtonPress = false;
	boolean waitingForDiceButtonPress = false;
	boolean waitingForProceedButtonPress = false;
	
	//init all buttons
	JButton ckpt1,ckpt2,ckpt3,thrst1,thrst2,thrst3,fres1,fres2,fres3,
			addCrewMem,addFuel,die1,die2,die3,die4,die5,reroll,proceed,noMove;
	
	//init all labels
	JLabel numCrewMem,maxCrewMem,numFuel,roundNumL,rollNumL,activePlayerNumL;
	
	JTextArea ckptTips,thrstTips,fresTips,crewMemTips,fuelTips;
	
	JTextArea debugConsole = new JTextArea();
	//JScrollPane scrollPane = new JScrollPane(debugConsole, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	PlayGame activeWindow;
	
	// dimension for Dice
	Dimension dieDim = new Dimension(50,50);
	
	//setting button color defaults
	Color red_btn = new Color(239,95,95);
	Color blu_btn = new Color(111, 220, 237);
	Color grn_btn = new Color(97,244,132);
	Color gry_btn =	Color.GRAY;
	
	boolean[] rerollDice = new boolean[5];
	
	/**
	 * BuildPhase(Hand[] initPlayers)
	 * This is the constructor for BuildPhase
	 * @param array of Hands representing different players
	 */
	public BuildPhase(Hand[] initPlayers/*, /*PlayGame activeWindow*/){
		super("build.jpg");
		//this.activeWindow = activeWindow;
		// init players and number of players
		players = initPlayers;
		numPlayers = players.length;
		
		// init panels and various layouts
		this.setLayout(new BorderLayout());
		JPanel scrb = new JPanel();
		scrb.setLayout(new GridBagLayout());
		JPanel dicePanel = new JPanel();
		dicePanel.setLayout(new GridBagLayout());
		scrb.setOpaque(false);
		dicePanel.setOpaque(false);
		// init GridBagConstraints object to control location of buttons & labels
		GridBagConstraints gCon = new GridBagConstraints();
		gCon.gridx = 1;
		gCon.gridy = 1;
		gCon.gridwidth = 1;
		gCon.gridheight = 1;
		gCon.weightx = 1;
		gCon.weighty = 1;
		gCon.insets = new Insets(10,10,10,10);
		gCon.anchor = GridBagConstraints.CENTER;
		gCon.fill = GridBagConstraints.BOTH;
		
		// init buttons and labels with descriptive text
		numCrewMem = new JLabel("Number of Crew Members: 0");
		maxCrewMem = new JLabel("Max Number of Crew Members: 0");
		addCrewMem = new JButton("Add Crew Member");
		numFuel = new JLabel("Units of Fuel: 0");
		addFuel = new JButton("Add Fuel");
		numCrewMem.setForeground(Color.white);
		maxCrewMem.setForeground(Color.WHITE);
		numFuel.setForeground(Color.white);
		
		debugConsole.setEditable(false);
		debugConsole.setPreferredSize(debugConsole.getPreferredSize());
		debugConsole.setLineWrap(true);
		debugConsole.setWrapStyleWord(true);
		
		ckpt1 = new JButton("Cockpit Mk1");
		ckpt2 = new JButton("Cockpit Mk2");
		ckpt3 = new JButton("Cockpit Mk3");
		
		thrst1 = new JButton("Thruster Mk1");
		thrst2 = new JButton("Thruster Mk2");
		thrst3 = new JButton("Thruster Mk3");
		
		fres1 = new JButton("Fuel Reserves Mk1");
		fres2 = new JButton("Fuel Reserves Mk2");
		fres3 = new JButton("Fuel Reserves Mk3");
		
		noMove = new JButton("No Move This Turn");
		
		die1 = new JButton("ELEC");
		die2 = new JButton("CREW");
		die3 = new JButton("FUEL");
		die4 = new JButton("HULL");
		die5 = new JButton("GLAS");
		
		roundNumL = new JLabel("Round: ");
		rollNumL = new JLabel("Roll: ");
		activePlayerNumL = new JLabel("Player: ");
		
		reroll = new JButton("REROLL");
		proceed = new JButton("PROCEED TO NEXT TURN");
		
		ckptTips = new JTextArea();
		thrstTips = new JTextArea();
		fresTips = new JTextArea();
		crewMemTips = new JTextArea();
		fuelTips = new JTextArea();
		
		ckptTips.setText("Cost to build a Cockpit:                GLAS, ELEC, CREW, HULL");
		thrstTips.setText("Cost to build a Thruster:               FUEL, ELEC, HULL, HULL");
		fresTips.setText("Cost to build Fuel Reserves:         FUEL, FUEL, ELEC, HULL");
		crewMemTips.setText("Cost to add a Crew Member:       At least 3 CREW, and Cockpit  Mk1 must be built");
		fuelTips.setText("Cost to add Fuel:                           At least 3 FUEL, and Fuel             Reserves Mk1 must be built");
		ckptTips.setOpaque(false);
		thrstTips.setOpaque(false);
		fresTips.setOpaque(false);
		crewMemTips.setOpaque(false);
		fuelTips.setOpaque(false);
		ckptTips.setForeground(Color.white);
		thrstTips.setForeground(Color.white);
		fresTips.setForeground(Color.white);
		crewMemTips.setForeground(Color.white);
		fuelTips.setForeground(Color.white);
		ckptTips.setWrapStyleWord(true);
		fuelTips.setWrapStyleWord(true);
		thrstTips.setWrapStyleWord(true);
		fresTips.setWrapStyleWord(true);
		crewMemTips.setWrapStyleWord(true);
		
		ckptTips.setEditable(false);
		thrstTips.setEditable(false);
		fresTips.setEditable(false);
		crewMemTips.setEditable(false);
		fuelTips.setEditable(false);
		
		ckptTips.setPreferredSize(new Dimension(50,20));
		thrstTips.setPreferredSize(ckptTips.getPreferredSize());
		fresTips.setPreferredSize(ckptTips.getPreferredSize());
		crewMemTips.setPreferredSize(ckptTips.getPreferredSize());
		fuelTips.setPreferredSize(ckptTips.getPreferredSize());
		
		ckptTips.setLineWrap(true);
		thrstTips.setLineWrap(true);
		fresTips.setLineWrap(true);
		crewMemTips.setLineWrap(true);
		fuelTips.setLineWrap(true);
		
		// init button listener
		ListenForButton lForBtn = new ListenForButton();
		
		// add action listeners to all buttons
		ckpt1.addActionListener(lForBtn);
		ckpt2.addActionListener(lForBtn);
		ckpt3.addActionListener(lForBtn);
		thrst1.addActionListener(lForBtn);
		thrst2.addActionListener(lForBtn);
		thrst3.addActionListener(lForBtn);
		fres1.addActionListener(lForBtn);
		fres2.addActionListener(lForBtn);
		fres3.addActionListener(lForBtn);
		die1.addActionListener(lForBtn);
		die2.addActionListener(lForBtn);
		die3.addActionListener(lForBtn);
		die4.addActionListener(lForBtn);
		die5.addActionListener(lForBtn);
		proceed.addActionListener(lForBtn);
		noMove.addActionListener(lForBtn);
		reroll.addActionListener(lForBtn);
		addCrewMem.addActionListener(lForBtn);
		addFuel.addActionListener(lForBtn);
		
		gCon.gridx = 1;
		scrb.add(ckpt1,gCon);
		gCon.gridx = 2;
		scrb.add(thrst1,gCon);
		gCon.gridx = 3;
		scrb.add(fres1,gCon);
		gCon.gridx = 1;
		gCon.gridy = 2;
		scrb.add(ckpt2,gCon);
		gCon.gridx = 2;
		scrb.add(thrst2,gCon);
		gCon.gridx = 3;
		scrb.add(fres2,gCon);
		gCon.gridx = 1;
		gCon.gridy = 3;
		scrb.add(ckpt3,gCon);
		gCon.gridx = 2;
		scrb.add(thrst3,gCon);
		gCon.gridx = 3;
		scrb.add(fres3,gCon);
		
		gCon.gridx = 4;
		gCon.gridy = 1;
		scrb.add(numCrewMem,gCon);
		gCon.gridy = 2;
		scrb.add(maxCrewMem,gCon);
		gCon.gridy = 3;
		scrb.add(addCrewMem,gCon);
		gCon.gridx = 5;
		gCon.gridy = 1;
		scrb.add(numFuel,gCon);
		gCon.gridy = 3;
		scrb.add(addFuel,gCon);
		gCon.gridx = 2;
		gCon.gridy = 4;
		scrb.add(noMove, gCon);
		gCon.gridx = 3;
		scrb.add(reroll, gCon);
		gCon.gridx = 4;
		scrb.add(proceed, gCon);
		
		
		gCon.gridx = 1;
		gCon.gridy = 1;
		dicePanel.add(ckptTips, gCon);
		gCon.gridx = 2;
		dicePanel.add(thrstTips, gCon);
		gCon.gridx = 3;
		dicePanel.add(fresTips, gCon);
		gCon.gridx = 4;
		dicePanel.add(crewMemTips, gCon);
		gCon.gridx = 5;
		dicePanel.add(fuelTips, gCon);
		
		
		gCon.gridx = 1;
		gCon.gridy = 2;
		dicePanel.add(die1,gCon);
		gCon.gridx = 2;
		dicePanel.add(die2,gCon);
		gCon.gridx = 3;
		dicePanel.add(die3,gCon);
		gCon.gridx = 4;
		dicePanel.add(die4,gCon);
		gCon.gridx = 5;
		dicePanel.add(die5,gCon);
		
		gCon.gridx = 1;
		gCon.gridy = 3;
		dicePanel.add(debugConsole, gCon);
		//textPane.add(debugConsole);
		//textPane.setVisible(true);
		gCon.gridx = 2;
		dicePanel.add(activePlayerNumL, gCon);
		gCon.gridx = 3;
		dicePanel.add(roundNumL, gCon);
		gCon.gridx = 4;
		dicePanel.add(rollNumL, gCon);
		gCon.gridx = 5;
		JPanel fill = new JPanel();
		fill.setOpaque(false);
		dicePanel.add(fill, gCon);
		
		//proceed.setVisible(false);// set PROCEED button to be hidden
		die1.setVisible(true);
		scrb.setVisible(true);
		dicePanel.setVisible(true);
		this.add(scrb, BorderLayout.NORTH);
		this.add(dicePanel, BorderLayout.CENTER);
		activePlayerNum = 0;
		curHand = players[0];
		activePlayerNumL.setText("Player: " + (activePlayerNum + 1));
		roundNumL.setText("Round 1");
		rollNumL.setText("Roll: 1");
		for(int i = 0; i < 5; i++)
			rerollDice[i] = false;
		refreshBuildOptions(curHand);
		rollResources(curHand,rerollDice);
		updateDiceText();
		debugConsole.setWrapStyleWord(true);
		debugConsole.setText("Please select dice to keep, click REROLL when finsihed");
		this.setVisible(true);
	}
	
	public void updateDiceText() {
		JButton[] playerDice = {die1, die2, die3, die4, die5};
		for(int dieInd = 0; dieInd < 5; dieInd++) {
			if(curHand.getResourceAt(dieInd) == Hand.Resources.Electronics) {
				playerDice[dieInd].setText("ELEC");
			} else if(curHand.getResourceAt(dieInd) == Hand.Resources.Fuel) {
				playerDice[dieInd].setText("FUEL");
			} else if(curHand.getResourceAt(dieInd) == Hand.Resources.Glass) {
				playerDice[dieInd].setText("GLAS");
			} else if(curHand.getResourceAt(dieInd) == Hand.Resources.Hull_Parts) {
				playerDice[dieInd].setText("HULL");
			} else if(curHand.getResourceAt(dieInd) == Hand.Resources.Crew_Member) {
				playerDice[dieInd].setText("CREW");
			} else {
				playerDice[dieInd].setText("N//A");
			}
			playerDice[dieInd].setBackground(null);
		}
	}
	
	/**
	 * rollResources(Hand h, boolean a, boolean b, boolean c, boolean d, boolean e)
	 * This method rerolls the resources in the players Hand based on which booleans
	 * are true and which are false (false = no reroll, true = reroll resource)
	 * @param Hand h, booleans a-e
	 */
	private void rollResources(Hand h, boolean[] a){ // 
		for(int i = 0; i < 5; i++) 
			if(!a[i]) 
				h.reroll(i);	
	}
	
	private void refreshBuildOptions(Hand h) {
		if(!h.checkPartBuilt(0))
			thrst1.setBackground(null);
		else
			thrst1.setBackground(blu_btn);
		thrst1.setEnabled(false);
		if(!h.checkPartBuilt(1))
			thrst2.setBackground(null);
		else
			thrst2.setBackground(blu_btn);
		thrst2.setEnabled(false);
		if(!h.checkPartBuilt(2))
			thrst3.setBackground(null);
		else
			thrst3.setBackground(blu_btn);
		thrst3.setEnabled(false);
		if(!h.checkPartBuilt(3))
			fres1.setBackground(null);
		else
			fres1.setBackground(blu_btn);
		fres1.setEnabled(false);
		if(!h.checkPartBuilt(4))
			fres2.setBackground(null);
		else
			fres2.setBackground(blu_btn);
		fres2.setEnabled(false);
		if(!h.checkPartBuilt(5))
			fres3.setBackground(null);
		else
			fres3.setBackground(blu_btn);
		fres3.setEnabled(false);
		if(!h.checkPartBuilt(6))
			ckpt1.setBackground(null);
		else
			ckpt1.setBackground(blu_btn);
		ckpt1.setEnabled(false);
		if(!h.checkPartBuilt(7))
			ckpt2.setBackground(null);
		else
			ckpt2.setBackground(blu_btn);
		ckpt2.setEnabled(false);
		if(!h.checkPartBuilt(8))
			ckpt3.setBackground(null);
		else
			ckpt3.setBackground(blu_btn);
		ckpt3.setEnabled(false);
		addFuel.setBackground(null);
		addFuel.setEnabled(false);
		addCrewMem.setBackground(null);
		addCrewMem.setEnabled(false);
		numCrewMem.setText("Number of Crew Members: " + curHand.getNumberCrewMembers());
		maxCrewMem.setText("Max Number of Crew Members: " + curHand.getMaxNumberCrewMembers());
		numFuel.setText("Units of Fuel: " + curHand.getNumberFuel());
		noMove.setEnabled(false);
		noMove.setBackground(null);
		proceed.setEnabled(false);
		proceed.setBackground(null);
	}

	/**
	 * updateBuildOptions(Hand h)
	 * This method updates colors of button backgrounds and label text
	 * @param Hand h
	 */
	private void updateBuildOptions(Hand h){
		if(canBuildCockpit(h)){
			// Cockpit can be built
			if(!h.checkPartBuilt(6)){
				ckpt1.setBackground(grn_btn);
				ckpt2.setBackground(red_btn);
				ckpt3.setBackground(red_btn);
				ckpt1.setEnabled(true);
				ckpt2.setEnabled(false);
				ckpt3.setEnabled(false);
			} else if(!h.checkPartBuilt(7)){
				ckpt1.setBackground(blu_btn);
				ckpt2.setBackground(grn_btn);
				ckpt3.setBackground(red_btn);
				ckpt1.setEnabled(false);
				ckpt2.setEnabled(true);
				ckpt3.setEnabled(false);
			} else if(!h.checkPartBuilt(8)){
				ckpt1.setBackground(blu_btn);
				ckpt2.setBackground(blu_btn);
				ckpt3.setBackground(grn_btn);
				ckpt1.setEnabled(false);
				ckpt2.setEnabled(false);
				ckpt3.setEnabled(true);
			}
		} else {
			ckpt1.setBackground(red_btn);
			ckpt2.setBackground(red_btn);
			ckpt3.setBackground(red_btn);
			ckpt1.setEnabled(false);
			ckpt2.setEnabled(false);
			ckpt3.setEnabled(false);
			if(h.checkPartBuilt(6))
				ckpt1.setBackground(blu_btn);
			if(h.checkPartBuilt(7))
				ckpt2.setBackground(blu_btn);
			if(h.checkPartBuilt(8))
				ckpt3.setBackground(blu_btn);
		} if(canBuildThruster(h)){
			//Thruster can be built
			if(!h.checkPartBuilt(0)){ // first Thruster upgrade can be built
				// set Thruster button colors
				thrst1.setBackground(grn_btn);
				thrst2.setBackground(red_btn);
				thrst3.setBackground(red_btn);
				thrst1.setEnabled(true);
				thrst2.setEnabled(false);
				thrst3.setEnabled(false);
			} else if(!h.checkPartBuilt(1)){ // second Thruster upgrade can be built
				// set Thruster button colors
				thrst1.setBackground(blu_btn);
				thrst2.setBackground(grn_btn);
				thrst3.setBackground(red_btn);
				thrst1.setEnabled(false);
				thrst2.setEnabled(true);
				thrst3.setEnabled(false);
			} else if(!h.checkPartBuilt(2)){ // third Thruster upgrade can be built
				// set Thruster button colors
				thrst1.setBackground(blu_btn);
				thrst2.setBackground(blu_btn);
				thrst3.setBackground(grn_btn);
				thrst1.setEnabled(false);
				thrst2.setEnabled(false);
				thrst3.setEnabled(true);
			}
		} else { // no Thruster upgrade can be built
			// set Thruster button colors
				thrst1.setBackground(red_btn);
				thrst2.setBackground(red_btn);
				thrst3.setBackground(red_btn);
				thrst1.setEnabled(false);
				thrst2.setEnabled(false);
				thrst3.setEnabled(false);
				if(h.checkPartBuilt(0))
					thrst1.setBackground(blu_btn);
				if(h.checkPartBuilt(1))
					thrst2.setBackground(blu_btn);
				if(h.checkPartBuilt(2))
					thrst3.setBackground(blu_btn);
		} if(canBuildFuelReserves(h)){
			//fuel reserve can be built (UI stuff)
			if(!h.checkPartBuilt(3)){
				fres1.setBackground(grn_btn);
				fres2.setBackground(red_btn);
				fres3.setBackground(red_btn);
				fres1.setEnabled(true);
				fres2.setEnabled(false);
				fres3.setEnabled(false);
			} else if(!h.checkPartBuilt(4)){
				fres1.setBackground(blu_btn);
				fres2.setBackground(grn_btn);
				fres3.setBackground(red_btn);
				fres1.setEnabled(false);
				fres2.setEnabled(true);
				fres3.setEnabled(false);
			} else if(!h.checkPartBuilt(5)){
				fres1.setBackground(blu_btn);
				fres2.setBackground(blu_btn);
				fres3.setBackground(grn_btn);
				fres1.setEnabled(false);
				fres2.setEnabled(false);
				fres3.setEnabled(true);
			}
		} else {
			fres1.setBackground(red_btn);
			fres2.setBackground(red_btn);
			fres3.setBackground(red_btn);
			fres1.setEnabled(false);
			fres2.setEnabled(false);
			fres3.setEnabled(false);
			if(h.checkPartBuilt(3))
				fres1.setBackground(blu_btn);
			if(h.checkPartBuilt(4))
				fres3.setBackground(blu_btn);
			if(h.checkPartBuilt(5))
				fres3.setBackground(blu_btn);
		} if(canAddCrewMembers(h)){
			//1 crew member can be added (UI stuff)
			addCrewMem.setBackground(grn_btn);
			addCrewMem.setEnabled(true);
		} else {
			addCrewMem.setBackground(red_btn);
			addCrewMem.setEnabled(false);
		}
		
		if(canAddFuel(h)){
			addFuel.setBackground(grn_btn);
			addFuel.setEnabled(true);
		} else {
			addFuel.setBackground(red_btn);
			addFuel.setEnabled(false);
		}
		noMove.setEnabled(true);
		noMove.setBackground(grn_btn);
		//update number of commodities & max fuel labels
		// h.handScorecard.commodities[0] is Crew Members
		// h.handScorecard.commodities[1] is Fuel
		
		//numCrewMem = new JLabel("Number of Crew Members: " + h.handScorecard.getNumberCrewMembers());
		//maxCrewMem = new JLabel("Max Number of Crew Members: " + h.handScorecard.getMaxNumberCrewMembers());
		//numFuel = new JLabel("Units of Fuel: " + h.handScorecard.getNumberFuel());
		
	}
	
	private void ontoNext() {
		if(activePlayerNum == (players.length - 1)) {
			curHand = players[0];
			activePlayerNum = 0;
		} else {
			curHand = players[activePlayerNum + 1];
			activePlayerNum++;
		}
		refreshBuildOptions(curHand);
		for(int i = 0; i < 5; i++) 
			rerollDice[i] = false;
		rollResources(curHand,rerollDice);
		updateDiceText();
		activePlayerNumL.setText("Player : " + (activePlayerNum + 1));
		rollNumL.setText("Roll: 1");
		rollNum = 0;
		debugConsole.setText("Please select dice to keep, click REROLL when finsihed");
	}
	/**
	 * ListenForButton class provides specific new implementation of ActionListener interface
	 */
	private class ListenForButton implements ActionListener{
		/**
		 * actionPerformed(ActionEvent e)
		 * Takes in event (button press, etc) and takes action accordingly
		 * @param event
		 */
		public void actionPerformed(ActionEvent e) {
				if(e.getSource() == ckpt1) {
					if(canBuildCockpit(curHand) && !curHand.checkPartBuilt(6)){
						buildCockpit(curHand);
						ckpt1.setBackground(blu_btn);
						proceed.setEnabled(true);
						proceed.setBackground(grn_btn);
						debugConsole.setText("Build Cockpit MKI, please click proceed");
					}
				}
				if(e.getSource() == ckpt2) {
					if(canBuildCockpit(curHand) && !curHand.checkPartBuilt(7)){
						buildCockpit(curHand);
						ckpt2.setBackground(blu_btn);
						proceed.setEnabled(true);
						proceed.setBackground(grn_btn);
						debugConsole.setText("Build Cockpit MKII, please click proceed");
					}
				}
				if(e.getSource() == ckpt3) {
					if(canBuildCockpit(curHand) && !curHand.checkPartBuilt(8)){
						buildCockpit(curHand);
						ckpt3.setBackground(blu_btn);
						proceed.setEnabled(true);
						proceed.setBackground(grn_btn);
						debugConsole.setText("Build Cockpit MKIII, please click proceed");
					}
				}
				if(e.getSource() == thrst1) {
					if(canBuildThruster(curHand) && !curHand.checkPartBuilt(0)){
						buildThruster(curHand);
						thrst1.setBackground(blu_btn);
						proceed.setEnabled(true);
						proceed.setBackground(grn_btn);
						debugConsole.setText("Build Thrusters MKI, please click proceed");
					}
				}
				if(e.getSource() == thrst2) {
					if(canBuildThruster(curHand) && !curHand.checkPartBuilt(1)){
						buildThruster(curHand);
						thrst2.setBackground(blu_btn);
						proceed.setEnabled(true);
						proceed.setBackground(grn_btn);
						debugConsole.setText("Build Thrusters MKII, please click proceed");
					}
				}
				if(e.getSource() == thrst3) {
					if(canBuildThruster(curHand) && !curHand.checkPartBuilt(2)){
						buildThruster(curHand);
						thrst3.setBackground(blu_btn);
						proceed.setEnabled(true);
						proceed.setBackground(grn_btn);
						debugConsole.setText("Build Thrusters MKIII, please click proceed");
					}
				}
				if(e.getSource() == fres1) {
					if(canBuildFuelReserves(curHand) && !curHand.checkPartBuilt(3)){
						buildFuelReserves(curHand);
						fres1.setBackground(blu_btn);
						proceed.setEnabled(true);
						proceed.setBackground(grn_btn);
						debugConsole.setText("Build Fuel Reserves MKI, please click proceed");
					}
				}
				if(e.getSource() == fres2) {
					if(canBuildFuelReserves(curHand) && !curHand.checkPartBuilt(4)){
						buildFuelReserves(curHand);
						fres2.setBackground(blu_btn);
						proceed.setEnabled(true);
						proceed.setBackground(grn_btn);
						debugConsole.setText("Build Fuel Reserves MKII, please click proceed");
					}
				}
				if(e.getSource() == fres3) {
					if(canBuildFuelReserves(curHand) && !curHand.checkPartBuilt(5)){
						buildFuelReserves(curHand);
						fres3.setBackground(blu_btn);
						proceed.setEnabled(true);
						proceed.setBackground(grn_btn);
						debugConsole.setText("Build Fuel Reserves MKIII, please click proceed");
					}
				}
				if(e.getSource() == addCrewMem) {
					if(canAddCrewMembers(curHand)){
						curHand.addCommodity(0);
						addCrewMem.setBackground(blu_btn);
						proceed.setEnabled(true);
						proceed.setBackground(grn_btn);
						debugConsole.setText("Added a crew member, please click proceed");
					}
				}
				if(e.getSource() == addFuel) {
					if(canAddFuel(curHand)){
						addFuel(curHand);
						proceed.setEnabled(true);
						proceed.setBackground(grn_btn);
						debugConsole.setText("Added fuel, please click proceed");
					}
				}
				if(e.getSource() == noMove) {
					debugConsole.setText("Nothing built, please click proceed");
					noMove.setBackground(blu_btn);
					proceed.setEnabled(true);
					proceed.setBackground(grn_btn);
				}
				if(e.getSource() == die1) {
					if(die1.getBackground() == grn_btn) {
						die1.setBackground(null);
						rerollDice[0] = false;
					} else {
						die1.setBackground(grn_btn);
						rerollDice[0] = true;
					}
				}
				if(e.getSource() == die2) {
					if(die2.getBackground() == grn_btn) {
						die2.setBackground(null);
						rerollDice[1] = false;
					} else {
						die2.setBackground(grn_btn);
						rerollDice[1] = true;
					}
				}
				if(e.getSource() == die3) {
					if(die3.getBackground() == grn_btn) {
						die3.setBackground(null);
						rerollDice[2] = false;
					} else {
						die3.setBackground(grn_btn);
						rerollDice[2] = true;
					}
				}
				if(e.getSource() == die4){
					if(die4.getBackground() == grn_btn) {
						die4.setBackground(null);
						rerollDice[3] = false;
					} else {
						die4.setBackground(grn_btn);
						rerollDice[3] = true;
					}
				}
				if(e.getSource() == die5) {
					if(die5.getBackground() == grn_btn) {
						die5.setBackground(null);
						rerollDice[4] = false;
					} else {
						die5.setBackground(grn_btn);
						rerollDice[4] = true;
					}
				}
				if(e.getSource() == reroll) {
					if((rerollDice[0] && rerollDice[1] && rerollDice[2] && rerollDice[3] && rerollDice[4]) || rollNum == 3) {
						updateBuildOptions(curHand);
					}
					else if (rollNum < 2) {
						rollResources(curHand, rerollDice);
						rollNum++;
						rollNumL.setText("Roll: " + (rollNum + 1));
						updateDiceText();
					} else {
						rollResources(curHand, rerollDice);
						updateDiceText();
						updateBuildOptions(curHand);
					}
					for(int din = 0; din < 5; din++) {
						rerollDice[din] = false;
					}
							
					
				}
			if(e.getSource() == proceed) {
				if(roundNum < 10) {
					if(activePlayerNum == (players.length - 1)) {
						roundNum++;
						roundNumL.setText("Round: " + (roundNum + 1));
					}
					ontoNext();
				} else {
					switchToSpace();
				}
			}
			
		}
	}
	
	/**
	 * buildThruster(Hand h)
	 * This method builds the next available upgrade of the thruster
	 * in the Hand's Scoreboard
	 * @param Hand h
	 */
	private void buildThruster(Hand h){
		if(canBuildThruster(h)){
			if(!h.checkPartBuilt(0)){
				h.buildPart(0);
			} else if(!h.checkPartBuilt(1)){
				h.buildPart(1);
			} else if(!h.checkPartBuilt(2)){
				h.buildPart(2);
			}
		}
	}
	
	/**
	 * buildFuelReserves(Hand h)
	 * This method builds the next available upgrade of the thruster
	 * in the Hand's Scoreboard
	 * @param Hand h
	 */
	private void buildFuelReserves(Hand h){
		if(canBuildFuelReserves(h)){
			//fuel reserve can be built (UI stuff)
			if(!h.checkPartBuilt(3)){
				h.buildPart(3);
			} else if(!h.checkPartBuilt(4)){
				h.buildPart(4);
			} else if(!h.checkPartBuilt(5)){
				h.buildPart(5);
			}
		}
	}
	
	/**
	 * buildCockpit(Hand h)
	 * This method builds the next available upgrade of the cockpit
	 * in the Hand's Scoreboard
	 * @param Hand h
	 */
	private void buildCockpit(Hand h){
		if(canBuildCockpit(h)){
			//cockpit can be built (UI stuff)
			if(!h.checkPartBuilt(6)){
				h.buildPart(6);
			} else if(!h.checkPartBuilt(7)){
				h.buildPart(7);
			} else if(!h.checkPartBuilt(8)){
				h.buildPart(8);
			}
		}
	}
	
	
	private void addFuel(Hand h){
		int amountOfFuel = calcFuelQuantity(h);
		for(int i = 0; i < amountOfFuel; i++){
			h.addCommodity(1);
		}
	}
	
	/**
	 * canBuildCockpit(Hand h)
	 * This method returns true if the player has the necessary
	 * resources in their Hand to build a cockpit upgrade, and
	 * false if they do not.
	 * @param Hand h
	 * @return true/false
	 */
	private boolean canBuildCockpit(Hand h){
		return (h.numberOfHullParts() >= 1 &&
				h.numberOfElectronics() >= 1 &&
				h.numberOfGlass() >= 1 &&
				h.numberOfCrewMembers() >= 1);
	}
	
	/**
	 * canBuildThruster(Hand h)
	 * This method returns true if the player has the necessary
	 * resources in their Hand to build a thruster upgrade, and
	 * false if they do not.
	 * @param Hand h
	 * @return true/false
	 */
	private boolean canBuildThruster(Hand h){
		return (h.numberOfHullParts() >= 2 &&
				h.numberOfElectronics() >= 1 &&
				h.numberOfFuel() >= 1);
	}
	
	/**
	 * canBuildFuelReserves(Hand h)
	 * This method returns true if the player has the necessary
	 * resources in their Hand to build a fuel reserve upgrade, and
	 * false if they do not.
	 * @param Hand h
	 * @return true/false
	 */
	private boolean canBuildFuelReserves(Hand h){
		return (h.numberOfHullParts() >= 1 &&
				h.numberOfElectronics() >= 1 &&
				h.numberOfFuel() >= 2);
	}
	
	/**
	 * canAddFuel(Hand h)
	 * This method returns true if the player has the necessary
	 * resources in their Hand to add unit(s) of fuel, and
	 * false if they do not.
	 * @param Hand h
	 * @return true/false
	 */
	private boolean canAddFuel(Hand h){
		return (h.numberOfFuel() >= 3 && h.checkPartBuilt(3));
	}
	
	/**
	 * canAddCrewMembers(Hand h)
	 * This method returns true if the player has the necessary
	 * resources in their Hand to add a crew member, and
	 * false if they do not.
	 * @param Hand h
	 * @return true/false
	 */
	private boolean canAddCrewMembers(Hand h){
		return ((h.numberOfCrewMembers() >= 3) && !h.checkCrewFull());
	}
	
	/**
	 * canAddCrewMembers(Hand h)
	 * This method returns an int representing the number of fuel
	 * units the user can add to their Scorecard based on the content
	 * of their Hand
	 * false if they do not.
	 * @param Hand h
	 * @return int (range: 0-3, inclusive)
	 */
	private int calcFuelQuantity(Hand h){
		if(h.numberOfFuel() < 3)
			return 0;
		if(h.numberOfFuel() == 3)
			return 1;
		else if(h.numberOfFuel() == 4)
			return 2;
		else if(h.numberOfFuel() == 5)
			return 3;
		else
			return 0;
	}
	
	private void switchToSpace() {
		activeWindow.switchPhases();
	}
	
}
