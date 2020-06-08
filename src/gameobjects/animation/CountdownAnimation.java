package gameobjects.animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import gameobjects.SpriteCollection;
import interfaces.Animation;

// The CountdownAnimation will display the given gameScreen,
// for numOfSeconds seconds, and on top of them it will show
// a countdown from countFrom back to 1, where each number will
// appear on the screen for (numOfSeconds / countFrom) seconds, before
// it is replaced with the next one.
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;

    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
//        Sleeper sleeper = new Sleeper();
//        long millisecondsPerFrame = (long) (numOfSeconds / 1000);
//        long millisecondsPerFrame = (long) (1000 / numOfSeconds);
        if (d == null) {
            return;
        }
        d.drawText(10, d.getHeight() / 2, String.valueOf(countFrom), 32);
        countFrom--;
        numOfSeconds--;
//        long startTime = System.currentTimeMillis(); // timing
//        long usedTime = System.currentTimeMillis() - startTime;
//        long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
//        if (milliSecondLeftToSleep > 0) {
//            sleeper.sleepFor/(milliSecondLeftToSleep);
//        }
    }


    @Override
    public boolean shouldStop() {
        boolean bo = (numOfSeconds > 0);
//        return !(numOfSeconds > 0);
        System.out.println(bo);
        return bo;
    }
}
