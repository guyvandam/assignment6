package gameobjects.animation.endscreens;

import gameobjects.Counter;

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-06-12.
 */
public class WinScreen extends EndScreen {
    /**
     * constructor method. creates a parent, EndScreen object. with an output text of "You Win! Your score is X",
     * where X is the score of the player, saved in the input "currentScore" Counter object.
     *
     * @param currentScore a Counter object.
     */
    public WinScreen(Counter currentScore) {
        super("You Win! Your score is ", currentScore);
    }
}
