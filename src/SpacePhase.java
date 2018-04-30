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

    // index of int array readScore()
    // 0: crew members
    // 1: fuel
    // 2:
    // constructor:
    //  with objects Hand[] - array of hands(each player),
    //      readScore()- returns array of integers representing ship parts

    // tiers of shipparts 1-3
    // increase fuel - static --> number turns
    // increase crew members - static --> decreases chance of accident
    // thrusters does affect distance --> base distance they move (Tier 1: move 1, Tier 2: move 2, Tier 3: move 3)


    // *fuel = how many turns in space phase
    // *crew = accident chance 1 crew = 20%, 2 crew = 15%
    //  random 0-100, check 0-20, 0-15, etc..

   // fuel = upper limit of for loop
   // accident = pick a move

   // fly space: tiers of thruster
    // cautious: distance * 1   fuel -2    accident 0%
    // normal:   distance * 1   fuel -1    accident 20% - crew members * 4
    // fast:     distance * 3   fuel -2    accident (20% - crew members * 4) * 2

    // make accident chance integer


    // flyspace() -- private
    //  cycle through each players
    //  if(players[0].checkRocketBuilt()) --> true, false: distance = 0
    //  else --> you can't fly into space

    //  .readScore() -> return [crewmembers, fuel, tier of thruster]

    //   while fuel != 0
    //   proceed cautious, normal, fast
    //   subtract fuel... until empty
    //   .recordfinaldistance()
    //   nextplayer.
    //

    // calculateAccident()
    // calculateDistance()

}