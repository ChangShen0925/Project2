package game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Deck;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;

public class Human extends Hand implements PlayerComponent {
    public ArrayList<Card> cards = new ArrayList<Card>();

    public Human(Deck deck) {
        super(deck);
    }

    @Override
    public void operate(){

    }
}
