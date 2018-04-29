/**
 * class SpacePhase
 */
public class SpacePhase {
    private int totalDistance;
    private int totalCrew;

    private Hand[] players;

    /**
     * SpacePhase constructor
     *
     * @param: players is the array of Hand objects with each object
     *  corresponding to each player
     * @return: n/a
     */
    public SpacePhase(Hand[] players){
        players[0].readScore();
        System.out.println(players[0].readScore());
    }

    // constructor:
    //  with objects Hand[] - array of hands(each player),
    //      readScore()- returns array of integers representing ship parts

    // tiers of shipparts 1-3
    // fuel reserves not going to affect distance
        // increase fuel - cannot be changed from what player has created
    // cockpit does not affect distance
        // increase crew members - cannot be changed from what player has created
    // *thrusters does affect distance

    // *fuel = how many turns in space phase
    // *crew = accident chance 1 crew = 20%, 2 crew = 15%
        // accident --> no distance traveled
        // random 0-100, check 0-20, 0-15, etc..


    // flyspace() -- private
    //  cycle through each players
    //  .checkRocketBuilt() --> true, false: distance = 0
    //  .readScore() -> return [fuel, crewmembers, returns tier of thruster]
    //  another while loop
    //   while fuel != 0
    //   proceed cautious, normal, fast
    //      cautious: no risk of accident, 2x fuel, distance = tier of thrusters
    //      normal: risk of accident (dependent upon crew members), 1x fuel, distance = tier of thrusters
    //      fast: 2x risk of accident, 2x fuel, distance = 3x tier of thrusters
    //   subtract fuel... until empty
    //   .record final distance()
    //   nextplayer.
    //
    //

    // check_fuel()
    // calculateAccident()
    // calculateDistance()



}


