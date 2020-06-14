package gameobjects.gamelevels;

import constants.Constants;
import gameobjects.sprites.Background;
import gameobjects.sprites.Block;
import geometryshapes.Point;
import interfaces.LevelInformation;
import interfaces.Sprite;
import velocity.Velocity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// need to take care of notes.

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-06-12.
 */
public class Green3Level extends Level implements LevelInformation {
    /**
     * constructor function. create a parent "Level" object to hold all the needed information.
     */
    public Green3Level() {
        super("Green 3");
    }

    @Override
    public int numberOfBalls() {
        return initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        int speed = 5, angle = 45;
        return new ArrayList<>() {
            {
                add(Velocity.fromAngleAndSpeed(angle, speed));
                add(Velocity.fromAngleAndSpeed(180 - angle, speed));
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
//        return "Green 3";
        return super.getLevelName();
    }

    @Override
    public Sprite getBackground() {
        return new Background(new Color(36, 118, 55));
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();

        java.awt.Color[] colors = {Color.gray, Color.red, Color.yellow, Color.BLUE, Color.WHITE};

        int blockWidth = 50, blockHeight = 25;
        int numOfRows = colors.length, numOfColumns = 10;
        int startX = Constants.GUI_WIDTH - numOfColumns * blockWidth - Constants.WIDTH_OR_HEIGHT - 1, startY = 100;

        int rowCounter = 0, columnCounter;
        while (rowCounter < numOfRows) {
            columnCounter = 0;
            while (columnCounter < numOfColumns) {
                Block temp = new Block(new geometryshapes.Rectangle(new Point(startX, startY), blockWidth, blockHeight),
                        colors[rowCounter]);
                blockList.add(temp);
                startX += blockWidth;
                columnCounter++;
            }
            startY += blockHeight;
            numOfColumns--;
            //resetting the starting x position.
            startX = Constants.GUI_WIDTH - numOfColumns * blockWidth - Constants.WIDTH_OR_HEIGHT - 1;
            rowCounter++;

        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}
