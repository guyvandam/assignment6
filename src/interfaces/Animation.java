package interfaces;

import biuoop.DrawSurface;

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-06-12.
 */
public interface Animation {
    /**
     * one frame of the animation.
     *
     * @param d a BIU-OOP DrawSurface object. the surface we want to draw our frame on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * @return a boolean. true if the animation should be stopped, false otherwise.
     */
    boolean shouldStop();

}
