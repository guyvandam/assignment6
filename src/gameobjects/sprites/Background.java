package gameobjects.sprites;

import biuoop.DrawSurface;
import constants.Constants;
import interfaces.Sprite;

import java.awt.Color;

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-06-12.
 */
public class Background implements Sprite {
    private java.awt.Color color; //the color of the background.

    /**
     * constructor function. gets the color of the background as input.
     *
     * @param color a java.awt.Color object. the color of the background.
     */
    public Background(Color color) {
        this.color = color;
    }

    /**
     * draws the sprite on the input surface.
     *
     * @param d a DrawSurface object. the GUI surface we want to draw the sprite on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        if (d != null) {
            d.setColor(color);
            d.fillRectangle(0, 0, Constants.GUI_WIDTH, Constants.GUI_HEIGHT);
        }
    }

    /**
     * notify the sprite the time has passed.
     */
    @Override
    public void timePassed() {

    }
}
