package game;

import ch.aplu.jcardgame.Hand;

public interface Player {
    public void operate();
    public boolean isHuman();
    public Hand getHand();
}
