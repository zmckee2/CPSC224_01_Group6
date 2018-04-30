import sun.jvm.hotspot.memory.Space;

public class Test {
    private Hand[] players;

    public static void main(String[] args) {

        Hand[] players = new Hand[2];
        for(int i = 0; i < 2; i++) {
            players[i] = new Hand();
        }

        players[0].buildPart(0);
        players[0].buildPart(1);
        players[0].addCommodity(0);
        players[0].addCommodity(0);
        players[0].addCommodity(1);
        players[0].addCommodity(1);
        players[0].addCommodity(1);
        players[0].addCommodity(1);
        int[] temp = players[0].readScore();
        System.out.println(temp[2]);
        System.out.println(players[0].getNumberFuel());

    }
}
