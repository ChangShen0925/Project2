package game;

import ch.aplu.jcardgame.*;

import java.util.ArrayList;

public class NPC implements Player, FilterNPC{

    private SelectingStrategy selectingStrategy;

    private ArrayList<Card> cards = new ArrayList<Card>();

    private Hand hand;
    private Card currentCard;


    public NPC(Hand hand, SelectingStrategy selectingStrategy) {
        this.hand = hand;
        this.selectingStrategy = selectingStrategy;
    }

    public Card Select(){
        return selectingStrategy.SelectCard(cards);
    }

    @Override
    public void operate(){
        currentCard = Select();
    }

    @Override
    public boolean isHuman(){
        return false;
    }

    @Override
    public Hand getHand() {
        return hand;
    }

    @Override
    public void CardSet(){
        this.cards = this.hand.getCardList();
    }


    public Card getCurrentCard() {
        return currentCard;
    }

    public void setCards(ArrayList<Card> card) {
        this.cards = card;
    }

    public ArrayList<Card> getCardsList(){
        return cards;
    }
}
