import gameobjects.GameLevel;
import gameobjects.gamelevels.DirectHitLevel;
//import gameobjects.gamelevels.FinalFourLevel;
import gameobjects.gamelevels.Green3Level;
import gameobjects.gamelevels.WideEasyLevel;

import java.lang.module.FindException;

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-05-28.
 */
public class Ass6Game {
    /**
     * main method. creates a new GameObjects.Game object, initializes everything and runs the game.
     *
     * @param args the command line arguments, we have no use for them in this main method.
     */
    public static void main(String[] args) {
//        GameLevel game = new GameLevel(800, 600,new DirectHitLevel(800,600));
//        GameLevel game = new GameLevel(800, 600, new WideEasyLevel(800, 600));
        GameLevel game = new GameLevel(800, 600, new Green3Level());
//        GameLevel game = new GameLevel(800, 600, new FindException());

        game.initialize();
        game.run();
    }
}