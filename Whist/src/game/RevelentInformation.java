package game;

import ch.aplu.jcardgame.*;
import ch.aplu.jgamegrid.*;

import java.util.ArrayList;

public class RevelentInformation {
    public ArrayList<Card> cards = new ArrayList<Card>();
    public Suit lead;
    public Suit trump;
    Hand[] hands = new Hand[4];
    private final Deck deck = new Deck(Suit.values(), Rank.values(), "cover");
    public void Initialise(){
        hands = deck.dealingOut(4, 13);
        for (Hand hand: hands){
            for (Card card: hand.getCardList()){
                cards.add(card);
            }
        }
    }

    public Suit getLead() {
        return lead;
    }

    public void setLead(Suit lead) {
        this.lead = lead;
    }

    public Suit getTrump() {
        return trump;
    }

    public void setTrump(Suit trump) {
        this.trump = trump;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void Remove(Card card){
        cards.remove(card);
    }
    public int SuitableCard(ArrayList<Card> HandCard){
        int HighestLead = 100;
        int HighestTrump = 100;
        int SmallestRank = 0;
        int LeadIndex = -1;
        int TrumpIndex = -1;
        int SmallestIndex = 0;
        Boolean HaveHigherLead = true;
        Boolean HaveHigherTrump = true;
        int index = 0;

        for(Card card: HandCard){
            if(card.getSuit() == lead && card.getRankId() < HighestLead && lead != null){
                HighestLead = card.getRankId();
                LeadIndex = index;

            }
            if(card.getSuit() == trump && card.getRankId() < HighestTrump){
                HighestTrump = card.getRankId();
                TrumpIndex = index;
            }
            if (card.getRankId() > SmallestRank){
                SmallestIndex = index;
                SmallestRank = card.getRankId();
            }
            index++;
        }
        for (Card card: cards){
            if((card.getSuit() == lead && card.getRankId() < HighestLead)  || lead == null || LeadIndex == -1){
                HaveHigherLead = false;
            }
            if((card.getSuit() == trump && card.getRankId() < HighestTrump) || TrumpIndex != -1){
                HaveHigherTrump = false;
            }
        }
        if (!HaveHigherLead && !HaveHigherTrump){
            return SmallestIndex;
        }else if(HaveHigherLead){
            return LeadIndex;
        }else{
            return TrumpIndex;
        }
    }

}
