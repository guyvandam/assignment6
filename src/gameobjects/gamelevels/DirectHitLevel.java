package gameobjects.gamelevels;

import biuoop.DrawSurface;
import constants.Constants;
import gameobjects.sprites.Block;
import geometryshapes.Point;
import geometryshapes.Rectangle;
import interfaces.LevelInformation;
import interfaces.Sprite;
import velocity.Velocity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//need to take care of notes in the function.


/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-06-12.
 */
public class DirectHitLevel extends Level implements LevelInformation {
    //the width and height of the red block in the middle of the target.
    private final int widthORHeight = 30;
    //the center x position of the target drawing and the red block.
    private final int centerX = this.getGuiWidth() / 2;
    // the center y position of the target drawing and the red block.
    private final int centerY = (this.getGuiHeight() - widthORHeight) / 3 + widthORHeight / 2;

    /**
     * constructor function. create a parent "Level" object to hold all the needed information.
     */
    public DirectHitLevel() {
        super("Direct Hit");
//        this.guiWidth = Constants.guiWidth;
//        this.guiHeight = Constants.guiHeight;
//        this.centerX = this.getGuiWidth() / 2;
//        this.centerY = (this.getGuiHeight() - widthORHeight) / 3 + widthORHeight / 2;

    }

    /**
     * @return an integer. the GUI width.
     */
    public int getGuiWidth() {
        return Constants.GUI_WIDTH;
    }

    /**
     * @return an integer. the GUI height.
     */
    public int getGuiHeight() {
        return Constants.GUI_HEIGHT;
    }

    /**
     * @return an integer. the corner block width/height.
     */
    public int getWidthORHeight() {
        return widthORHeight;
    }

    /**
     * @return an integer.
     */
    public int getCenterX() {
        return centerX;
    }

    /**
     * @return an integer.
     */
    public int getCenterY() {
        return centerY;
    }

    @Override
    public int numberOfBalls() {
        return this.initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity v = new Velocity(0, -7);
        return new ArrayList<>() {
            {
                add(v);
            }
        };
    }

    @Override
    public int paddleSpeed() {

//        return 10;
        return super.getPaddleSpeed();
    }

    @Override
    public int paddleWidth() {
//        return 80;
        return super.getPaddleWidth();
    }

    @Override
    public String levelName() {
//        return "Direct Hit";
        return super.getLevelName();
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                int r = 50, numOfCircles = 3, halfALine = numOfCircles * r + 20;
                if (d != null) {
                    d.setColor(Color.BLACK);
                    d.fillRectangle(0, 0, getGuiWidth(), getGuiHeight());
                    this.drawCircles(d, r, numOfCircles);
                    d.drawLine(centerX - halfALine, centerY, centerX + halfALine, centerY);
                    d.drawLine(centerX, centerY - halfALine, centerX, centerY + halfALine);

                }
            }

            public void drawCircles(DrawSurface d, int r, int numOfCircles) {
                if (d == null) {
                    return;
                }
                d.setColor(Color.BLUE);
                for (int i = 0; i < numOfCircles; i++) {
                    d.drawCircle(centerX, centerY, (i + 1) * r);
                }
            }

            @Override
            public void timePassed() {
            }
        };
    }

    @Override
    public List<Block> blocks() {
        Block b = new Block(new Rectangle(new Point(this.getCenterX() - (this
                .getWidthORHeight() / 2), this.getCenterY() - (this.getWidthORHeight() / 2)),
                this.getWidthORHeight(), this.getWidthORHeight()), Color.RED);
        return new ArrayList<>() {
            {
                add(b);
            }
        };
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}
