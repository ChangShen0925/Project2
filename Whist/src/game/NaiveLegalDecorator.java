package game;
import ch.aplu.jcardgame.*;
import java.util.ArrayList;

public class NaiveLegalDecorator extends FilteringDecorator {

    public ArrayList<Card> card;
    public NaiveLegalDecorator(NPC npc, Suit lead, Suit trump){
        super(npc, lead, trump);
        this.card = npc.getHand().getCardList();
    }

    @Override
    public void CardSet(){
        npc.setCards(naiveLegalSet());
    }

    private ArrayList<Card>  naiveLegalSet(){
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i = 0; i < card.size(); i++){
            if (card.get(i).getSuit() == lead || card.get(i).getSuit() == trump){
                cards.add(card.get(i));
            }
        }
        if(cards.size() == 0){
            return card;
        }else{
            return cards;
        }
    }

}
