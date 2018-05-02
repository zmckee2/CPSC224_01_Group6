/**
 * class SpacePhase
 *  cautious: distance * 1   fuel -2    accident 0%
 *  normal:   distance * 1   fuel -1    accident 20% - crew members * 4
 *  fast:     distance * 3   fuel -2    accident (20% - crew members * 4) * 2
 */

import java.util.Scanner;

public class SpacePhase {
    private int totalDistance;
    private int totalCrew;
    private int totalFuel;
    private int rocketTier;
    private int[] tempScore;

    public enum SpeedTier {CAUTIOUS, NORMAL, FAST}

    private double accidentChance;
    private boolean accident;

    private int speedOption;

    /**
     * SpacePhase constructor
     *
     * @param: players is the array of Hand objects with each object
     * corresponding to each player
     * @return: n/a
     */
    public SpacePhase(Hand[] players) {

        for (int i = 0; i < players.length; i++) {
            System.out.println("\nPLAYER " + (i+1) +"\n");
            if(players[i].checkRocketBuilt()) {
                tempScore = players[i].readScore();
                totalCrew = tempScore[0];
                totalFuel = tempScore[1];
                rocketTier = tempScore[2];
                totalDistance = 0;
                speedOption = 0;

                System.out.println("--- Player " + (i+1) + "'s  Rocket ---" );
                System.out.println("total crew: " + totalCrew);
                System.out.println("total fuel: " + totalFuel);
                System.out.println("rocket tier: " + rocketTier);
                System.out.println("total distance: " + totalDistance);

                spaceTravel();
            }
            else {
                System.out.println("ERROR: ROCKET NOT COMPLETE");
            }
        }
    }

    /**
     * setPlayerSpeed() method
     * sets the player speed based upon user input: CAUTIOUS, NORMAL, or FAST
     * @param: n/a
     * @return: n/a
     */
    private SpeedTier setPlayerSpeed(){
        SpeedTier speedTier;
        do{
            Scanner keyboard = new Scanner(System.in);
            System.out.println(" --- Spaceship Speeds --- ");
            int option = 1;
            for (SpeedTier speed: SpeedTier.values()){
                System.out.println(option + " ~ " + speed.name());
                option++;
            }
            System.out.println("Please select a number corresponding to a speed setting: ");
            speedOption = keyboard.nextInt();
            speedTier = SpeedTier.values()[speedOption - 1];
            System.out.println("selected speed: " + speedTier);
        } while (speedOption == 0);
        return speedTier;
    }

    /**
     * calculateFuel() method
     * calculates the total amount of fuel remaining for each player
     * @param: speed of type SpeedTier is the speed each player chooses to travel in space at
     * @return: n/a
     */
    private int calculateFuel(SpeedTier speed){
        if (speed == SpeedTier.CAUTIOUS){
            totalFuel = totalFuel - 2;
            System.out.println("remaining fuel: " + totalFuel);
        }
        else if (speed == SpeedTier.NORMAL){
            totalFuel = totalFuel - 1;
            System.out.println("remaining fuel: " + totalFuel);
        }
        else if (speed == SpeedTier.FAST){
            totalFuel = totalFuel - 2;
            System.out.println("remaining fuel: " + totalFuel);
        }
        else {
            totalFuel = 0;
        }
        return totalFuel;
    }

    /**
     * calculateAccident() method
     * calculates the chance of an accident for each player
     * @param: speed of type SpeedTier is the speed each player chooses to travel in space at
     * @return: n/a
     */
    private boolean calculateAccident(SpeedTier speed) {
        if (speed == SpeedTier.CAUTIOUS) {
            accidentChance = 0.0;
            System.out.println("accident chance: " + accidentChance + " %");
            if(Math.random() * 100 < accidentChance){
                System.out.println("GAME OVER: CRASH!");
                accident = true;
            }
        }
        else if (speed == SpeedTier.NORMAL) {
            accidentChance = (20 - (totalCrew * 4));
            System.out.println("accident chance: " + accidentChance + " %");
            if(Math.random() * 100 < accidentChance){
                System.out.println("GAME OVER: CRASH!");
                accident = true;
            }
        }
        else if (speed == SpeedTier.FAST){
            accidentChance = ((20 - (totalCrew * 4)) * 2);
            System.out.println("accident chance: " + accidentChance + " %");
            if(Math.random() * 100 < accidentChance){
                System.out.println("GAME OVER: CRASH!");
                accident = true;
            }
        }
        else { accident = false; }
        return accident;
    }

    /**
     * calculateTotalDistance() method
     * calculates the total distance each player travels
     * @param: speed of type SpeedTier is the speed each player chooses to travel in space at
     * @return: n/a
     */
    private int calculateTotalDistance(SpeedTier speed) {
        if (speed == SpeedTier.CAUTIOUS){
            totalDistance = totalDistance + rocketTier * 1;
            System.out.println("total distance: " + totalDistance);
        }
        else if (speed == SpeedTier.NORMAL){
            totalDistance = totalDistance + rocketTier * 1;
            System.out.println("total distance: " + totalDistance);
        }
        else if (speed == SpeedTier.FAST){
            totalDistance = totalDistance + rocketTier * 3;
            System.out.println("total distance: " + totalDistance);
        }
        else {
            totalDistance = 0;
        }
        return totalDistance;
    }

    /**
     * spaceTravel() method
     * calls calculateFuel(), calculateAccident(), and calculateTotalDistance for each player
     * spaceTravel() is the driver function for SpacePhase
     * @param: n/a
     * @return: n/a
     */
    private int spaceTravel(){
        int finalDistance = 0;
        int remainingFuel;
        boolean crashAccident;

        do {
            SpeedTier speed = setPlayerSpeed();
            if (speed == SpeedTier.CAUTIOUS) {
                remainingFuel = calculateFuel(SpeedTier.CAUTIOUS);
                crashAccident = calculateAccident(SpeedTier.CAUTIOUS);
                finalDistance = calculateTotalDistance(SpeedTier.CAUTIOUS);
            } else if (speed == SpeedTier.NORMAL) {
                remainingFuel = calculateFuel(SpeedTier.NORMAL);
                crashAccident = calculateAccident(SpeedTier.NORMAL);
                finalDistance = calculateTotalDistance(SpeedTier.NORMAL);
            } else if (speed == SpeedTier.FAST) {
                remainingFuel = calculateFuel(SpeedTier.FAST);
                crashAccident = calculateAccident(SpeedTier.FAST);
                finalDistance = calculateTotalDistance(SpeedTier.FAST);
            } else {
                return 0;
            }
        } while (remainingFuel > 0 && !crashAccident);

        return finalDistance;
    }
}