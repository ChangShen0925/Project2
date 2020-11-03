package game;

import ch.aplu.jcardgame.*;
import ch.aplu.jgamegrid.*;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

public class NPC extends Hand implements PlayerComponent{

    private SelectingStrategy selectingStrategy;
    private FilteringStrategy filteringStrategy;

    public ArrayList<Card> cards = new ArrayList<Card>();

    public NPC(Deck deck) {
        super(deck);
    }


    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public void Filtering(FilteringStrategy filteringStrategy, Hand hand){
         this.filteringStrategy = filteringStrategy;
         cards = filteringStrategy.CardSet(hand);
    }

    public Card Select(SelectingStrategy selectingStrategy){
        this.selectingStrategy = selectingStrategy;
        return selectingStrategy.SelectCard(cards);
    }

    @Override
    public void operate(){

    }
}
