package game;

import ch.aplu.jcardgame.Card;

import java.util.ArrayList;

public class HighestSelectStrategy extends SelectingStrategy {

    public HighestSelectStrategy(RevelentInformation information){
        super(information);
    }

    @Override
    public Card SelectCard(ArrayList<Card> cards) {
        int max = cards.get(0).getRankId();
        int index = 0;
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getRankId() > max) {
                max = cards.get(i).getRankId();
                index = i;
            }
        }
        return cards.get(index);
    }
}
