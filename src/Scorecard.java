/** Scorecard.java
 * 
 * @author Zach McKee, Kevin Hance, Andrew Yang
 *
 */
public class Scorecard {
	private boolean[] partsBuilt = new boolean[10]; //The first 9 elements are reserved for the rocket's parts.
											//The tenth element is reserved for if the rocket is complete
	private int[] commodities = new int[2]; //This is to keep track of the crew members and fuel
									//Index 0 is crew members, index 1 is fuel
	public Scorecard() {
		for(int i = 0; i < 10; i++) {
			partsBuilt[i] = false;
		}
		commodities[0] = 0;
		commodities[1] = 0;
	}
	private int finalDistance;
	
	/**
	 * checkPartBuilt
	 * This method checks to see if a part of the rocket has been built.
	 * @param index
	 * @return Returns true if the part at index has been built, else returns false
	 */
	public boolean checkPartBuilt(int index) {
		return partsBuilt[index];
	}
	
	/**
	 * checkCrewFull
	 * This method checks to see if ther are 4 crew members
	 * @param index
	 * @return Returns true if there are 4 crew members, else returns false
	 */
	public boolean checkCrewFull(int index ) { //Edit to make it dependent on cockpit size
		int currentCrew = commodities[0];
		return (currentCrew == 4);
	}
	
	/**
	 * addCommoditiy
	 * This method adds a commoditiy, fuel or crew members.
	 * Only to be called after checkCrewFull to insure <= 4 crew members
	 * @param index
	 */
	public void addCommodity(int index) {
		commodities[index]++;
	}

	/**
	 * buildPart
	 * This 
	 * @param index
	 */
	public void buildPart(int index) {
		partsBuilt[index] = true;
		if(index == 3) {
			commodities[1] += 5;
		} else if (index == 4) {
			commodities[1] += 2;
		} else if (index == 5) {
			commodities[1] += 3;
		} else if (index == 6) {
			commodities[0] +=1; 
		}
		
		partsBuilt[9] = (partsBuilt[0] && partsBuilt[3] && partsBuilt[6]);
	}

	/**
	 * recordFinalDistance
	 * This method returns the final distance for a player determined in space phase.
	 * Records said distance to the hand's scorecard
	 * @param int distance: The distance determined in spacephase
	 */
	public void recordFinalDistance(int distance) {
		finalDistance = distance;
	}
	
	//Will add documentation, refer to readScores in hand
	public int[] readScores() {
		int[] scores = new int[2];
		scores[0] = commodities[1]; //Crew members
		scores[1] = commodities[0]; //Fuel
		int thrusterTier = 1;
		if(partsBuilt[1])
			thrusterTier++;
			if(partsBuilt[2])
				thrusterTier++;
		scores[2] = thrusterTier;
		return scores;
	}
	
	//Will add documentation, refer to checkRocketBuilt in hand
	public boolean checkRocketComplete() {
		return partsBuilt[9];
	}
}
