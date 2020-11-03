package game;
import ch.aplu.jcardgame.*;

import java.util.ArrayList;

public class FilteringDecorator implements FilterNPC{

    public FilterNPC npc;

    public FilteringDecorator(FilterNPC npc){
        this.npc = npc;
    }

    @Override
    public void filter(){
        npc.filter();
    }

}
