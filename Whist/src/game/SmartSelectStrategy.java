package game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Deck;
import ch.aplu.jgamegrid.*;

import java.util.ArrayList;

public class SmartSelectStrategy implements SelectingStrategy{
    public RevelentInformation information;
    public SmartSelectStrategy(RevelentInformation information){
        this.information = information;
    }
    @Override
    public Card SelectCard(ArrayList<Card> cards) {
        return cards.get(information.SuitableCard(cards));
    }
}
