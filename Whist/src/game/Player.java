package game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;

public interface Player {
    public void operate();
    public boolean isHuman();
    public Hand getHand();
    public ArrayList<Card> getCardsList();
}
