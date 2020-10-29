package game;
import ch.aplu.jcardgame.*;
import java.util.ArrayList;

public class NaiveLegalStrategy implements FilteringStrategy{
    public Suit lead;
    public Suit trump;
    public NaiveLegalStrategy(Suit lead, Suit trump){
        this.lead = lead;
        this.trump = trump;
    }
    public ArrayList<Card> CardSet(Hand hand){
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i = 0; i < hand.getCardList().size(); i++){
            if (hand.getCardList().get(i).getSuit() == lead || hand.getCardList().get(i).getSuit() == trump){
                cards.add(hand.getCardList().get(i));
            }
        }
        if(cards.size() == 0){
            return hand.getCardList();
        }else{
            return cards;
        }
    }
}
