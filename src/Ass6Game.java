import biuoop.GUI;
import constants.Constants;
import gameobjects.GameFlow;
import gameobjects.animation.AnimationRunner;
import gameobjects.gamelevels.DirectHitLevel;
import gameobjects.gamelevels.FinalFourLevel;
import gameobjects.gamelevels.Green3Level;
import gameobjects.gamelevels.WideEasyLevel;
import interfaces.LevelInformation;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-06-12.
 */
public class Ass6Game {
    private static final TreeMap<Integer, LevelInformation> LEVEL_NUMBER_LEVEL_INFORMATION_OBJECT_MAP = new
            TreeMap<>() {
                {
                    put(1, new DirectHitLevel());
                    put(2, new WideEasyLevel());
                    put(3, new Green3Level());
                    put(4, new FinalFourLevel());
                }
            };

    /**
     * returns a list of LevelInformation according to the numbers in the input of the command line for the main
     * method, the "args" String array. takes care of strings that doesn's represent integers and empty inputs.
     *
     * @param stringArray a String array. the input from the command line of the main method.
     * @return a Java ArrayList of LevelInformation objects.
     */
    public static List<LevelInformation> parseStringToIntArray(String[] stringArray) {
        if (stringArray == null || stringArray.length == 0
                || (stringArray[0].equals("${args}") && stringArray.length == 1)) {
            return new ArrayList<>(LEVEL_NUMBER_LEVEL_INFORMATION_OBJECT_MAP.values());
        }
        List<LevelInformation> levelInformationList = new ArrayList<>();
        LevelInformation temp;
        int levelNumber;
        for (String s : stringArray) {
            temp = null;
            try {
                levelNumber = Integer.parseInt(s);
                try {
                    temp = LEVEL_NUMBER_LEVEL_INFORMATION_OBJECT_MAP.get(levelNumber);
                } catch (NullPointerException nullPointerException) {
                }
            } catch (Exception e) {
            }

            if (temp != null) {
                levelInformationList.add(temp);
            }

        }
        if (levelInformationList.isEmpty()) {
            return new ArrayList<>(LEVEL_NUMBER_LEVEL_INFORMATION_OBJECT_MAP.values());
        }
        return levelInformationList;
    }


    /**
     * main method. runs the game.
     * <p>
     * converts the input array into a list of levels. creates a BIU-OOP GUI object and a GameFlow object that runs
     * the levels.
     * </p>
     *
     * @param args the input from the command line. can be a list of numbers representing levels.
     */
    public static void main(String[] args) {


        List<LevelInformation> levelInformationList = parseStringToIntArray(args);

        GUI gui = new GUI("Arkanoid", Constants.GUI_WIDTH, Constants.GUI_HEIGHT);


        GameFlow gameFlow = new GameFlow(new AnimationRunner(gui, 60), gui
                .getKeyboardSensor());

        gameFlow.runLevels(levelInformationList);

    }
}