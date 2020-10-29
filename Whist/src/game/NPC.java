package game;

import ch.aplu.jcardgame.*;
import ch.aplu.jgamegrid.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

public class NPC {
    public boolean RandomSelection = false;
    public boolean HighestSelection = true;
    public boolean SmartSelection = false;

    public boolean NaiveLegal = false;
    public boolean TrumpSaving = false;

    public ArrayList<Card> cards = new ArrayList<Card>();

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public void Filtering(Suit lead, Suit trump, Hand hand){
        FilteringStrategy filter;
         if (NaiveLegal){
             filter = new NaiveLegalStrategy(lead, trump);
         }
         else{
             filter = new TrumpSavingStrategy(lead, trump);
         }
        cards = filter.CardSet(hand);
    }

    public Card Select(RevelentInformation information){
        SelectingStrategy select;
        if(RandomSelection){
            select = new RandomSelectStrategy();
        }
        else if (HighestSelection){
            select = new HighestSelectStrategy();
        }
        else{
            select = new SmartSelectStrategy(information);
        }
        return select.SelectCard(cards);
    }

}
