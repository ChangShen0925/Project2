package game;

import ch.aplu.jcardgame.Hand;

import java.util.*;


public class ManipulatePlayer {

    private int playerNum;
    private int humanNum;
    private int npcNum;
    private Hand[] hands;
    private RevelentInformation information;
    private  Random random;
    private HashMap<Boolean, Integer> seed = new HashMap<>();
    private final int NUMBER_OF_FILTERING_METHOD = 3;
    private final int NUMBER_OF_SELECTING_METHOD = 3;

    private List<Player> players = new ArrayList<Player>();
    private ArrayList<Hand> CardInHand = new ArrayList<Hand>();
    public ManipulatePlayer(int playerNum, int humanNum, int npcNum, Hand[] hands, RevelentInformation information, HashMap<Boolean, Integer> seed){
        this.seed = seed;
        this.playerNum = playerNum;
        this.humanNum = humanNum;
        this.npcNum = npcNum;
        this.hands = hands;
        this.information = information;
    }

    public void init(int[] SelectMethod){
        SelectingStrategy[] SelectingMethod = new SelectingStrategy[]{new RandomSelectStrategy(information),
                new HighestSelectStrategy(information), new SmartSelectStrategy(information)};
        for(int i = 0; i < SelectingMethod.length; i++){
            if(SelectMethod[i] == -1){
                SelectMethod[i] = RandomNumberForSelecting();
            }
        }
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

    public void selectDecorator(NPC player, Suit lead, Suit trumps, int position){

        FilterNPC[] FilterMethod = new FilterNPC[]{player, new NaiveLegalDecorator(player, lead, trumps),
                new TrumpSavingDecorator(player, lead, trumps)};
        if(position == -1){
            position = RandomNumberForFiltering();
        }
        FilterMethod[position].CardSet();
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

    public int RandomNumberForFiltering(){
        if(seed.containsKey(true)){
            this.random = new Random((long) seed.get(true));
        }else{
            this.random = new Random();
        }
        return random.nextInt(NUMBER_OF_FILTERING_METHOD);
    }

    public int RandomNumberForSelecting(){
        if(seed.containsKey(true)){
            this.random = new Random((long) seed.get(true));
        }else{
            this.random = new Random();
        }
        return random.nextInt(NUMBER_OF_SELECTING_METHOD);
    }
}
