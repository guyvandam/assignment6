package gameobjects.animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import gameobjects.SpriteCollection;
import interfaces.Animation;

import java.awt.*;

// The CountdownAnimation will display the given gameScreen,
// for numOfSeconds seconds, and on top of them it will show
// a countdown from countFrom back to 1, where each number will
// appear on the screen for (numOfSeconds / countFrom) seconds, before
// it is replaced with the next one.
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;

    private Sleeper sleeper;
    private boolean isFirstPrint = true;
    private int millisecondsPerFrame;

    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.sleeper = new Sleeper();
        this.millisecondsPerFrame = (int) ((this.getNumOfSeconds() / this.getCountFrom()) * 1000);
    }

    public double getNumOfSeconds() {
        return numOfSeconds;
    }

    public int getCountFrom() {
        return countFrom;
    }

    public Sleeper getSleeper() {
        return sleeper;
    }

    public SpriteCollection getGameScreen() {
        return gameScreen;
    }

    public boolean isFirstPrint() {
        return isFirstPrint;
    }

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
