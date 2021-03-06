package game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Deck;
import ch.aplu.jgamegrid.*;

import java.util.ArrayList;

public class SmartSelectStrategy extends SelectingStrategy{
    public SmartSelectStrategy(RevelentInformation information){
        super(information);
    }

    @Override
    public Card SelectCard(ArrayList<Card> cards) {
        return cards.get(information.SuitableCard(cards));
    }
}
