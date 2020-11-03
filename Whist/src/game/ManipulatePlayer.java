package game;

import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;
import java.util.List;

public class ManipulatePlayer {

    private int playerNum;
    private int humanNum;
    private int npcNum;
    private Hand[] hands;

    private List<Player> players = new ArrayList<Player>();

    public ManipulatePlayer(int playerNum, int humanNum, int npcNum, Hand[] hands){
        this.playerNum = playerNum;
        this.humanNum = humanNum;
        this.npcNum = npcNum;
        this.hands = hands;
    }

    public void init(){

        for (int i = 0; i < playerNum; i ++) {
            if (humanNum > 0) {
                players.add(createNPC(hands[i]));
                humanNum --;
            }else if (npcNum > 0) {
                players.add(createHuman(hands[i]));
                npcNum --;
            }
        }
    }

    private NPC createNPC(Hand hand){
        return new NPC(hand, new RandomSelectStrategy());
    }

    private Human createHuman(Hand hand){
        return new Human(hand);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
