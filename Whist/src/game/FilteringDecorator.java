package game;
import ch.aplu.jcardgame.*;

import java.util.ArrayList;

public abstract class FilteringDecorator implements FilterNPC{

    public NPC npc;
    public Suit lead;
    public Suit trump;

    public FilteringDecorator(NPC npc, Suit lead, Suit trump){
        this.npc = npc;
        this.lead = lead;
        this.trump = trump;
    }
}
