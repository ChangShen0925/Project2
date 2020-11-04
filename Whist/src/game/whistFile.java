package game;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class whistFile implements File{
    static final Random random = ThreadLocalRandom.current();
    private static final int NumberOfFilteringMethod = 3;
    private static final int NumberOfSelectingMethod = 3;
    @Override
    public Properties writeProperties() {
        Properties whist = new Properties();
        whist.setProperty("human", "1");
        whist.setProperty("NPC", "3");
        whist.setProperty("nbStartCard", "13");
        whist.setProperty("winningScore", "24");

        whist.setProperty("NPCOneSelect", Integer.toString(RandomNumberForSelecting()));
        whist.setProperty("NPCTwoSelect", Integer.toString(RandomNumberForSelecting()));
        whist.setProperty("NPCThreeSelect", Integer.toString(RandomNumberForSelecting()));

        whist.setProperty("NPCOneFilter", Integer.toString(RandomNumberForFiltering()));
        whist.setProperty("NPCTwoFilter", Integer.toString(RandomNumberForFiltering()));
        whist.setProperty("NPCThreeFilter", Integer.toString(RandomNumberForFiltering()));

        return whist;
    }

    public int RandomNumberForFiltering(){
        return random.nextInt(NumberOfFilteringMethod);
    }

    public int RandomNumberForSelecting(){
        return random.nextInt(NumberOfSelectingMethod);
    }

}
