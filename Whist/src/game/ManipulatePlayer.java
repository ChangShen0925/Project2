package game;

import ch.aplu.jcardgame.Hand;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class ManipulatePlayer {

    private int playerNum;
    private int humanNum;
    private int npcNum;
    private Hand[] hands;
    private RevelentInformation information;


    private List<Player> players = new ArrayList<Player>();
    private ArrayList<Hand> CardInHand = new ArrayList<Hand>();
    public ManipulatePlayer(int playerNum, int humanNum, int npcNum, Hand[] hands, RevelentInformation information){
        this.playerNum = playerNum;
        this.humanNum = humanNum;
        this.npcNum = npcNum;
        this.hands = hands;
        this.information = information;
    }

    public void init(int[] SelectMethod){
        SelectingStrategy[] SelectingMethod = new SelectingStrategy[]{new RandomSelectStrategy(information),
                new HighestSelectStrategy(information), new SmartSelectStrategy(information)};
        int HumanNumber = humanNum;
        for (int i = 0; i < playerNum; i ++) {
            CardInHand.add(hands[i]);
            if (humanNum > 0) {
                players.add(createHuman(hands[i]));
                humanNum --;
            }else if (npcNum > 0) {
                players.add(createNPC(hands[i], SelectingMethod[SelectMethod[i - HumanNumber]]));
                npcNum --;
            }
        }
    }

    private NPC createNPC(Hand hand, SelectingStrategy method){
        return new NPC(hand, method);
    }

    private Human createHuman(Hand hand){
        return new Human(hand);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
