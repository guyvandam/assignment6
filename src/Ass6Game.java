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

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-06-12.
 */
public class Ass6Game {
    /**
     * main method. runs the game.
     * <p>
     * creates a BIU-OOP GUI object, an array list of levels and a GameFlow object that runs those levels.
     * </p>
     *
     * @param args 
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", Constants.GUI_WIDTH, Constants.GUI_HEIGHT);

        List<LevelInformation> levels = new ArrayList<>() {
            {
                add(new DirectHitLevel());
                add(new WideEasyLevel());
                add(new Green3Level());
                add(new FinalFourLevel());
            }
        };
        GameFlow gameFlow = new GameFlow(new AnimationRunner(gui, 60), gui
                .getKeyboardSensor());

        gameFlow.runLevels(levels);

    }
}