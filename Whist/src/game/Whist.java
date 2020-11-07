package game;

import ch.aplu.jcardgame.*;
import ch.aplu.jgamegrid.*;

import java.awt.Color;
import java.awt.Font;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Properties;

@SuppressWarnings("serial")
public class Whist extends CardGame {
		final String TRUMPIMAGE[] = {"bigspade.gif","bigheart.gif","bigdiamond.gif","bigclub.gif"};

  	static final Random RANDOM = ThreadLocalRandom.current();
  
  	// return random Enum value
  	public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
      	int x = RANDOM.nextInt(clazz.getEnumConstants().length);
      	return clazz.getEnumConstants()[x];
  	}
  
  	// return random Card from Hand
  	public static Card randomCard(Hand hand){
      	int x = RANDOM.nextInt(hand.getNumberOfCards());
      	return hand.get(x);
  	}
 
  	// return random Card from ArrayList
  	public static Card randomCard(ArrayList<Card> list){
      	int x = RANDOM.nextInt(list.size());
      	return list.get(x);
  	}
  
  	public boolean rankGreater(Card card1, Card card2) {
	  	return card1.getRankId() < card2.getRankId(); // Warning: Reverse rank order of cards (see comment on enum)
  	}
	 
  	private final String VERSION = "1.0";
  	public  int nbPlayers;
  	public int nbStartCards;
  	public int winningScore;
  	public int seedProp = -1;
  	public RevelentInformation information = new RevelentInformation();

  	private final int HANDWIDTH = 400;
  	private final int TRICKWIDTH = 40;
	private final static int WIDTH = 700;
  	private final static int HEIGHT = 700;
  	private final static int STATUS_HEIGHT = 30;
  	private final static int RIGHT_ANGLE = 90;
  	private final Deck DECK = new Deck(Suit.values(), Rank.values(), "cover");

  	private int humanNum;
  	private int npcNum;

  	private final Location[] HANDLOCATIONS = {
  			new Location(350, 625),
			new Location(75, 350),
			new Location(350, 75),
			new Location(625, 350)
  	};
  	private final Location[] SCORELOCATIONS = {
  			new Location(575, 675),
			new Location(25, 575),
			new Location(575, 25),
			new Location(650, 575)
  	};

  	private Actor[] scoreActors = {null, null, null, null };
  	private final Location TRICKLOCATION = new Location(350, 350);
  	private final Location TEXTLOCATION = new Location(350, 450);
  	private final int THINKINGTIME = 2000;
  	private final int HUMAN_DELAY_TIME = 100;
  	private final int TRICK_DELAY_TIME = 600;
  	private Hand[] hands;
  	private Location hideLocation = new Location(-500, - 500);
  	private Location trumpsActorLocation = new Location(50, 50);
  	private boolean enforceRules=false;


  	private int[] NPCSelectMethod;
  	private int[] NPCFilterMethod;
  	//stores the players
  	private ManipulatePlayer players;

  	//check wether the player is human or npc
	public static boolean isHuman = false;

  	public void setStatus(String string) { setStatusText(string); }
  	private int[] scores;
  	private Font bigFont = new Font("Serif", Font.BOLD, 36);

	private void initScore() {
		for (int i = 0; i < nbPlayers; i++) {
			scores[i] = 0;
		 	scoreActors[i] = new TextActor("0", Color.WHITE, bgColor, bigFont);
		 	addActor(scoreActors[i], SCORELOCATIONS[i]);
	 	}
  	}

	private void updateScore(int player) {
		removeActor(scoreActors[player]);
		scoreActors[player] = new TextActor(String.valueOf(scores[player]), Color.WHITE, bgColor, bigFont);
		addActor(scoreActors[player], SCORELOCATIONS[player]);
	}

	private Card selected;

	private void initRound() {
		 hands = DECK.dealingOut(nbPlayers, nbStartCards); // Last element of hands is leftover cards; these are ignored
		 for (int i = 0; i < nbPlayers; i++) {
			   hands[i].sort(Hand.SortType.SUITPRIORITY, true);
		 }
		 // Set up human player for interaction
		CardListener cardListener = new CardAdapter()  // Human Player plays card
			    {
			      public void leftDoubleClicked(Card card) { selected = card; hands[0].setTouchEnabled(false); }
			    };
		 for (int i = 0; i < humanNum; i++){
			 hands[i].addCardListener(cardListener);
		 }

		 // graphics
	    RowLayout[] layouts = new RowLayout[nbPlayers];
	    for (int i = 0; i < nbPlayers; i++) {
	      	layouts[i] = new RowLayout(HANDLOCATIONS[i], HANDWIDTH);
	      	layouts[i].setRotationAngle(RIGHT_ANGLE * i);
	      	// layouts[i].setStepDelay(10);
	      	hands[i].setView(this, layouts[i]);
	      	hands[i].setTargetArea(new TargetArea(TRICKLOCATION));
	      	hands[i].draw();
	    }
	    for (int i = humanNum; i < nbPlayers; i++) {  // This code can be used to visually hide the cards in a hand (make them face down)
			hands[i].setVerso(true);
		}
		information.Initialise();
	    HashMap<Boolean, Integer> seedMap = new HashMap<>();
	    if(seedProp == -1){
	    	seedMap.put(false, 0);
		}else{
	    	seedMap.put(true,seedProp);
		}
	    seedProp = seedMap.get(true);
		players = new ManipulatePlayer(nbPlayers, humanNum, npcNum, hands, information, seedMap);
	    players.init(NPCSelectMethod);
	}

	private String printHand(ArrayList<Card> cards) {
		String out = "";
		for(int i = 0; i < cards.size(); i++) {
			out += cards.get(i).toString();
			if(i < cards.size()-1) out += ",";
		}
		return(out);
	}

 	private Optional<Integer> playRound() {  // Returns winner, if any
		// Select and display trump suit
		final Suit trumps = randomEnum(Suit.class);
		final Actor trumpsActor = new Actor("sprites/"+ TRUMPIMAGE[trumps.ordinal()]);
	    addActor(trumpsActor, trumpsActorLocation);
		// End trump suit
		Hand trick;
		int winner;
		Card winningCard;
		Suit lead;

		int nextPlayer = RANDOM.nextInt(nbPlayers); // randomly select player to lead for this round

		information.setTrump(trumps);
		for (int i = 0; i < nbStartCards; i++) {
			trick = new Hand(DECK);
    		selected = null;
        	if (players.getPlayers().get(nextPlayer).isHuman()) {  // Select lead depending on player type
    			players.getPlayers().get(nextPlayer).operate();
    			setStatus("Player " + nextPlayer +  " double-click on card to lead.");
    			while (null == selected) delay(HUMAN_DELAY_TIME);
        	} else {
    			setStatusText("Player " + nextPlayer + " thinking...");
    			delay(THINKINGTIME);
				((NPC)players.getPlayers().get(nextPlayer)).setCards(players.getPlayers().get(nextPlayer).getHand().getCardList());
				players.getPlayers().get(nextPlayer).operate();
            	selected = ((NPC)players.getPlayers().get(nextPlayer)).getCurrentCard();
        	}


        	// Lead with selected card
			trick.setView(this, new RowLayout(TRICKLOCATION, (trick.getNumberOfCards()+2)* TRICKWIDTH));
        	trick.draw();
			selected.setVerso(false);
			// No restrictions on the card being lead
			lead = (Suit) selected.getSuit();
			selected.transfer(trick, true); // transfer to trick (includes graphic effect)
			winner = nextPlayer;
			winningCard = selected;
			System.out.println("New trick: Lead Player = "+nextPlayer+", Lead suit = "+selected.getSuit()+", Trump suit = "+trumps);
			System.out.println("Player "+nextPlayer+" play: "+selected.toString()+" from ["+printHand(players.getPlayers().get(nextPlayer).getHand().getCardList())+"]");
			// End Lead
			information.setLead(lead);
			information.Remove(selected);
			for (int j = 1; j < nbPlayers; j++) {
				if (++nextPlayer >= nbPlayers) nextPlayer = 0;  // From last back to first
				selected = null;
	        	if (players.getPlayers().get(nextPlayer).isHuman()) {
					players.getPlayers().get(nextPlayer).operate();
					setStatus("Player " + nextPlayer +  " double-click on card to lead.");
	    			while (null == selected) delay(HUMAN_DELAY_TIME);
	        	} else {
		        	setStatusText("Player " + nextPlayer + " thinking...");
					delay(THINKINGTIME);

					//filtering the cards
					players.selectDecorator((NPC)players.getPlayers().get(nextPlayer), lead, trumps, nextPlayer - humanNum);

					//run npc
					players.getPlayers().get(nextPlayer).operate();
					//update current selected cards
					selected = ((NPC)players.getPlayers().get(nextPlayer)).getCurrentCard();
	        	}
				information.Remove(selected);
	        	// Follow with selected card
		        trick.setView(this, new RowLayout(TRICKLOCATION, (trick.getNumberOfCards()+2)* TRICKWIDTH));
				trick.draw();
				selected.setVerso(false);  // In case it is upside down
				// Check: Following card must follow suit if possible
				if (selected.getSuit() != lead && players.getPlayers().get(nextPlayer).getHand().getNumberOfCardsWithSuit(lead) > 0) {
					// Rule violation
					String violation = "Follow rule broken by player " + nextPlayer + " attempting to play " + selected;
					//System.out.println(violation);
					if (enforceRules)
						try {
							throw(new BrokeRuleException(violation));
						} catch (BrokeRuleException e) {
							e.printStackTrace();
							System.out.println("A cheating player spoiled the game!");
							System.exit(0);
						}
				}
				// End Check
				 selected.transfer(trick, true); // transfer to trick (includes graphic effect)
				 System.out.println("Winning card: "+winningCard.toString());
				 System.out.println("Player "+nextPlayer+" play: "+selected.toString()+" from ["+printHand(players.getPlayers().get(nextPlayer).getHand().getCardList())+"]");
				 if ( // beat current winner with higher card
					 (selected.getSuit() == winningCard.getSuit() && rankGreater(selected, winningCard)) ||
					  // trumped when non-trump was winning
					 (selected.getSuit() == trumps && winningCard.getSuit() != trumps)) {
					 winner = nextPlayer;
					 winningCard = selected;
				 }
			// End Follow
			}
			delay(TRICK_DELAY_TIME);
			trick.setView(this, new RowLayout(hideLocation, 0));
			trick.draw();
			nextPlayer = winner;
			System.out.println("Winner: "+winner);
			setStatusText("Player " + nextPlayer + " wins trick.");
			scores[nextPlayer]++;
			updateScore(nextPlayer);
			if (winningScore == scores[nextPlayer]) return Optional.of(nextPlayer);
		}
		removeActor(trumpsActor);
		return Optional.empty();
	}

	public void ReadPropertyFile() throws IOException {
		 Properties PropertyFile = new Properties();

		 FileReader inStream = null;
		 try{
		 	inStream = new FileReader("Whist/whist.properties");
		 	//inStream = new FileReader("Whist/legal.properties");
		 	//inStream = new FileReader("Whist/smart.properties");
		 }finally {
		 	if(inStream != null){
				PropertyFile.load(inStream);
		 		inStream.close();
			}
		 }
		 seedProp = Integer.parseInt(PropertyFile.getProperty("Seed"));
		 humanNum = Integer.parseInt(PropertyFile.getProperty("human"));
		 npcNum = Integer.parseInt(PropertyFile.getProperty("NPC"));
		 nbStartCards = Integer.parseInt(PropertyFile.getProperty("nbStartCard"));
		 winningScore = Integer.parseInt(PropertyFile.getProperty("winningScore"));
		 NPCFilterMethod = new int[npcNum];
		 NPCSelectMethod = new int[npcNum];

		 if(npcNum == 1){
		 	NPCFilterMethod[0] = Integer.parseInt(PropertyFile.getProperty("NPCOneFilter"));
		 	NPCSelectMethod[0] = Integer.parseInt(PropertyFile.getProperty("NPCOneSelect"));
		 }else if(npcNum == 2){
			 NPCFilterMethod[0] = Integer.parseInt(PropertyFile.getProperty("NPCOneFilter"));
			 NPCSelectMethod[0] = Integer.parseInt(PropertyFile.getProperty("NPCOneSelect"));

			 NPCFilterMethod[1] = Integer.parseInt(PropertyFile.getProperty("NPCTwoFilter"));
			 NPCSelectMethod[1] = Integer.parseInt(PropertyFile.getProperty("NPCTwoSelect"));
		 }else if(npcNum == 3){

			 NPCFilterMethod[0] = Integer.parseInt(PropertyFile.getProperty("NPCOneFilter"));
			 NPCSelectMethod[0] = Integer.parseInt(PropertyFile.getProperty("NPCOneSelect"));

			 NPCFilterMethod[1] = Integer.parseInt(PropertyFile.getProperty("NPCTwoFilter"));
			 NPCSelectMethod[1] = Integer.parseInt(PropertyFile.getProperty("NPCTwoSelect"));

			 NPCFilterMethod[2] = Integer.parseInt(PropertyFile.getProperty("NPCThreeFilter"));
			 NPCSelectMethod[2] = Integer.parseInt(PropertyFile.getProperty("NPCThreeSelect"));
		 }else if(npcNum == 4){
			 NPCFilterMethod[0] = Integer.parseInt(PropertyFile.getProperty("NPCOneFilter"));
			 NPCSelectMethod[0] = Integer.parseInt(PropertyFile.getProperty("NPCOneSelect"));

			 NPCFilterMethod[1] = Integer.parseInt(PropertyFile.getProperty("NPCTwoFilter"));
			 NPCSelectMethod[1] = Integer.parseInt(PropertyFile.getProperty("NPCTwoSelect"));

			 NPCFilterMethod[2] = Integer.parseInt(PropertyFile.getProperty("NPCThreeFilter"));
			 NPCSelectMethod[2] = Integer.parseInt(PropertyFile.getProperty("NPCThreeSelect"));

			 NPCFilterMethod[3] = Integer.parseInt(PropertyFile.getProperty("NPCFourFilter"));
			 NPCSelectMethod[3] = Integer.parseInt(PropertyFile.getProperty("NPCFourSelect"));

		 }

		 nbPlayers = humanNum + npcNum;
		 scores = new int[nbPlayers];

	}

	public Whist() throws IOException{
    	super(WIDTH, HEIGHT, STATUS_HEIGHT);
    	setTitle("Whist (V" + VERSION + ") Constructed for UofM SWEN30006 with JGameGrid (www.aplu.ch)");
    	setStatusText("Initializing...");
		ReadPropertyFile();
    	initScore();
    	Optional<Integer> winner;
    	do {
      		initRound();
      		winner = playRound();
      		information.Initialise();
    	} while (!winner.isPresent());
    	addActor(new Actor("sprites/gameover.gif"), TEXTLOCATION);
    	setStatusText("Game over. Winner is player: " + winner.get());
    	refresh();
  	}

  	public static void main(String[] args) throws IOException
  {
  	new Whist();
  }

}
