package game;

import ch.aplu.jcardgame.*;


import java.util.ArrayList;


public interface SelectingStrategy {
    public Card SelectCard(ArrayList<Card> cards);
}
