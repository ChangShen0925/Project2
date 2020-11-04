package game;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class smartFile implements File{
    static final Random random = ThreadLocalRandom.current();
    private static final int NumberOfFilteringMethod = 3;
    private static final int NumberOfSelectingMethod = 3;
    @Override
    public Properties writeProperties() {
        Properties smart = new Properties();
        smart.setProperty("human", "1");
        smart.setProperty("NPC", "3");
        smart.setProperty("nbStartCard", "13");
        smart.setProperty("winningScore", "24");

        smart.setProperty("NPCOneSelect", "2");
        smart.setProperty("NPCTwoSelect", Integer.toString(RandomNumberForSelecting()));
        smart.setProperty("NPCThreeSelect", Integer.toString(RandomNumberForSelecting()));

        smart.setProperty("NPCOneFilter", "0");
        smart.setProperty("NPCTwoFilter", Integer.toString(RandomNumberForFiltering()));
        smart.setProperty("NPCThreeFilter", Integer.toString(RandomNumberForFiltering()));
        return smart;
    }

    public int RandomNumberForFiltering(){
        return random.nextInt(NumberOfFilteringMethod);
    }

    public int RandomNumberForSelecting(){
        return random.nextInt(NumberOfSelectingMethod);
    }
}
