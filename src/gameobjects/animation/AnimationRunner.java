package gameobjects.animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import interfaces.Animation;

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-06-12.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * constructor function.
     *
     * @param gui             a BIU-OOP GUI object.
     * @param framesPerSecond an integer.
     * @param sleeper         a BIU-OOP object.
     */
    public AnimationRunner(GUI gui, int framesPerSecond, Sleeper sleeper) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
        this.sleeper = sleeper;
    }

    /**
     * @return a BIU-OOP object.
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * @return a BIU-OOP object.
     */
    public Sleeper getSleeper() {
        return sleeper;
    }

    /**
     * @param animation an Animation object. runs the input animation.
     */
    public void run(Animation animation) {
        if (animation == null) {
            return;
        }
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.getGui().getDrawSurface();

            animation.doOneFrame(d);

            this.getGui().show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.getSleeper().sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
