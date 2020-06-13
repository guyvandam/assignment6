package gameobjects.animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import gameobjects.SpriteCollection;
import interfaces.Animation;

import java.awt.Color;

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-06-12.
 * <p>
 * The CountdownAnimation will display the given gameScreen, for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will appear on the screen for (numOfSeconds / countFrom)
 * seconds, before it is replaced with the next one.
 * </p>
 */

public class CountdownAnimation implements Animation {
    private double numOfSeconds; // number of seconds for the whole animation.
    private int countFrom; // the number we want to count from.
    private SpriteCollection gameScreen; // the Sprite collection of the game.

    private Sleeper sleeper;
    private boolean isFirstPrint = true; // marks whether it's the first frame in of the animation.
    private int millisecondsPerFrame;

    /**
     * constructor function.
     *
     * @param numOfSeconds a double.
     * @param countFrom    an integer.
     * @param gameScreen   a SpriteCollection object.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.sleeper = new Sleeper();
        this.millisecondsPerFrame = (int) ((this.getNumOfSeconds() / this.getCountFrom()) * 1000);
    }

    /**
     * @return a double.
     */
    public double getNumOfSeconds() {
        return numOfSeconds;
    }

    /**
     * @return an integer.
     */
    public int getCountFrom() {
        return countFrom;
    }

    /**
     * @return a BIU-OOP Sleeper object.
     */
    public Sleeper getSleeper() {
        return sleeper;
    }

    /**
     * @return a SpriteCollection.
     */
    public SpriteCollection getGameScreen() {
        return gameScreen;
    }

    /**
     * @return a boolean. true if it's the first print, false otherwise.
     */
    public boolean isFirstPrint() {
        return isFirstPrint;
    }

    /**
     * @return an integer.
     */
    public int getMillisecondsPerFrame() {
        return millisecondsPerFrame;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        long startTime = System.currentTimeMillis(); // timing
        if (d == null) {
            return;
        }
        this.getGameScreen().drawAllOn(d);
        d.setColor(Color.WHITE);
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, String.valueOf(this.getCountFrom()), 50);
        this.countFrom--;
        if (!this.isFirstPrint()) {
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = this.getMillisecondsPerFrame() - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.getSleeper().sleepFor(milliSecondLeftToSleep);
            }
        }
        this.isFirstPrint = false;
    }


    @Override
    public boolean shouldStop() {
        return this.getCountFrom() < 0;
    }
}
