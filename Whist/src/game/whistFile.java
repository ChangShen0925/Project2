package game;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class whistFile implements File{
    static final Random random = ThreadLocalRandom.current();
    private static final int NumberOfFilteringMethod = 2;
    private static final int NumberOfSelectingMethod = 3;
    @Override
    public void writeProperties() {
        Properties whist = new Properties();
        whist.setProperty("human", "1");
        whist.setProperty("NPC", "3");
        whist.setProperty("nbStartCard", "13");
        whist.setProperty("winningScore", "24");

        if (RandomNumberForFiltering() == 0){
            whist.setProperty("NPCOneNaiveLegal", "true");
            whist.setProperty("NPCOneTrumpSaving", "false");
        }else{
            whist.setProperty("NPCOneNaiveLegal", "false");
            whist.setProperty("NPCOneTrumpSaving", "true");
        }

        if (RandomNumberForFiltering() == 0){
            whist.setProperty("NPCTwoNaiveLegal", "true");
            whist.setProperty("NPCTwoTrumpSaving", "false");
        }else{
            whist.setProperty("NPCTwoNaiveLegal", "false");
            whist.setProperty("NPCTwoTrumpSaving", "true");
        }

        if (RandomNumberForFiltering() == 0){
            whist.setProperty("NPCThreeNaiveLegal", "true");
            whist.setProperty("NPCThreeTrumpSaving", "false");
        }else{
            whist.setProperty("NPCThreeNaiveLegal", "false");
            whist.setProperty("NPCThreeTrumpSaving", "true");
        }

        if(RandomNumberForSelecting() == 0){
            whist.setProperty("NPCOneRandomSelection", "true");
            whist.setProperty("NPCOneHighestSelection", "false");
            whist.setProperty("NPCOneSmartSelection", "false");
        }else if(RandomNumberForSelecting() == 1){
            whist.setProperty("NPCOneRandomSelection", "false");
            whist.setProperty("NPCOneHighestSelection", "true");
            whist.setProperty("NPCOneSmartSelection", "false");
        }else{
            whist.setProperty("NPCOneRandomSelection", "false");
            whist.setProperty("NPCOneHighestSelection", "false");
            whist.setProperty("NPCOneSmartSelection", "true");
        }

        if(RandomNumberForSelecting() == 0){
            whist.setProperty("NPCTwoRandomSelection", "true");
            whist.setProperty("NPCTwoHighestSelection", "false");
            whist.setProperty("NPCTwoSmartSelection", "false");
        }else if(RandomNumberForSelecting() == 1){
            whist.setProperty("NPCTwoRandomSelection", "false");
            whist.setProperty("NPCTwoHighestSelection", "true");
            whist.setProperty("NPCTwoSmartSelection", "false");
        }else{
            whist.setProperty("NPCTwoRandomSelection", "false");
            whist.setProperty("NPCTwoHighestSelection", "false");
            whist.setProperty("NPCTwoSmartSelection", "true");
        }

        if(RandomNumberForSelecting() == 0){
            whist.setProperty("NPCThreeRandomSelection", "true");
            whist.setProperty("NPCThreeHighestSelection", "false");
            whist.setProperty("NPCThreeSmartSelection", "false");
        }else if(RandomNumberForSelecting() == 1){
            whist.setProperty("NPCThreeRandomSelection", "false");
            whist.setProperty("NPCThreeHighestSelection", "true");
            whist.setProperty("NPCThreeSmartSelection", "false");
        }else{
            whist.setProperty("NPCThreeRandomSelection", "false");
            whist.setProperty("NPCThreeHighestSelection", "false");
            whist.setProperty("NPCThreeSmartSelection", "true");
        }

    }

    public int RandomNumberForFiltering(){
        return random.nextInt(NumberOfFilteringMethod);
    }

    public int RandomNumberForSelecting(){
        return random.nextInt(NumberOfSelectingMethod);
    }

}
