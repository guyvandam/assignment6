package gameobjects.gamelevels;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import constants.Constants;
import gameobjects.Counter;
import gameobjects.GameEnvironment;
import gameobjects.SpriteCollection;
import gameobjects.animation.AnimationRunner;
import gameobjects.animation.PauseScreen;
import gameobjects.hitlisteners.BallRemover;
import gameobjects.hitlisteners.BlockRemover;
import gameobjects.hitlisteners.ScoreTrackingListener;
import gameobjects.sprites.Ball;
import gameobjects.sprites.Block;
import gameobjects.sprites.Paddle;
import gameobjects.sprites.ScoreIndicator;
import geometryshapes.Point;
import geometryshapes.Rectangle;
import interfaces.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 2.0
 * @since 2020-03-28.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter remainingBlocks; // the number of blocks in the game.
    private Counter remainingBalls; // the number of balls in the game.
    private Counter currentScore;
    private GUI gui;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation levelInformation;

    private int guiWidth;
    private int guiHeight;
    private int widthORHeight = 20;
    private KeyboardSensor keyboard;


    /**
     * constractur function.
     */
    public GameLevel(LevelInformation levelInformation, AnimationRunner runner, KeyboardSensor keyboardSensor,
                     Counter currentScore) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.guiWidth = Constants.guiWidth;
        this.guiHeight = Constants.guiHeight;
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
//        this.currentScore = new Counter();
        this.currentScore = currentScore;
//        this.runner = new AnimationRunner(this.gui, 60, new Sleeper());
        this.runner = runner;
//        this.gui = new GUI("Arkanoid", getGuiWidth(), getGuiHeight());
        this.gui = this.runner.getGui();
//        this.keyboard = this.getGui().getKeyboardSensor();
        this.keyboard = keyboardSensor;
        this.levelInformation = levelInformation;
    }


    /**
     * @return an integer. the GUI width.
     */
    public int getGuiWidth() {
        return guiWidth;
    }

    /**
     * @return an integer. the GUI height.
     */
    public int getGuiHeight() {
        return guiHeight;
    }

    /**
     * @return a GUI object.
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * @param newGui a GUI object. the future GUI to be
     */
    public void setGui(GUI newGui) {
        this.gui = newGui;
    }

    /**
     * @return a spriteCollection object.
     */
    public SpriteCollection getSprites() {
        return sprites;
    }

    /**
     * @return the width/height of the corner block.
     */
    public int getWidthORHeight() {
        return widthORHeight;
    }

    /**
     * @return a GameObjects.GameEnvironment object.
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * @return a Counter object, the counter representing the number of remaining blocks.
     */
    public Counter getRemainingBlocks() {
        return remainingBlocks;
    }

    /**
     * @return a Counter object, the counter representing the number of remaining balls.
     */
    public Counter getRemainingBalls() {
        return remainingBalls;
    }

    /**
     * @return a Counter object, the counter representing the score.
     */
    public Counter getCurrentScore() {
        return currentScore;
    }

    private boolean getRunning() {
        return this.running;
    }

    public LevelInformation getLevelInformation() {
        return levelInformation;
    }

    /**
     * adds the input Interfaces.Collidable to the Interfaces.Collidable list of the GameObjects.GameEnvironment object.
     *
     * @param c a Interfaces.Collidable object.
     */
    public void addCollidable(Collidable c) {
        this.getEnvironment().addCollidable(c);
    }

    /**
     * adds the input Interfaces.Sprite to the Interfaces.Sprite list of the GameObjects.SpriteCollection object.
     *
     * @param s a Interfaces.Sprite object.
     */
    public void addSprite(Sprite s) {
        this.getSprites().addSprite(s);
    }

    /**
     * removes the input Collidable from the GameEnvironment of this Game.
     *
     * @param c a Collidable object.
     */
    public void removeCollidable(Collidable c) {
        this.getEnvironment().removeCollidable(c);
    }

    /**
     * removes the input Sprite from the Sprite Collection of this Game.
     *
     * @param s a Sprite object.
     */
    public void removeSprite(Sprite s) {
        this.getSprites().removeSprite(s);
    }

    /**
     * Initialize a new game: creates the Blocks, Balls (and GameObjects.Paddle) and adds them to the game.
     */
    public void initialize() {
//        this.setGui(new GUI("title", getGuiWidth(), getGuiHeight()));

        BlockRemover blockRemover = new BlockRemover(this, this.getRemainingBlocks());
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.getCurrentScore());

        List<HitListener> blockHitListeners = new ArrayList<>() {{
            add(blockRemover);
            add(scoreTrackingListener);
        }};

        BallRemover ballRemover = new BallRemover(this, this.getRemainingBalls());

        this.addBackgroundColor();
//        this.addBackgroundColor();
        this.addIndicatorBlock();
        this.addBorderBlocks(ballRemover);
