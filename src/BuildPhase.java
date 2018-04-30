import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.awt.color.*;




public class BuildPhase extends JPanel{
	
	// fields for array of Hands, current Hand during turn, and number of players
	Hand[] players;
	Hand curHand;
	int numPlayers;
	
	//init all buttons
	JButton ckpt1,ckpt2,ckpt3,thrst1,thrst2,thrst3,fres1,fres2,fres3,
			addCrewMem,addFuel,die1,die2,die3,die4,die5,reroll,proceed;
	
	//init all labels
	JLabel numCrewMem,maxCrewMem,numFuel;
	
	// dimension for Dice
	Dimension dieDim = new Dimension(50,50);
	
	//setting button color defaults
	Color red_btn = new Color(239,95,95);
	Color blu_btn = new Color(111, 220, 237);
	Color grn_btn = new Color(97,244,132);
	
	/**
	 * BuildPhase(Hand[] initPlayers)
	 * This is the constructor for BuildPhase
	 * @param array of Hands representing different players
	 */
	public BuildPhase(Hand[] initPlayers){
		players = initPlayers;
		numPlayers = players.length;
		
		this.setLayout(new BorderLayout());
		JPanel scrb = new JPanel();
		scrb.setLayout(new GridBagLayout());
		JPanel dicePanel = new JPanel();
		dicePanel.setLayout(new GridBagLayout());
		
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
		
		
		
		
		numCrewMem = new JLabel("Number of Crew Members: 0");
		maxCrewMem = new JLabel("Max Number of Crew Members: 0");
		addCrewMem = new JButton("Add Crew Member");
		numFuel = new JLabel("Units of Fuel: 0");
		addFuel = new JButton("Add Fuel");
		ckpt1 = new JButton("Cockpit Mk1");
		ckpt2 = new JButton("Cockpit Mk2");
		ckpt3 = new JButton("Cockpit Mk3");
		thrst1 = new JButton("Thruster Mk1");
		thrst2 = new JButton("Thruster Mk2");
		thrst3 = new JButton("Thruster Mk3");
		fres1 = new JButton("Fuel Reserves Mk1");
		fres2 = new JButton("Fuel Reserves Mk2");
		fres3 = new JButton("Fuel Reserves Mk3");
		
		die1 = new JButton("ELEC");
		die2 = new JButton("CREW");
		die3 = new JButton("FUEL");
		die4 = new JButton("HULL");
		die5 = new JButton("GLAS");
		reroll = new JButton("REROLL");
		proceed = new JButton("PROCEED TO SPACE PHASE");
		
		/*JButton[] btns = {ckpt1,ckpt2,ckpt3,thrst1,thrst2,thrst3,fres1,fres2,fres3,
				addCrewMem,addFuel,die1,die2,die3,die4,die5,reroll,proceed};
		
		JButton[] dice = {die1,die2,die3,die4,die5};*/
		
		ListenForButton lForBtn = new ListenForButton();
		
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
		gCon.gridx = 3;
		gCon.gridy = 4;
		scrb.add(reroll, gCon);
		gCon.gridx = 4;
		scrb.add(proceed, gCon);
		
		
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
		
		//proceed.setVisible(false);// set PROCEED button to be hidden
		die1.setVisible(true);
		scrb.setVisible(true);
		dicePanel.setVisible(true);
		this.add(scrb, BorderLayout.NORTH);
		this.add(dicePanel, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	/**
	 * playBuild()
	 * This method runs through 10 turns for all players, during which
	 * players will build their space ship.
	 */
	public void playBuild(){ // this is basically the main method of BuildPhase
		for(int i = 0; i < 10; i++){//loop for 10 turns
			for(int j = 0; j < players.length; j++){
				curHand = players[j];
				boolean continueRolling = true;
				//roll all 5 dice
				rollResources(curHand, true, true, true, true, true);
				for(int k = 0; k < 3 && continueRolling; k++){// roll 3 times
					//display results on Dice buttons
					JButton[] playerDice = {die1, die2, die3, die4, die5};
					for(int dieInd = 0; dieInd < 5; dieInd++) {
						if(curHand.playerResources[dieInd] == Hand.Resources.Electronics) {
							playerDice[dieInd].setText("ELEC");
						} else if(curHand.playerResources[dieInd] == Hand.Resources.Fuel) {
							playerDice[dieInd].setText("FUEL");
						} else if(curHand.playerResources[dieInd] == Hand.Resources.Glass) {
							playerDice[dieInd].setText("GLAS");
						} else if(curHand.playerResources[dieInd] == Hand.Resources.Hull_Parts) {
							playerDice[dieInd].setText("ELEC");
						} else if(curHand.playerResources[dieInd] == Hand.Resources.Crew_Member) {
							playerDice[dieInd].setText("CREW");
						} else {
							playerDice[i].setText("N//A");
						}
					}
					
					
					
					
				}
				// show player what the options are for scoring
				updateBuildOptions(players[j]);
				
			}
		}
	}
	
	/**
	 * rollResources(Hand h, boolean a, boolean b, boolean c, boolean d, boolean e)
	 * This method rerolls the resources in the players Hand based on which booleans
	 * are true and which are false (false = no reroll, true = reroll resource)
	 * @param Hand h, booleans a-e
	 */
	private void rollResources(Hand h, boolean a, boolean b, boolean c, boolean d, boolean e){ // 
		if(a)
			h.playerResources[0].randomResource();
		if(b)
			h.playerResources[1].randomResource();
		if(c)
			h.playerResources[2].randomResource();
		if(d)
			h.playerResources[3].randomResource();
		if(e)
			h.playerResources[4].randomResource();
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
			if(!h.checkCrewFull()){
				addCrewMem.setBackground(grn_btn);
			} else {
				addCrewMem.setBackground(blu_btn);
			}
		} else {
			addCrewMem.setBackground(red_btn);
		}
		
		if(canAddFuel(h)){
			int amountOfFuel = calcFuelQuantity(h);
			addFuel.setBackground(grn_btn);
		} else {
			addFuel.setBackground(red_btn);
		}
		
		//update number of commodities & max fuel labels
		// h.handScorecard.commodities[0] is Crew Members
		// h.handScorecard.commodities[1] is Fuel
		
		//numCrewMem = new JLabel("Number of Crew Members: " + h.handScorecard.getNumberCrewMembers());
		//maxCrewMem = new JLabel("Max Number of Crew Members: " + h.handScorecard.getMaxNumberCrewMembers());
		//numFuel = new JLabel("Units of Fuel: " + h.handScorecard.getNumberFuel());
		
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
				}
			}
			if(e.getSource() == ckpt2) {
				if(canBuildCockpit(curHand) && !curHand.checkPartBuilt(7)){
					buildCockpit(curHand);
				}
			}
			if(e.getSource() == ckpt3) {
				if(canBuildCockpit(curHand) && !curHand.checkPartBuilt(8)){
					buildCockpit(curHand);
				}
			}
			if(e.getSource() == thrst1) {
				if(canBuildThruster(curHand) && !curHand.checkPartBuilt(0)){
					buildThruster(curHand);
				}
			}
			if(e.getSource() == thrst2) {
				if(canBuildThruster(curHand) && !curHand.checkPartBuilt(1)){
					buildThruster(curHand);
				}
			}
			if(e.getSource() == thrst3) {
				if(canBuildThruster(curHand) && !curHand.checkPartBuilt(2)){
					buildThruster(curHand);
				}
			}
			if(e.getSource() == fres1) {
				if(canBuildFuelReserves(curHand) && !curHand.checkPartBuilt(3)){
					buildFuelReserves(curHand);
				}
			}
			if(e.getSource() == fres2) {
				if(canBuildFuelReserves(curHand) && !curHand.checkPartBuilt(4)){
					buildFuelReserves(curHand);
				}
			}
			if(e.getSource() == fres3) {
				if(canBuildFuelReserves(curHand) && !curHand.checkPartBuilt(5)){
					buildFuelReserves(curHand);
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
	
	/**
	 * addCrewMember(Hand h)
	 * Adds another Crew Member to the Scoreboard
	 * @param Hand h
	 */
	private void addCrewMember(Hand h){
		if(canAddCrewMembers(h)){
			if(!h.checkCrewFull()){
				h.addCommodity(0);
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
		return (h.numberOfHullParts() >= 1 &&
				h.numberOfElectronics() >= 1 &&
				h.numberOfFuel() >= 2);
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
		return (h.numberOfCrewMembers() >= 3);
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
	
}
