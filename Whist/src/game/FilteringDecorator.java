package game;
import ch.aplu.jcardgame.*;

import java.util.ArrayList;

public class FilteringDecorator implements FilterNPC{

    public ArrayList<Card> cards;

    public FilteringDecorator(ArrayList<Card> cards){
        this.cards = cards;
    }

    @Override
    public ArrayList<Card> CardSet() {
        return cards;
    }
}