//        this.addBalls();
        this.addBalls(4);
//        this.addPaddle();
        this.addPaddle();
//        this.addBlocks(blockHitListeners);
        this.addBlockss(blockHitListeners);

    }

    public void addBackgroundColor() {
        this.addSprite(this.getLevelInformation().getBackground());
    }

    public void addBalls(int j) {
        int size = 4, startX = this.getGuiWidth() / 2, startY = this.getGuiHeight() - 2 * this.getWidthORHeight() - size;
        int numOfBalls = this.getLevelInformation().numberOfBalls();

        Ball[] balls = new Ball[numOfBalls];
        for (int i = 0; i < numOfBalls; i++) {
            balls[i] = new Ball(new Point(startX, startY), size, Color.WHITE, this.getLevelInformation().initialBallVelocities().get(i), this.getEnvironment());
            balls[i].addToGame(this);
        }
        this.getRemainingBalls().increase(numOfBalls);


    }

    public void addBlockss(List<HitListener> hitListeners) {
        List<Block> blocks = this.getLevelInformation().blocks();
        for (Block b : blocks) {
            b.addToGame(this);
            for (HitListener hl : hitListeners) {
                b.addHitListener(hl);
            }
        }
        this.getRemainingBlocks().increase(blocks.size());
    }

    /**
     * adds a blue rectangle to the screen to use as a blue background.
     *
     * @param d a DrawSurface. the surface we want to add the rectangle to.
     */
    public void addBackgroundColor(DrawSurface d) {
        if (d != null) {
            d.setColor(Color.blue);
            d.fillRectangle(0, 0, this.getGuiWidth(), this.getGuiHeight());
        }


    }

    /**
     * adds the colored blocks to the GameObjects.Game, with the same pattern of the example in the task page.
     *
     * @param hitListeners a list of HitListeners objects, the hit listeners for the soon to be added blocks.
     */
    public void addBlocks(List<HitListener> hitListeners) {
        java.awt.Color[] colors = {Color.gray, Color.red, Color.yellow, Color.cyan, Color.pink, Color.green};

        int blockWidth = 50, blockHeight = 20;
        int numOfRows = colors.length, numOfColumns = 12;
        int startX = this.getGuiWidth() - numOfColumns * blockWidth - this.getWidthORHeight() - 1, startY = 100;
        int rowCounter = 0, columnCounter;
        while (rowCounter < numOfRows) {
            columnCounter = 0;
            while (columnCounter < numOfColumns) {
                Block temp = new Block(new Rectangle(new Point(startX, startY), blockWidth, blockHeight),
                        colors[rowCounter]);
                temp.addToGame(this);
                for (HitListener hl : hitListeners) {
                    temp.addHitListener(hl);
                }
                startX += blockWidth;
                columnCounter++;
            }
            this.remainingBlocks.increase(columnCounter);
            startY += blockHeight;
            numOfColumns--;
            startX = this.getGuiWidth() - numOfColumns * blockWidth - this.getWidthORHeight() - 1;
            rowCounter++;

        }
    }

    /**
     * adds the paddle to the game.
     */
    public void addPaddle() {
        int paddleWidth = this.getLevelInformation().paddleWidth(),
                paddleHeight = 20, paddleX = (this.guiWidth - paddleWidth) / 2,
                paddleY = this.getGuiHeight() - this.getWidthORHeight() - paddleHeight;
        java.awt.Color paddleColor = Color.ORANGE;

        Paddle paddle = new Paddle(this.getGui().getKeyboardSensor(), new Rectangle(new Point(paddleX, paddleY),
                paddleWidth, paddleHeight), paddleColor, this.getEnvironment(), this.getLevelInformation().paddleSpeed());
        paddle.addToGame(this);
    }

    /**
     * adds 2 balls to the game.
     */
    public void addBalls() {
        int ballSize = 3, dx = 3, dy = 3, numOfBalls = 3;
        java.awt.Color ballColor = Color.BLACK;
        Point startPoint = new Point(this.getWidthORHeight() * 6, (double) this.getGuiHeight() / 2);

        Ball[] balls = {new Ball(startPoint, ballSize, ballColor), new Ball(startPoint, ballSize, ballColor),
                new Ball(startPoint, ballSize, ballColor)};

        balls[0].setVelocity(dx, dy);
        balls[1].setVelocity(-dx, -dy);
        balls[2].setVelocity(dx, -dy);

        for (Ball b : balls) {
            b.addToGame(this);
            b.setGameEnvironment(this.getEnvironment());
        }

        this.getRemainingBalls().increase(numOfBalls);


//        // randomly picked start points so they wont look uniform.
//        Ball b1 = new Ball(new Point(this.getWidthORHeight() * 2, this.getWidthORHeight() * 2), ballSize, ballColor,
//                new Velocity(dx, dy), this.getEnvironment());
//        Ball b2 = new Ball(new Point(this.getWidthORHeight() + this.getGuiWidth() / 3,
//                this.getWidthORHeight() * 3), ballSize,
//                ballColor, new Velocity(dx, dy), this.getEnvironment());
//
//        b1.addToGame(this);
//        b2.addToGame(this);
    }

    /**
     * adds the border blocks to the game.
     *
     * @param ballRemover a HitListener object. the hit listener for the bottom block which makes it, the DEATH block.
     */
    public void addBorderBlocks(HitListener ballRemover) {
        java.awt.Color blockColor = Color.gray;
        Block upper = new Block(new Rectangle(new Point(0, this.getWidthORHeight()), this.getGuiWidth(),
                this.getWidthORHeight()),
                blockColor);
        Block lower = new Block(new Rectangle(new Point(this.getWidthORHeight(), this.getGuiHeight()
//                - this.getWidthORHeight()),
                + 20),
//                this.getGuiWidth() - 2 * this.getWidthORHeight(), this.getWidthORHeight()), blockColor);
                this.getGuiWidth() - 2 * this.getWidthORHeight(), 1), blockColor);

        lower.addHitListener(ballRemover); //turns the lower/bottom block into the DEATH block..

        Block left = new Block(new Rectangle(new Point(0, this.getWidthORHeight()), this.getWidthORHeight(),
                this.getGuiHeight() - this.getWidthORHeight()), blockColor);

        Block right = new Block(new Rectangle(new Point(this.getGuiWidth() - this.getWidthORHeight(),
                this.getWidthORHeight()),
                this.getWidthORHeight(), this.getGuiHeight() - this.getWidthORHeight()), blockColor);

        Block[] blocks = {upper, lower, right, left};
        for (Block b : blocks) {
            b.addToGame(this);
        }
    }

    /**
     * initializes and adds the IndicatorBlock to the game.
     */
    public void addIndicatorBlock() {
        Block indicatorBlock = new Block(new Rectangle(new Point(0, 0), this.getGuiWidth(), this.getWidthORHeight()),
                Color.WHITE);
        ScoreIndicator scoreTrackingListener = new ScoreIndicator(this.getCurrentScore(), indicatorBlock, this.getLevelInformation().levelName());
        scoreTrackingListener.addToGame(this);


    }

    /**
     * Run the game -- start the animation loop.
     */
