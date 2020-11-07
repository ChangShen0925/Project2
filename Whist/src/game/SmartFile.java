package game;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class SmartFile implements File{
    /*
    For Filtering Method, 0 means non filtering method, 1 means naive legal method, 2 means trump saving method
    For Seleting Method, 0 means Random selecting method, 1 means highest selecting method, 2 means smart selecting method
     */
    public static final Random RANDOM = ThreadLocalRandom.current();
    private static final int NUMBER_OF_FILTERING_METHOD = 3;
    private static final int NUMBER_OF_SELECTING_METHOD = 3;
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
        return RANDOM.nextInt(NUMBER_OF_FILTERING_METHOD);
    }

    public int RandomNumberForSelecting(){
        return RANDOM.nextInt(NUMBER_OF_SELECTING_METHOD);
    }
}
