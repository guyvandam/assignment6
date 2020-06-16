package gameobjects.animation.endscreens;

import biuoop.DrawSurface;
import gameobjects.Counter;
import interfaces.Animation;

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-06-12.
 */
public abstract class EndScreen implements Animation {
    private String endMessage;
    private Counter currentScore;

    /**
     * @param endMessage   a String. the message displayed to player.
     * @param currentScore a Counter object. holding the score of the player.
     */
    public EndScreen(String endMessage, Counter currentScore) {
        this.endMessage = endMessage;
        this.currentScore = currentScore;
    }

    /**
     * @return a Counter object.
     */
    public Counter getCurrentScore() {
        return currentScore;
    }

    /**
     * @return a string.
     */
    public String getEndMessage() {
        return endMessage;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (d != null) {
            d.drawText(10, d.getHeight() / 2, this.getEndMessage() + this.getCurrentScore().getValue(), 32);
        }
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
