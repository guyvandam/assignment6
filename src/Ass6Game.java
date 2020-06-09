import gameobjects.GameLevel;
import gameobjects.gamelevels.DirectHitLevel;

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
        GameLevel game = new GameLevel(800, 600,new DirectHitLevel());
        game.initialize();
        game.run();
    }
}