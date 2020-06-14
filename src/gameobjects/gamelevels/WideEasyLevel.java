package gameobjects.gamelevels;

import constants.Constants;
import gameobjects.sprites.Background;
import gameobjects.sprites.Block;
import geometryshapes.Point;
import geometryshapes.Rectangle;
import interfaces.LevelInformation;
import interfaces.Sprite;
import velocity.Velocity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//note clean up.

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-06-12.
 */
public class WideEasyLevel extends Level implements LevelInformation {
    /**
     * constructor function. create a parent "Level" object to hold all the needed information. changes the block's
     * default width and the speed to width = 550 and speed = 1 (the slowest speed).
     */
    public WideEasyLevel() {
        super("Wide Easy");
        super.setPaddleSpeed(1);
        super.setPaddleWidth(550);
    }
//    private int guiWidth;
//    private int guiHeight;

//    private int widthOfBorders = 20;

//    public WideEasyLevel() {
//        this.guiWidth = Constants.guiWidth;
//        this.guiHeight = Constants.guiHeight;
//}

//    public int getGuiWidth() {
//        return Constants.guiWidth;
//    }
//
//    public int getGuiHeight() {
//        return Constants.guiHeight;
//    }

    @Override
    public int numberOfBalls() {
        return initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        int speed = 6, delta = 9, angle = 90 - 5 * delta;
        for (int i = 0; i < 11; i++) {
            if (angle != 90) {
                velocities.add(Velocity.fromAngleAndSpeed(angle, speed));
            }
            angle += delta;
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
//        return 1;
        return super.getPaddleSpeed();
    }

    @Override
    public int paddleWidth() {
//        return 550;
        return super.getPaddleWidth();
    }

    @Override
    public String levelName() {
//        return "Wide Easy";
        return super.getLevelName();
    }

    @Override
    public Sprite getBackground() {
//        return new Sprite() {
//            @Override
//            public void drawOn(DrawSurface d) {
//                if (d != null) {
//                    d.setColor(Color.white);
//                    d.fillRectangle(0, 0, getGuiWidth(), getGuiHeight());
//                }
//            }
//
//            @Override
//            public void timePassed() {
//            }
//        };
        return new Background(Color.WHITE);
    }

    @Override
    public List<Block> blocks() {
        java.awt.Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.PINK,
                Color.CYAN};
        int x = Constants.WIDTH_OR_HEIGHT + 1, y = 230, blockWidth, blockHeight = 25, blockInColor;
        List<Block> blockList = new ArrayList<>();
        for (java.awt.Color c : colors) {
            //resetting the values for the width, and number in color after the green color.
            blockInColor = 2;
            blockWidth = 51;
            if (c.equals(Color.GREEN)) {
                blockInColor = 3;
                blockWidth += 2; //lining up the blocks with the edges.
            }

            for (int i = 0; i < blockInColor; i++) {
                blockList.add(new Block(new Rectangle(new Point(x, y), blockWidth, blockHeight), c));
                x += blockWidth - 1; //subtraction 1 for lining up the blocks;
            }
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}
