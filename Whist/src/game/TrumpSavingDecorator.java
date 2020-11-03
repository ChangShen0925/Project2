package game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;

public class TrumpSavingDecorator extends FilteringDecorator {
    public Suit lead;
    public Suit trump;
    public TrumpSavingDecorator(FilterNPC npc, Suit lead, Suit trump){
        super(npc);
        this.lead = lead;
        this.trump = trump;
    }

    public ArrayList<Card> CardSet(Hand hand){
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i = 0; i < hand.getCardList().size(); i++){
            if (hand.getCardList().get(i).getSuit() == lead){
                cards.add(hand.getCardList().get(i));
            }
        }
        if(cards.size() == 0) {
            for (int i = 0; i < hand.getCardList().size(); i++) {
                if (hand.getCardList().get(i).getSuit() == trump) {
                    cards.add(hand.getCardList().get(i));
                }
            }
        }else{
            return cards;
        }
        if(cards.size() == 0){
            return hand.getCardList();
        }else{
            return cards;
        }
    }

    @Override
    public void filter() {
        npc.filter();
        ((NPC)npc).setCards(CardSet(((NPC)npc).getHand()));
    }
}
