package gameobjects.animation;

import biuoop.DrawSurface;
import constants.Constants;
import interfaces.Sprite;

import java.awt.*;

public class Background implements Sprite {
    private java.awt.Color color;

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
            d.fillRectangle(0, 0, Constants.guiWidth, Constants.guiHeight);
        }
    }

    /**
     * notify the sprite the time has passed.
     */
    @Override
    public void timePassed() {

    }
}
