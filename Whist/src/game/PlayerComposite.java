package game;

import java.util.ArrayList;
import java.util.List;

public class PlayerComposite implements PlayerComponent {

    //list stores all players and npc
    private List<PlayerComponent> list = new ArrayList<PlayerComponent>();

    public void add(PlayerComponent C){
        list.add(C);
    }

    public void remove(PlayerComponent C){
        list.remove(C);
    }

    @Override
    public void operate(){
        for (PlayerComponent player: list){
            player.operate();
        }
    }
}
