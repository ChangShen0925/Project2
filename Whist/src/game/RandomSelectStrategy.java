package game;

import ch.aplu.jcardgame.Card;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class RandomSelectStrategy extends SelectingStrategy{
    static final Random random = ThreadLocalRandom.current();

    public RandomSelectStrategy(RevelentInformation information){
        super(information);
    }

    @Override
    public Card SelectCard(ArrayList<Card> cards) {
        int x = random.nextInt(cards.size());

        return cards.get(x);
    }
}
