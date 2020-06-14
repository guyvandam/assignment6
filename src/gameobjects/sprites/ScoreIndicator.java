package gameobjects.sprites;

import biuoop.DrawSurface;
import gameobjects.Counter;
import gameobjects.gamelevels.GameLevel;
import interfaces.Sprite;

import java.awt.Color;

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-05-28.
 */
public class ScoreIndicator implements Sprite {
    private Counter currentScore;
    private Block block;
    private String levelName;

    /**
     * constructor function.
     *
     * @param currentScore a Counter object.
     * @param block        a Block object.
     * @param levelName    a String.
     */
    public ScoreIndicator(Counter currentScore, Block block, String levelName) {
        this.currentScore = currentScore;
        this.block = block;
        this.levelName = levelName;
    }

    /**
     * @return a Counter object.
     */
    public Counter getCurrentScore() {
        return currentScore;
    }


    /**
     * @return a Block object.
     */
    public Block getBlock() {
        return block;
    }

    /**
     * @return a String. the level name.
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     * draws the sprite on the input surface.
     *
     * @param d a DrawSurface object. the GUI surface we want to draw the sprite on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        String display = "Score: " + this.getCurrentScore().getValue();
        int fontSize = 15, x = (int) this.getBlock().getRect().getWidth() / 3, y = fontSize;
        if (d != null) {
            this.getBlock().drawOn(d);
            d.setColor(Color.BLACK);
            d.drawText(x, y, display, fontSize);
            d.drawText(2 * x, y, "Level Name: " + this.getLevelName(), fontSize);
        }
    }

    /**
     * notify the sprite the time has passed.
     */
    @Override
    public void timePassed() {
    }

    /**
     * adds the ScoreIndicator object to the input Game be adding it to the Game's spriteCollection.
     *
     * @param g a Game object.
     */
    public void addToGame(GameLevel g) {
        if (g == null) {
            return;
        }
        g.addSprite(this);
    }
}
