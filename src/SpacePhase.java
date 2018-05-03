/**
 * class SpacePhase
 *  cautious: distance * 1   fuel -2    accident 0%
 *  normal:   distance * 1   fuel -1    accident 20% - crew members * 4
 *  fast:     distance * 3   fuel -2    accident (20% - crew members * 4) * 2
 */

import java.awt.Color;
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
    public SpacePhase(Hand[] players, PlayGame activeWindow){
    	super("space.jpg");
    	this.players = players;
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
      	output.setEditable(false);
    	
    	this.add(cautious);
    	this.add(normal);
    	this.add(fast);
    	this.add(proceed);
    	this.add(fuelLeft);
    	this.add(distance);
    	this.add(output);
    	this.add(launch);
    	this.add(currSpeed);
    	this.add(curAccident);
    	currentPlayer = -1;
    	advancePlayer();
    }

    
    private void advancePlayer() {
    	if(currentPlayer < players.length - 1) {
    		currentPlayer++;
    		curHand = players[currentPlayer];
    		tempScore = curHand.readScore();
    		accidentChance = 20 - (tempScore[0] * 4);
    		rocketTier = tempScore[2];
    		distance.setText("Current Distance: " + currentDistance);
    		fuelLeft.setText("Current fuel: " + tempScore[1]);
    		curAccident.setText("Accident Change at current speed: " + accidentChance);
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
    			output.setText("Too bad Player " + (currentPlayer + 1) + ", you didn't complete your rocket. Please click proceed.");
    			proceed.setEnabled(true);
    			proceed.setBackground(Color.green);
    			normal.setEnabled(false);
    			cautious.setEnabled(false);
    			fast.setEnabled(false);
    			launch.setEnabled(false);
    		}
    	} else {
    		activeWindow.endGame();
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
    
    private void flyTurn() {
    	int distanceNow = calculateTotalDistance(curSpeed);
    	boolean accidentNow = calculateAccident(curSpeed);
    	int fuelLost = calculateFuel(curSpeed);
    	if(accidentNow) {
    		output.setText("You had an accident! You gain no distance but still burn fuel.");
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
    		curHand.recordFinalDistance(totalDistance);
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
				curAccident.setText("Accident Change at current speed: " + (accidentChance * 2) + "%");
			}
			if(e.getSource() == cautious) {
				curSpeed = SpeedTier.CAUTIOUS;
				currSpeed.setText("Current Speed: " + curSpeed);
				curAccident.setText("Accident Change at current speed: 0%");
			}
			if(e.getSource() == normal) {
				curSpeed = SpeedTier.NORMAL;
				currSpeed.setText("Current Speed: " + curSpeed);
				curAccident.setText("Accident Change at current speed: " + accidentChance + "%");
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