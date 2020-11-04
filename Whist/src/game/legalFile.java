package game;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class legalFile implements File{
    private static final int NumberOfSelectingMethod = 3;
    static final Random random = ThreadLocalRandom.current();

    @Override
    public Properties writeProperties() {
        Properties legal = new Properties();
        legal.setProperty("human", "0");
        legal.setProperty("NPC", "3");
        legal.setProperty("nbStartCard", "6");
        legal.setProperty("winningScore", "6");

        legal.setProperty("NPCOneSelect", "0");
        legal.setProperty("NPCTwoSelect", "1");
        legal.setProperty("NPCThreeSelect", "1");

        legal.setProperty("NPCOneFilter", "1");
        legal.setProperty("NPCTwoFilter", "2");
        legal.setProperty("NPCThreeFilter","0");

        return legal;
    }


}
