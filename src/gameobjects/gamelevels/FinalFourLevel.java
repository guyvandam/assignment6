package gameobjects.gamelevels;

import constants.Constants;
import gameobjects.animation.Background;
import gameobjects.sprites.Block;
import geometryshapes.Point;
import geometryshapes.Rectangle;
import interfaces.LevelInformation;
import interfaces.Sprite;
import velocity.Velocity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FinalFourLevel implements LevelInformation {

    public FinalFourLevel() {
    }

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        int angle = 60, speed = 4;
        List<Velocity> velocityList = new ArrayList<>();
        for (int i = 0; i < this.numberOfBalls(); i++) {
            velocityList.add(Velocity.fromAngleAndSpeed(angle + i * 30, speed));
        }
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 80;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new Background(new Color(62, 133, 226));
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        java.awt.Color[] colors = {Color.gray, Color.red, Color.yellow, Color.GREEN, Color.WHITE, Color.pink, Color.cyan};

        int startX = Constants.widthOrHeight, startY = 100, blockWidth = 51, blockHeight = 25,
                numOfBlocks = (Constants.guiWidth - 2 * Constants.widthOrHeight) / blockWidth;
        for (int i = 0; i < colors.length; i++) {
            for (int j = 0; j < numOfBlocks; j++) {
                blockList.add(new Block(new Rectangle(new Point(startX + blockWidth * j, startY + blockHeight * i),
                        blockWidth, blockHeight), colors[i]));
            }
        }

        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}