//    public void run() {
//        int framesPerSecond = 60;
//        int millisecondsPerFrame = 1000 / framesPerSecond;
//        Sleeper sleeper = new Sleeper();
//
//        while (true) {
//            long startTime = System.currentTimeMillis(); // timing
//            DrawSurface d = this.getGui().getDrawSurface();
//
//            // game-specific logic
//            this.addBackgroundColor(d);
//            this.sprites.drawAllOn(d);
//            this.sprites.notifyAllTimePassed();
//
//
//            //stopping conditions
//            if (this.getRemainingBlocks().getValue() == 0) {
//                this.getCurrentScore().increase(100);
//                this.getGui().close();
//                return;
//            }
//
//            if (this.getRemainingBalls().getValue() == 0) {
//                this.getGui().close();
//                return;
//            }
//
//            this.getGui().show(d);
//
//            // timing
//            long usedTime = System.currentTimeMillis() - startTime;
//            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
//            if (milliSecondLeftToSleep > 0) {
//                sleeper.sleepFor(milliSecondLeftToSleep);
//            }
//
//        }
//    }
//
//    public void run(int i) {
//        int framesPerSecond = 60;
//        int millisecondsPerFrame = 1000 / framesPerSecond;
//        Sleeper sleeper = new Sleeper();
//
//
//        while (!this.shouldStop()) { // shouldStop() is in charge of stopping condition.
//            long startTime = System.currentTimeMillis(); // timing
//            DrawSurface d = gui.getDrawSurface();
//
//            this.doOneFrame(d); // doOneFrame(DrawSurface) is in charge of the logic.
//
//            gui.show(d);
//            long usedTime = System.currentTimeMillis() - startTime;
//            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
//            if (milliSecondLeftToSleep > 0) {
//                sleeper.sleepFor(milliSecondLeftToSleep);
//            }
//        }
//    }
    public void run() {
//        this.runner.run(new CountdownAnimation(1,20,this.getSprites())); // countdown before turn starts.
        this.running = true;
        this.runner.run(this);
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        // game-specific logic
//        this.addBackgroundColor(d);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();


        //stopping conditions
        if (this.getRemainingBlocks().getValue() == 0) {
            this.getCurrentScore().increase(100);
            this.running = false;
        }

        if (this.getRemainingBalls().getValue() == 0) {
            this.running = false;
        }

        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.getRunning();
    }


}
