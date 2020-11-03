package game;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class smartFile implements File{
    static final Random random = ThreadLocalRandom.current();
    private static final int NumberOfFilteringMethod = 2;
    private static final int NumberOfSelectingMethod = 3;
    @Override
    public void writeProperties() {
        Properties smart = new Properties();
        smart.setProperty("human", "1");
        smart.setProperty("NPC", "3");
        smart.setProperty("nbStartCard", "13");
        smart.setProperty("winningScore", "24");

        smart.setProperty("NPCOneNaiveLegal", "false");
        smart.setProperty("NPCOneTrumpSaving", "false");

        if (RandomNumberForFiltering() == 0){
            smart.setProperty("NPCTwoNaiveLegal", "true");
            smart.setProperty("NPCTwoTrumpSaving", "false");
        }else{
            smart.setProperty("NPCTwoNaiveLegal", "false");
            smart.setProperty("NPCTwoTrumpSaving", "true");
        }

        if (RandomNumberForFiltering() == 0){
            smart.setProperty("NPCThreeNaiveLegal", "true");
            smart.setProperty("NPCThreeTrumpSaving", "false");
        }else{
            smart.setProperty("NPCThreeNaiveLegal", "false");
            smart.setProperty("NPCThreeTrumpSaving", "true");
        }

        smart.setProperty("NPCOneRandomSelection", "false");
        smart.setProperty("NPCOneHighestSelection", "false");
        smart.setProperty("NPCOneSmartSelection", "true");

        if(RandomNumberForSelecting() == 0){
            smart.setProperty("NPCTwoRandomSelection", "true");
            smart.setProperty("NPCTwoHighestSelection", "false");
            smart.setProperty("NPCTwoSmartSelection", "false");
        }else if(RandomNumberForSelecting() == 1){
            smart.setProperty("NPCTwoRandomSelection", "false");
            smart.setProperty("NPCTwoHighestSelection", "true");
            smart.setProperty("NPCTwoSmartSelection", "false");
        }else{
            smart.setProperty("NPCTwoRandomSelection", "false");
            smart.setProperty("NPCTwoHighestSelection", "false");
            smart.setProperty("NPCTwoSmartSelection", "true");
        }

        if(RandomNumberForSelecting() == 0){
            smart.setProperty("NPCThreeRandomSelection", "true");
            smart.setProperty("NPCThreeHighestSelection", "false");
            smart.setProperty("NPCThreeSmartSelection", "false");
        }else if(RandomNumberForSelecting() == 1){
            smart.setProperty("NPCThreeRandomSelection", "false");
            smart.setProperty("NPCThreeHighestSelection", "true");
            smart.setProperty("NPCThreeSmartSelection", "false");
        }else{
            smart.setProperty("NPCThreeRandomSelection", "false");
            smart.setProperty("NPCThreeHighestSelection", "false");
            smart.setProperty("NPCThreeSmartSelection", "true");
        }

    }

    public int RandomNumberForFiltering(){
        return random.nextInt(NumberOfFilteringMethod);
    }

    public int RandomNumberForSelecting(){
        return random.nextInt(NumberOfSelectingMethod);
    }
}
