/** Scorecard.java
 * 
 * @author Zach McKee, Kevin Hance, Andrew Yang
 *
 */
public class Scorecard {
	boolean[] partsBuilt = new boolean[10]; //The first 9 elements are reserved for the rocket's parts.
											//The tenth element is reserved for if the rocket is complete
	int[] commodities = new int[2]; //This is to keep track of the crew members and fuel
									//Index 0 is crew members, index 1 is fuel
	public Scorecard() {
		for(int i = 0; i < 10; i++) {
			partsBuilt[i] = false;
		}
	}
	
	public boolean checkPartBuilt(int index) {
		return partsBuilt[index];
	}
	
	public boolean checkCommodities(int index ) {
		if(index == 0) { //Check to make sure the crew members does not exceed 4
			return commodities[index] == 4;
		}				 //Check to make sure fuel does not exceed 10
		return commodities[index] == 10;
	}
	
	public void addCommodity(int index) {
		commodities[index]++;
	}

	public void buildPart(int index) {
		partsBuilt[index] = true;
	}

}
