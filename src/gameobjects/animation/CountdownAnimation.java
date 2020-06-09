package gameobjects.animation;

import biuoop.DrawSurface;
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
        if (d == null) {
            return;
        }
        for (int i = 0; i < 120; i++) {
            d.drawText(10, d.getHeight() / 2, String.valueOf(countFrom), 32);
        }
        countFrom--;
    }


    @Override
    public boolean shouldStop() {
        boolean bo = !(countFrom > 0);
//        return !(numOfSeconds > 0);
        System.out.println(bo);
        return bo;
    }
}
