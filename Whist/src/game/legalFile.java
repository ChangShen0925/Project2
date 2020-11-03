package game;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class legalFile implements File{
    private static final int NumberOfSelectingMethod = 3;
    static final Random random = ThreadLocalRandom.current();

    @Override
    public void writeProperties() {
        Properties legal = new Properties();
        legal.setProperty("human", "0");
        legal.setProperty("NPC", "3");
        legal.setProperty("nbStartCard", "6");
        legal.setProperty("winningScore", "6");

        legal.setProperty("NPCOneNaiveLegal", "true");
        legal.setProperty("NPCOneTrumpSaving", "false");

        legal.setProperty("NPCTwoNaiveLegal", "false");
        legal.setProperty("NPCTwoTrumpSaving", "true");

        legal.setProperty("NPCThreeNaiveLegal", "false");
        legal.setProperty("NPCThreeTrumpSaving", "false");

        if(RandomNumberForSelecting() == 0){
            legal.setProperty("NPCOneRandomSelection", "true");
            legal.setProperty("NPCOneHighestSelection", "false");
            legal.setProperty("NPCOneSmartSelection", "false");
        }else if(RandomNumberForSelecting() == 1){
            legal.setProperty("NPCOneRandomSelection", "false");
            legal.setProperty("NPCOneHighestSelection", "true");
            legal.setProperty("NPCOneSmartSelection", "false");
        }else{
            legal.setProperty("NPCOneRandomSelection", "false");
            legal.setProperty("NPCOneHighestSelection", "false");
            legal.setProperty("NPCOneSmartSelection", "true");
        }

        legal.setProperty("NPCTwoRandomSelection", "false");
        legal.setProperty("NPCTwoHighestSelection", "true");
        legal.setProperty("NPCTwoSmartSelection", "false");

        legal.setProperty("NPCThreeRandomSelection", "true");
        legal.setProperty("NPCThreeHighestSelection", "false");
        legal.setProperty("NPCThreeSmartSelection", "false");
    }

    public int RandomNumberForSelecting(){
        return random.nextInt(NumberOfSelectingMethod);
    }

}
