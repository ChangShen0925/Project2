package game;

import ch.aplu.jcardgame.*;


import java.util.ArrayList;


public abstract class SelectingStrategy {
    public RevelentInformation information;
    public SelectingStrategy(RevelentInformation information){
        this.information = information;
    }
    public abstract Card SelectCard(ArrayList<Card> cards);

}
