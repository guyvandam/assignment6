package gameobjects.animation.endscreens;

import gameobjects.Counter;

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-06-12.
 */
public class GameOverScreen extends EndScreen {
    /**
     * constructor method. creates a parent, EndScreen object. with an output text of "Game Over. Your Score is X",
     * where X is the score of the player, saved in the input "currentScore" Counter object.
     *
     * @param currentScore a Counter object.
     */
    public GameOverScreen(Counter currentScore) {
        super("Game Over. Your Score is ", currentScore);
    }
}
