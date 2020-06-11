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
        int angle = 60, speed = 5;
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

        int startX = Constants.widthOrHeight + 1, startY = 100, numOfBlocks = 15, blockHeight = 25,
                blockWidth = (Constants.guiWidth - startX) / numOfBlocks - 1,
                tempX = startX;

        for (Color color : colors) {
            for (int j = 0; j < numOfBlocks; j++) {
                if(j==numOfBlocks/2){
                    System.out.println(j);
                    blockWidth += 8;
                }
                blockList.add(new Block(new Rectangle(new Point(tempX, startY),
                        blockWidth, blockHeight), color));

                tempX += blockWidth;
                blockWidth = (Constants.guiWidth - startX) / numOfBlocks - 1;
            }
            startY += blockHeight;
            tempX = startX;
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}
