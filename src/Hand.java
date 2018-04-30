import java.util.Arrays;
import java.util.Random;
public class Hand {
	public enum Resources {Fuel, Hull_Parts, Crew_Member, Electronics, Glass;
						   private static Random ran = new Random();
						   public static Resources randomResource() { //This method is used to get random resources
							   return Resources.values()[ran.nextInt(5)];};
						   }
	Resources[] playerResources;
	Scorecard handScorecard;
	private final int HAND_SIZE = 5;
	
	public Hand() {
		playerResources = new Resources[HAND_SIZE];
		for(int i = 0; i < HAND_SIZE; i++) {
			playerResources[i] = Resources.randomResource();
		}
		handScorecard = new Scorecard();
	}
	
	/**
	 * reroll()
	 * This method rerolls a die at the specified index
	 * @param index
	 */
	public void reroll(int index) {
		playerResources[index] = Resources.randomResource();
	}
	
	/**
	 * getDieAt()
	 * This method returns the resource at index
	 * @param index
	 * @return die at index
	 */
	public Resources getResourceAt(int index) {
		return playerResources[index];
	}
	
	/**
	 * toString()
	 * This method returns the hand in a printable format
	 * @return Hand in a string form
	 */
	public String toString() {
		StringBuilder a = new StringBuilder();
		for(int i = 0; i < HAND_SIZE; i++) {
			a.append(playerResources[i] + " ");
		}
		return a.toString();
	}
	
	/**
	 * sort()
	 * This method sorts the hand
	 */
	public void sort() {
		Arrays.sort(playerResources);
	}
	
	/**
	 * getHandSize()
	 * This method returns the size of the hand
	 * @return HAND_SIZE
	 */
	public int getHandSize() {
		return HAND_SIZE;
	}
	
	
	/**
	 * checkPartBuilt()
	 * This method returns whether a part has already been built on line index
	 * Returns true if the line is occupied
	 * @param index
	 * @return Whether the line is taken
	 */
	public boolean checkPartBuilt(int index) {
		return handScorecard.checkPartBuilt(index);
	}
	
	/**
	 * buildPart()
	 * This method builds a part by setting partsBuilt at index to true
	 * @param index
	 */
	public void buildPart(int index) {
		handScorecard.buildPart(index);
	}
	
	/**
	 * addCommoditiy()
	 * This method will add a commodity to the hand's scorecard. 0 adds crew member, 1 adds fuel
	 * @param index
	 */
	public void addCommodity(int index) {
		handScorecard.addCommodity(index);
	}
	
	/**
	 * getNumberCrewMembers()
	 * This method returns the number of crew members the hand currently has
	 * @return Number of crew members
	 */
	public int getNumberCrewMembers() {
		return handScorecard.getNumberCrewMembers();
	}
	
	/**
	 * getMaxNumberCrewMembers()
	 * This method returns the maximum amount of crew members the hand can hold.
	 * @return Maximum number of crew members
	 */
	public int getMaxNumberCrewMembers() {
		return handScorecard.getMaxNumberCrewMembers();
	}
	
	/**
	 * getNumberFuel()
	 * This method returns how much fuel the hand currently has
	 * @return Amount of fuel
	 */
	public int getNumberFuel() {
		return handScorecard.getNumberFuel();
	}
	
	/**
	 * checkCrewFull
	 * This method checks the hand's scorecard to make sure there are 3 or less crew members.
	 * @param index
	 * @return True if there are already 4 crew members, else returns false
	 */
	public boolean checkCrewFull() {
		return handScorecard.checkCrewFull();
	}
	
	/**
	 * recordFinalDistance
	 * This method returns the final distance for a player determined in space phase.
	 * Records said distance to the hand's scorecard
	 * @param int distance: The distance determined in spacephase
	 */
	public void recordFinalDistance(int distance) {
		handScorecard.recordFinalDistance(distance);
	}
	
	/**
	 * readScore
	 * Method to be used in spacephase. Returns an int array containing numbers needed in spacephase.
	 * scores[0] : Fuel, scores[1] : Crew Members, scores[2] : thruster tier
	 * @return int[] scores: defined above
	 */
	public int[] readScore() {
		return handScorecard.readScores();
	}
	
	
	/**
	 * checkRocketBuilt
	 * This method returns if the rocket is complete.
	 * A complete rocket has at least tier one of each rocket part (thruster, fuel reserves, cockpit)
	 * @return boolean rocketComplete: True if the rocket is completly built, false if not
	 */
	public boolean checkRocketBuilt() {
		return handScorecard.checkRocketComplete();
	}
	
	public int numberOfHullParts(){
		int num = 0;
		for(int i = 0; i < 5; i++){
			if(playerResources[i] == Resources.Hull_Parts)
				num++;
		}
		return num;
	}
	
	public int numberOfElectronics(){
		int num = 0;
		for(int i = 0; i < 5; i++){
			if(playerResources[i] == Resources.Electronics)
				num++;
		}
		return num;
	}
	
	public int numberOfGlass(){
		int num = 0;
		for(int i = 0; i < 5; i++){
			if(playerResources[i] == Resources.Glass)
				num++;
		}
		return num;
	}
	
	public int numberOfCrewMembers(){
		int num = 0;
		for(int i = 0; i < 5; i++){
			if(playerResources[i] == Resources.Crew_Member)
				num++;
		}
		return num;
	}
	
	public int numberOfFuel(){
		int num = 0;
		for(int i = 0; i < 5; i++){
			if(playerResources[i] == Resources.Fuel)
				num++;
		}
		return num;
	}
	
}

