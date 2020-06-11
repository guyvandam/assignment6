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

public class WideEasyLevel implements LevelInformation {
    private int guiWidth;
    private int guiHeight;

    private int widthOfBorders = 20;

    public WideEasyLevel() {
        this.guiWidth = Constants.guiWidth;
        this.guiHeight = Constants.guiHeight;
    }

    public int getGuiWidth() {
        return guiWidth;
    }

    public int getGuiHeight() {
        return guiHeight;
    }

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
        return 1;
    }

    @Override
    public int paddleWidth() {
        return 550;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                if (d != null) {
                    d.setColor(Color.white);
                    d.fillRectangle(0, 0, getGuiWidth(), getGuiHeight());
                }
            }

            @Override
            public void timePassed() {
            }
        };

    }

    @Override
    public List<Block> blocks() {
        java.awt.Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.PINK, Color.CYAN};
        int x = widthOfBorders + 1, y = 230, blockWidth, blockHeight = 25, blockInColor;
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
