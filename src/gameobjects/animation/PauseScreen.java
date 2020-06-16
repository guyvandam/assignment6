package gameobjects.animation;

import biuoop.DrawSurface;
import interfaces.Animation;

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-06-12.
 */
public class PauseScreen implements Animation {
    @Override
    public void doOneFrame(DrawSurface d) {
        if (d != null) {
            d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        }
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}