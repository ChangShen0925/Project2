package game;

import ch.aplu.jcardgame.*;
import ch.aplu.jgamegrid.*;

import java.util.ArrayList;

public class RevelentInformation {
    public ArrayList<Card> cards = new ArrayList<Card>();
    public Suit lead;
    public Suit trump;
    public void Initialise(ArrayList<Hand> hands){
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

    public void Remove(Card card){
        cards.remove(card);
    }
    public int SuitableCard(ArrayList<Card> HandCard){
        int HighestLead = HandCard.get(0).getRankId();
        int HighestTrump = HandCard.get(0).getRankId();
        int SmallestRank = HandCard.get(0).getRankId();;
        int LeadIndex = 0;
        int TrumpIndex = 0;
        int SmallestIndex = 0;
        Boolean HaveHigherLead = false;
        Boolean HaveHigherTrump = false;
        int index = 0;
        for(Card card: HandCard){
            if(card.getSuit() == lead && card.getRankId() > HighestLead){
                HighestLead = card.getRankId();
                LeadIndex = index;

            }else if(card.getSuit() == trump && card.getRankId() > HighestTrump){
                HighestTrump = card.getRankId();
                TrumpIndex = index;
            }else if (card.getRankId() < SmallestIndex){
                SmallestIndex = index;
            }
            index++;
        }
        for (Card card: cards){
            if(card.getSuit() == lead && card.getRankId() > HighestLead){
                HaveHigherLead = true;
            }else if(card.getSuit() == trump && card.getRankId() > HighestTrump){
                HaveHigherTrump = true;
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
