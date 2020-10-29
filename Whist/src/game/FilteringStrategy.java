package game;
import ch.aplu.jcardgame.*;

import java.util.ArrayList;

public interface FilteringStrategy {
    public ArrayList<Card> CardSet(Hand hand);
}
