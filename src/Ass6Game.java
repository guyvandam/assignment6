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
    private static final TreeMap<Integer, LevelInformation> levelNumberLevelInformationObjectMap = new TreeMap<>() {
        {
            put(1, new DirectHitLevel());
            put(2, new WideEasyLevel());
            put(3, new Green3Level());
            put(4, new FinalFourLevel());
        }
    };

    public static List<LevelInformation> parseStringToIntArray(String[] stringArray) {
        if (stringArray == null || stringArray.length == 0) {
            return new ArrayList<>(levelNumberLevelInformationObjectMap.values());
        }
        List<LevelInformation> levelInformationList = new ArrayList<>();
        LevelInformation temp;
        int levelNumber;
        for (String s : stringArray) {
            temp = null;
            try {
                levelNumber = Integer.parseInt(s);
                try {
                    temp = levelNumberLevelInformationObjectMap.get(levelNumber);
                } catch (NullPointerException nullPointerException) {
                }
            } catch (Exception e) {
            }

            if (temp != null) {
                levelInformationList.add(temp);
            }

        }
        return levelInformationList;
    }


    /**
     * main method. runs the game.
     * <p>
     * creates a BIU-OOP GUI object, an array list of levels and a GameFlow object that runs those levels.
     * </p>
     *
     * @param args
     */
    public static void main(String[] args) {


        List<LevelInformation> levelInformationList = parseStringToIntArray(args);


        GUI gui = new GUI("Arkanoid", Constants.GUI_WIDTH, Constants.GUI_HEIGHT);


        GameFlow gameFlow = new GameFlow(new AnimationRunner(gui, 60), gui
                .getKeyboardSensor());

        gameFlow.runLevels(levelInformationList);

    }
}