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
	
	Hand[] players;
	int numPlayers;
	
	JButton ckpt1,ckpt2,ckpt3,thrst1,thrst2,thrst3,fres1,fres2,fres3,
			addCrewMem,addFuel,die1,die2,die3,die4,die5,reroll,proceed;
	
	JLabel numCrewMem,maxCrewMem,numFuel;
	
	//JButton[] btns;
	
	//JButton[] dice;
	
	Dimension dieDim = new Dimension(50,50);
	
	Color red_btn = new Color(239,95,95);
	Color blu_btn = new Color(111, 220, 237);
	Color grn_btn = new Color(97,244,132);
	
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
		
		this.updateBuildOptions(players[0]);
		
		//proceed.setVisible(false);// set PROCEED button to be hidden
		die1.setVisible(true);
		scrb.setVisible(true);
		dicePanel.setVisible(true);
		this.add(scrb, BorderLayout.NORTH);
		this.add(dicePanel, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	public void playBuild(){ // this is basically the main method of BuildPhase
		for(int i = 0; i < 10; i++){//loop for 10 turns
			for(int j = 0; j < players.length; j++){
				// roll 3 times
				boolean continueRolling = true;
				for(int k = 0; k < 3 && continueRolling; k++){
					players[j].reroll(0);
					players[j].reroll(1);
					players[j].reroll(2);
					players[j].reroll(3);
					players[j].reroll(4);
					//if the player doesn't want to roll again, set continueRolling to false
					rollResources(players[j]);
				}
				updateBuildOptions(players[j]);
				
			}
		}
	}
	
	private void rollResources(Hand h){ // 
		for(int i = 0; i < 5; i++){
			h.playerResources[i].randomResource();
		}
	}
	
	/*private void swingInit(){
		this.setSize(700, 700); // parameter
		this.setVisible(true);
		
		Toolkit tk = Toolkit.getDefaultToolkit;
		Dimension dim = tk.getScreenSize();
		
		int xPos = (dim.width/2) - (this.getWidth()/2);
		int yPos = (dim.height/2) - (this.getHeight()/2);
		
		this.setLocation(xPos, yPos);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setTitle("SPACE RACE: Build Phase");
	}*/
	
	private void updateBuildOptions(Hand h){
		if(canBuildCockpit(h)){
			// Cockpit can be built
			if(!h.checkPartBuilt(6)){
				ckpt1.setBackground(grn_btn);
			} else if(!h.checkPartBuilt(7)){
				ckpt1.setBackground(blu_btn);
				ckpt2.setBackground(grn_btn);
			} else if(!h.checkPartBuilt(8)){
				ckpt1.setBackground(blu_btn);
				ckpt2.setBackground(blu_btn);
				ckpt3.setBackground(grn_btn);
			}
		} else {
			ckpt1.setBackground(red_btn);
			ckpt2.setBackground(red_btn);
			ckpt3.setBackground(red_btn);
		} if(canBuildThruster(h)){
			//Thruster can be built
			if(!h.checkPartBuilt(0)){ // first Thruster upgrade can be built
				thrst1.setBackground(grn_btn);
			} else if(!h.checkPartBuilt(1)){ // second Thruster upgrade can be built
				thrst1.setBackground(blu_btn);
				thrst2.setBackground(grn_btn);
			} else if(!h.checkPartBuilt(2)){ // third Thruster upgrade can be built
				thrst1.setBackground(blu_btn);
				thrst2.setBackground(blu_btn);
				thrst3.setBackground(grn_btn);
			}
		} else { // no Thruster upgrade can be built
				thrst1.setBackground(red_btn);
				thrst2.setBackground(red_btn);
				thrst3.setBackground(red_btn);
		} if(canBuildFuelReserves(h)){
			//fuel reserve can be built (UI stuff)
			if(!h.checkPartBuilt(3)){
				fres1.setBackground(grn_btn);
			} else if(!h.checkPartBuilt(4)){
				fres1.setBackground(blu_btn);
				fres2.setBackground(grn_btn);
			} else if(!h.checkPartBuilt(5)){
				fres1.setBackground(blu_btn);
				fres2.setBackground(blu_btn);
				fres3.setBackground(grn_btn);
			}
		} else {
			fres1.setBackground(red_btn);
			fres2.setBackground(red_btn);
			fres3.setBackground(red_btn);
		} if(canAddCrewMembers(h)){
			//1 crew member can be added (UI stuff)
			if(!h.checkCrewFull()){
				System.out.println("1 crew member can be added");
			}
		} if(canAddFuel(h)){
			int amountOfFuel = calcFuelQuantity(h);
			//<amountOfFuel> units of fuel can be added (UI stuff)
			System.out.println(amountOfFuel + " units of fuel can be added");
		}
	}
	
	private class ListenForButton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == ckpt1) {
				if(ckpt1.getBackground() == red_btn)
					ckpt1.setBackground(grn_btn);
				else
					ckpt1.setBackground(red_btn);
			}
		}
	}
	
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
	
	private void addCrewMember(Hand h){
		if(canAddCrewMembers(h)){
			//1 crew member can be added (UI stuff)
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
	
	private boolean canBuildCockpit(Hand h){
		return (h.numberOfHullParts() >= 1 &&
				h.numberOfElectronics() >= 1 &&
				h.numberOfGlass() >= 1 &&
				h.numberOfCrewMembers() >= 1);
	}
	
	private boolean canBuildThruster(Hand h){
		return (h.numberOfHullParts() >= 1 &&
				h.numberOfElectronics() >= 1 &&
				h.numberOfFuel() >= 2);
	}
	
	private boolean canBuildFuelReserves(Hand h){
		return (h.numberOfHullParts() >= 1 &&
				h.numberOfElectronics() >= 1 &&
				h.numberOfFuel() >= 2);
	}
	
	private boolean canAddFuel(Hand h){
		return (h.numberOfFuel() >= 3 && h.checkPartBuilt(3));
	}
	
	private boolean canAddCrewMembers(Hand h){
		return (h.numberOfCrewMembers() >= 3);
	}
	
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
