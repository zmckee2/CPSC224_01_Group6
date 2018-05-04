/**
 * SpacePhase.java
 * This class lets people play space phase in space race yahtzee
 * 
 * CPSC 224_01, Spring 2018
 * @author Andrew Yang, Zach McKee
 * @version 1.3 5/2/2018
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class SpacePhase extends PicturePanel{
    private int totalDistance;
    private int totalCrew;
    private int totalFuel;
    private int rocketTier;
    private int[] tempScore;
    private int currentPlayer;
    private int currentDistance;

    private SpeedTier curSpeed;
    
    private JButton cautious, normal, fast, proceed, launch;
    private JLabel fuelLeft, distance, curAccident, currSpeed;
    private Hand curHand;
    private JTextArea output;
    private Hand[] players;
    private PlayGame activeWindow;
    private double accidentChance;

    public enum SpeedTier {CAUTIOUS, NORMAL, FAST};
    

    /**
     * SpacePhase constructor
     *
     * @param: players is the array of Hand objects with each object
     * corresponding to each player
     * @return: n/a
     */
    public SpacePhase(Hand[] Iplayers, PlayGame activeWindow){
    	super("space.jpg");
    	players = Iplayers;
    	this.activeWindow = activeWindow;
    	cautious = new JButton("Cautious");
    	normal = new JButton("Normal");
    	fast = new JButton("Fast");
    	launch = new JButton("Launch with current speed");
    	proceed = new JButton("Proceed");
    	cautious.addActionListener(new buttonListener());
    	normal.addActionListener(new buttonListener());
    	fast.addActionListener(new buttonListener());
    	proceed.addActionListener(new buttonListener());
    	launch.addActionListener(new buttonListener());
    	distance = new JLabel();
    	fuelLeft = new JLabel();
    	curAccident = new JLabel();
    	currSpeed = new JLabel();
    	output = new JTextArea();
    	output.setOpaque(false);
    	Dimension size = new Dimension(800, 100);
    	output.setPreferredSize(size);
    	output.setFont(new Font("Hind", Font.PLAIN, 40));
    	output.setWrapStyleWord(true);
      	output.setEditable(false);
      	output.setForeground(Color.WHITE);
      	distance.setFont(new Font("Hind", Font.PLAIN, 20));
      	fuelLeft.setFont(new Font("Hind", Font.PLAIN, 20));
      	curAccident.setFont(new Font("Hind", Font.PLAIN, 16));
      	currSpeed.setFont(new Font("Hind", Font.PLAIN, 20));
      	cautious.setFont(new Font("Hind", Font.PLAIN, 20));
      	fast.setFont(new Font("Hind", Font.PLAIN, 20));
      	normal.setFont(new Font("Hind", Font.PLAIN, 20));
      	launch.setFont(new Font("Hind", Font.PLAIN, 20));
      	proceed.setFont(new Font("Hind", Font.PLAIN, 20));
      	distance.setForeground(Color.white);
      	fuelLeft.setForeground(Color.white);
      	curAccident.setForeground(Color.white);
      	currSpeed.setForeground(Color.white);
    	
      	JPanel firstFrame = new JPanel();
      	JPanel buttonFrame = new JPanel();
      	firstFrame.setOpaque(false);
      	buttonFrame.setOpaque(false);
      	buttonFrame.setLayout(new GridLayout(3,3,50,75));
      	firstFrame.setLayout(new BorderLayout());
    	buttonFrame.add(cautious);
    	buttonFrame.add(curAccident);
    	buttonFrame.add(distance);
    	buttonFrame.add(normal);
    	buttonFrame.add(launch);
    	buttonFrame.add(fuelLeft);
    	buttonFrame.add(fast);
    	buttonFrame.add(proceed);
    	buttonFrame.add(currSpeed);
    	firstFrame.add(output, BorderLayout.NORTH);
    	firstFrame.add(buttonFrame, BorderLayout.CENTER);
    	this.setLayout(new BorderLayout());
    	this.add(firstFrame, BorderLayout.CENTER);
    	currentPlayer = -1;
    	advancePlayer();
    }

    /**
     * advancePlayer
     * This method advances the current player and refreshes the GUI accordingly
     */
    private void advancePlayer() {
    	if(currentPlayer < players.length - 1) {
    		currentPlayer++;
    		curHand = players[currentPlayer];
    		tempScore = curHand.readScore();
    		accidentChance = 20 - (tempScore[0] * 4);
    		rocketTier = tempScore[2];
    		currentDistance = 0;
    		totalDistance = 0;
    		distance.setText("Current Distance: " + currentDistance);
    		fuelLeft.setText("Current fuel: " + tempScore[1]);
    		curAccident.setText("Accident chance at current speed: " + accidentChance + "%");
    		curSpeed = SpeedTier.NORMAL;
    		totalFuel = tempScore[1];
    		
    		currSpeed.setText("Current Speed: " + curSpeed);
    		if(curHand.checkRocketBuilt()) {
    			output.setText("Welcome Player " + (currentPlayer + 1) + ", please select a speed");
    			fast.setEnabled(true);
    			cautious.setEnabled(true);
    			normal.setEnabled(true);
    			proceed.setEnabled(false);
    			proceed.setBackground(null);
    			launch.setEnabled(true);
    		}
    		else {
    			output.setText("Too bad Player " + (currentPlayer + 1) + ", you didn't complete your rocket.\nPlease click proceed.");
    			players[currentPlayer].recordFinalDistance(0);
    			proceed.setEnabled(true);
    			proceed.setBackground(Color.green);
    			normal.setEnabled(false);
    			cautious.setEnabled(false);
    			fast.setEnabled(false);
    			launch.setEnabled(false);
    		}
    	} else {
    		int[] distances = new int[players.length];
    		int winner = 0;
    		for(int i = 0; i < players.length; i++) {
    			distances[i] = players[i].getFinalDistance();
    			if(players[i].getFinalDistance() > winner) {
    				winner = (i+1);
    			}
    		}
    		activeWindow.endGame(distances, winner);
    	}
    }

    /**
     * calculateFuel() method
     * calculates the total amount of fuel remaining for each player
     * @param: speed of type SpeedTier is the speed each player chooses to travel in space at
     * @return: n/a
     */
    private int calculateFuel(SpeedTier speed){
    	int fuelLoss = 0;
        if (speed == SpeedTier.CAUTIOUS){
            fuelLoss = 2;
        }
        else if (speed == SpeedTier.NORMAL){
            fuelLoss = 1;
        }
        else if (speed == SpeedTier.FAST){
            fuelLoss = 2;
        }
        return fuelLoss;
    }

    /**
     * calculateAccident() method
     * calculates the chance of an accident for each player
     * @param: speed of type SpeedTier is the speed each player chooses to travel in space at
     * @return: n/a
     */
    private boolean calculateAccident(SpeedTier speed) {
        if (speed == SpeedTier.CAUTIOUS) {
            if(Math.random() * 100 < accidentChance){
                return true;
            }
        }
        else if (speed == SpeedTier.NORMAL) {
            if(Math.random() * 100 < accidentChance){
                return true;
            }
        }
        else if (speed == SpeedTier.FAST){
            if(Math.random() * 100 < (accidentChance*2)){
                return true;
            }
        }
        return false;
    }

    /**
     * calculateTotalDistance() method
     * calculates the total distance each player travels
     * @param: speed of type SpeedTier is the speed each player chooses to travel in space at
     * @return: n/a
     */
    private int calculateTotalDistance(SpeedTier speed) {
    	int dhere = 0;
        if (speed == SpeedTier.CAUTIOUS){
            dhere = rocketTier * 1;
        }
        else if (speed == SpeedTier.NORMAL){
            dhere = rocketTier * 1;
        }
        else if (speed == SpeedTier.FAST){
            dhere = rocketTier * 3;
        }
        return dhere;
    }
    
    /**
     * flyTurn
     * This method runs through a turn of space phase given the current speed
     */
    private void flyTurn() {
    	int distanceNow = calculateTotalDistance(curSpeed);
    	boolean accidentNow = calculateAccident(curSpeed);
    	int fuelLost = calculateFuel(curSpeed);
    	if(accidentNow) {
    		output.setText("You had an accident! You gain no distance,\nbut still burn fuel.");
    	} else {
    		output.setText("Success! You go " + distanceNow + "km");
    		totalDistance += distanceNow;
    	}
    	totalFuel -= fuelLost;
    	fuelLeft.setText("Fuel remaining: " + totalFuel);
    	distance.setText("Current Distance: " + totalDistance);
    	if(totalFuel == 0) {
    		proceed.setEnabled(true);
    		proceed.setBackground(Color.GREEN);
    		output.setText("Player " + (currentPlayer + 1) + " went " + totalDistance + ", please click proceed to let the next player play.");
    		fast.setEnabled(false);
    		cautious.setEnabled(false);
    		normal.setEnabled(false);
    		launch.setEnabled(false);
    		players[currentPlayer].recordFinalDistance(totalDistance);
    	}
    	if(totalFuel == 1) {
    		fast.setEnabled(false);
    		cautious.setEnabled(false);
    		curSpeed = SpeedTier.NORMAL;
    		currSpeed.setText("Current Speed: " + curSpeed);
			curAccident.setText("Accident Change at current speed: " + accidentChance + "%");
    	}
    }
    
    private class buttonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == fast) {
				curSpeed = SpeedTier.FAST;
				currSpeed.setText("Current Speed: " + curSpeed);
				curAccident.setText("Accident Chance at current speed: " + (accidentChance * 2) + "%");
			}
			if(e.getSource() == cautious) {
				curSpeed = SpeedTier.CAUTIOUS;
				currSpeed.setText("Current Speed: " + curSpeed);
				curAccident.setText("Accident Chance at current speed: 0%");
			}
			if(e.getSource() == normal) {
				curSpeed = SpeedTier.NORMAL;
				currSpeed.setText("Current Speed: " + curSpeed);
				curAccident.setText("Accident Chance at current speed: " + accidentChance + "%");
			}
			if(e.getSource() == launch) {
				flyTurn();
			}
			if(e.getSource() == proceed) {
				advancePlayer();
			}
		}
    	
    }
}