package game;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class WhistFile implements File{

    /*
    For Filtering Method, 0 means non filtering method, 1 means naive legal method, 2 means trump saving method
    For Seleting Method, 0 means Random selecting method, 1 means highest selecting method, 2 means smart selecting method
     */
    public static final Random RANDOM = ThreadLocalRandom.current();
    private static final int NUMBER_OF_FILTERING_METHOD = 3;
    private static final int NUMBER_OF_SELECTING_METHOD = 3;
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
        return RANDOM.nextInt(NUMBER_OF_FILTERING_METHOD);
    }

    public int RandomNumberForSelecting(){
        return RANDOM.nextInt(NUMBER_OF_SELECTING_METHOD);
    }

}
