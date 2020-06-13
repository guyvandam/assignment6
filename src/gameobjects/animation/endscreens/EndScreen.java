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
    //    private KeyboardSensor keyboard;
//    private boolean stop;
    private Counter currentScore;

    public EndScreen(String endMessage, Counter currentScore) {
        this.endMessage = endMessage;
        this.currentScore = currentScore;
    }
    //    public EndScreen(KeyboardSensor k, Counter currentScore, String endMessage) {
//        this.keyboard = k;
//        this.stop = false;
//        this.currentScore = currentScore;
//        this.endMessage = endMessage;
//    }

    public Counter getCurrentScore() {
        return currentScore;
    }

    public String getEndMessage() {
        return endMessage;
    }

    public void doOneFrame(DrawSurface d) {
        if (d != null) {
            d.drawText(10, d.getHeight() / 2, this.getEndMessage() + this.getCurrentScore().getValue(), 32);
        }
//        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
//            this.stop = true;
//        }
    }

    public boolean shouldStop() {
        return false;
    }
}
