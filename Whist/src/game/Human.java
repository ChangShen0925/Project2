package game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;

public class Human implements Player {

    private Hand hand;

    public ArrayList<Card> cards = new ArrayList<Card>();

    public Human(Hand hand) {
        this.hand = hand;
    }

    @Override
    public void operate(){
        hand.setTouchEnabled(true);
    }

    @Override
    public boolean isHuman(){
        return true;
    }

    @Override
    public Hand getHand(){
        return hand;
    }

    public ArrayList<Card> getCardsList(){
        return cards;
    }

}
