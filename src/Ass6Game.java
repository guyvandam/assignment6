import biuoop.GUI;
import biuoop.Sleeper;
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

public class Ass6Game {
    public static void main(String[] args) {
//        GameLevel game = new GameLevel(800, 600,new DirectHitLevel(800,600));
//        GameLevel game = new GameLevel(800, 600, new WideEasyLevel(800, 600));
//        GameLevel game = new GameLevel(800, 600, new Green3Level());
//        GameLevel game = new GameLevel(800, 600, new FinalFourLevel());

//        game.initialize();
//        game.run();
        GUI gui = new GUI("Arkanoid", Constants.guiWidth, Constants.guiHeight);

        List<LevelInformation> levels = new ArrayList<>() {
            {
                add(new DirectHitLevel());
                add(new WideEasyLevel());
                add(new Green3Level());
                add(new FinalFourLevel());
            }
        };
        GameFlow gameFlow = new GameFlow(new AnimationRunner(gui, 60, new Sleeper()), gui
                .getKeyboardSensor());

        gameFlow.runLevels(levels);

    }
}