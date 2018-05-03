/**
    SpacePhase testing
 */
public class Test {
    private Hand[] players;

    public static void main(String[] args) {

        Hand[] players = new Hand[3];
        for(int i = 0; i < players.length; i++) {
            players[i] = new Hand();
        }

        // PLAYER 1
        // crew
        players[0].addCommodity(0);
        players[0].addCommodity(0);

        // fuel
        players[0].addCommodity(1);
        players[0].addCommodity(1);
        players[0].addCommodity(1);
        players[0].addCommodity(1);

        // rocket tier
        players[0].buildPart(0);
        players[0].buildPart(1);

        // complete rocket
        players[0].buildPart(3);
        players[0].buildPart(4);
        players[0].buildPart(5);
        players[0].buildPart(6);
        players[0].buildPart(7);
        players[0].buildPart(8);
        players[0].buildPart(9);

        // PLAYER 2
        // crew
        players[1].addCommodity(0);
        players[1].addCommodity(0);

        // fuel
        players[1].addCommodity(1);
        players[1].addCommodity(1);
        players[1].addCommodity(1);
        players[1].addCommodity(1);

        // rocket tier
        players[1].buildPart(0);
        players[1].buildPart(1);

        // complete rocket
        players[1].buildPart(3);
        players[1].buildPart(4);
        players[1].buildPart(5);
        players[1].buildPart(6);
        players[1].buildPart(7);
        players[1].buildPart(8);
        players[1].buildPart(9);

        // PLAYER 3
        // crew
        players[2].addCommodity(0);
        players[2].addCommodity(0);

        // fuel
        players[2].addCommodity(1);
        players[2].addCommodity(1);
        players[2].addCommodity(1);
        players[2].addCommodity(1);

        // rocket tier
        players[2].buildPart(0);
        players[2].buildPart(1);

        // complete rocket
        players[2].buildPart(3);
        players[2].buildPart(4);
        players[2].buildPart(5);
        players[2].buildPart(6);
        players[2].buildPart(7);
        players[2].buildPart(8);
        players[2].buildPart(9);

        SpacePhase spaceship = new SpacePhase(players);
    }
}
