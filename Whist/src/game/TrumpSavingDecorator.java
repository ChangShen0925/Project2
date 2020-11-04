package game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;

public class TrumpSavingDecorator extends FilteringDecorator {
    public Suit lead;
    public Suit trump;
    public ArrayList<Card> card;
    public TrumpSavingDecorator(ArrayList<Card> cards, Suit lead, Suit trump){
        super(cards);
        card = cards;
        this.lead = lead;
        this.trump = trump;
    }

    public ArrayList<Card> CardSet(){
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i = 0; i < card.size(); i++){
            if (card.get(i).getSuit() == lead){
                cards.add(card.get(i));
            }
        }
        if(cards.size() == 0) {
            for (int i = 0; i < card.size(); i++) {
                if (card.get(i).getSuit() == trump) {
                    cards.add(card.get(i));
                }
            }
        }else{
            return cards;
        }
        if(cards.size() == 0){
            return card;
        }else{
            return cards;
        }
    }

}
