package gameobjects.gamelevels;

import biuoop.DrawSurface;
import gameobjects.sprites.Block;
import geometryshapes.Point;
import geometryshapes.Rectangle;
import interfaces.LevelInformation;
import interfaces.Sprite;
import velocity.Velocity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DirectHitLevel implements LevelInformation {
    private int guiWidth;
    private int guiHeight;

    private int widthORHeight = 30;
    private int centerX;
    private int centerY;


    public DirectHitLevel() {
        this.guiWidth = 800;
        this.guiHeight = 600;
        this.centerX = this.getGuiWidth() / 2;
        this.centerY = (this.getGuiHeight() - widthORHeight) / 4 + widthORHeight / 2;

    }

    public int getGuiWidth() {
        return guiWidth;
    }

    public int getGuiHeight() {
        return guiHeight;
    }

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity v = new Velocity(0, -3);
        return new ArrayList<>() {
            {
                add(v);
            }
        };
    }

    @Override
    public int paddleSpeed() {
        return 1;
    }

    @Override
    public int paddleWidth() {
        return 60;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                int r = 50, halfALine = 3 * r + 20;
                if (d != null) {
                    d.setColor(Color.BLACK);
                    d.fillRectangle(0, 0, getGuiWidth(), getGuiHeight());
                    this.drawCircles(d, r);
                    d.drawLine(centerX - halfALine, centerY, centerX + halfALine, centerY);
                    d.drawLine(centerX, centerY - halfALine, centerX, centerY + halfALine);

                }
            }

            public void drawCircles(DrawSurface d, int r) {
                if (d == null) {
                    return;
                }
                d.setColor(Color.BLUE);
                int numOfCircles = 3;
                for (int i = 0; i < numOfCircles; i++) {
                    
                }
                d.drawCircle(centerX, centerY, r);
                d.drawCircle(centerX, centerY, 2 * r);
                d.drawCircle(centerX, centerY, 3 * r);
            }

            @Override
            public void timePassed() {
            }
        };
    }

    @Override
    public List<Block> blocks() {
//        int widthORHeight = 30;
//        Block b = new Block(new Rectangle(new Point((this.getGuiWidth() - widthORHeight) / 2, (this.getGuiHeight() - widthORHeight) / 3), widthORHeight, widthORHeight), Color.RED);
        Block b = new Block(new Rectangle(new Point((centerX - widthORHeight) / 2, (centerY - widthORHeight) / 2),
                widthORHeight, widthORHeight), Color.RED);
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
