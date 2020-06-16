package gameobjects.gamelevels;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import constants.Constants;
import gameobjects.Counter;
import gameobjects.GameEnvironment;
import gameobjects.SpriteCollection;
import gameobjects.animation.AnimationRunner;
import gameobjects.animation.CountdownAnimation;
import gameobjects.animation.KeyPressStoppableAnimation;
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
import interfaces.Animation;
import interfaces.LevelInformation;
import interfaces.HitListener;
import interfaces.Sprite;
import interfaces.Collidable;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 3.0
 * @since 2020-03-28.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter remainingBlocks; // the number of blocks in the game.
    private Counter remainingBalls; // the number of balls in the game.
    private Counter currentScore;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation levelInformation;
    private KeyboardSensor keyboard;

    private BallRemover ballRemover;
    private List<HitListener> blockHitListeners;


    /**
     * constructor method.
     *
     * @param levelInformation a LevelInformation object.
     * @param runner           a AnimationRunner object. to run the game animation.
     * @param keyboardSensor   a BIU-OOP object.
     * @param currentScore     a Counter object.
     */
    public GameLevel(LevelInformation levelInformation, AnimationRunner runner, KeyboardSensor keyboardSensor,
                     Counter currentScore) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
        this.currentScore = currentScore;
        this.runner = runner;
        this.keyboard = keyboardSensor;
        this.levelInformation = levelInformation;
        this.initializeHitListeners();
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
     * @return a spriteCollection object.
     */
    public SpriteCollection getSprites() {
        return sprites;
    }

    /**
     * @return the width/height of the corner block.
     */
    public int getWidthORHeight() {
        return Constants.WIDTH_OR_HEIGHT;
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

    /**
     * @return a boolean. the "running" variable value.
     */
    private boolean getRunning() {
        return this.running;
    }

    /**
     * @return a LevelInformation object.
     */
    public LevelInformation getLevelInformation() {
        return levelInformation;
    }

    /**
     * @return a BIU-OOP KeyboardSensor object.
     */
    public KeyboardSensor getKeyboard() {
        return keyboard;
    }

    /**
     * @return a BallRemover object.
     */
    public BallRemover getBallRemover() {
        return ballRemover;
    }

    /**
     * @return a java-List of HitListeners objects.
     */
    public List<HitListener> getBlockHitListeners() {
        return blockHitListeners;
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
        this.addBackgroundColor();
        this.addIndicatorBlock();
        this.addBorderBlocks();
        this.addBalls();
        this.addPaddle();
        this.addBlocks();


    }

    /**
     * initializes the HitListeners for the blocks (the regular and 'death' blocks) and the score hit listener.
     * called from the constructor to avoid a null-pointer exception.
     */
    public void initializeHitListeners() {
        BlockRemover blockRemover = new BlockRemover(this, this.getRemainingBlocks());
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.getCurrentScore());

        this.blockHitListeners = new ArrayList<>() {{
            add(blockRemover);
            add(scoreTrackingListener);
        }};

        this.ballRemover = new BallRemover(this, this.getRemainingBalls());

    }

    /**
     * adds the background color by creating a new Background object with the desired color, and adding it to the
     * SpriteCollection of the GameLevel.
     */
    public void addBackgroundColor() {
        this.addSprite(this.getLevelInformation().getBackground());
    }

    /**
     * adds the balls to the game with a center horizontally, 5 pixels above the paddle starting position, with the
     * velocities from the LevelInformation object.
     */
    public void addBalls() {
        int size = Constants.BALL_SIZE,
                startX = this.getGuiWidth() / 2, startY =
                this.getGuiHeight() - this.getWidthORHeight() - size - Constants.PADDLE_HEIGHT
                        - Constants.PIXELS_BETWEEN_BALL_AND_PADDLE;
        int numOfBalls = this.getLevelInformation().numberOfBalls();

        Ball[] balls = new Ball[numOfBalls];
        for (int i = 0; i < numOfBalls; i++) {
            balls[i] = new Ball(new Point(startX, startY), size, Color.WHITE,
                    this.getLevelInformation().initialBallVelocities().get(i), this.getEnvironment());
            balls[i].addToGame(this);
        }
        this.getRemainingBalls().increase(numOfBalls);


    }

    /**
     * adds the blocks from the LevelInformation object to the game, and set the HitListeners for each one of them.
     */
    public void addBlocks() {
        List<Block> blocks = this.getLevelInformation().blocks();
        for (Block b : blocks) {
            b.addToGame(this);
            for (HitListener hl : this.getBlockHitListeners()) {
                b.addHitListener(hl);
            }
        }
        this.getRemainingBlocks().increase(blocks.size());
    }


    /**
     * adds the paddle to the game.
     */
    public void addPaddle() {
        int paddleWidth = this.getLevelInformation().paddleWidth(),
                paddleHeight = Constants.PADDLE_HEIGHT, paddleX = (this.getGuiWidth() - paddleWidth) / 2,
                paddleY = this.getGuiHeight() - this.getWidthORHeight() - paddleHeight;
        java.awt.Color paddleColor = Color.ORANGE;

        Paddle paddle = new Paddle(this.getKeyboard(), new Rectangle(new Point(paddleX, paddleY),
                paddleWidth, paddleHeight), paddleColor, this.getEnvironment(),
                this.getLevelInformation().paddleSpeed());
        paddle.addToGame(this);
    }

    /**
     * adds the border blocks to the game.
     */
    public void addBorderBlocks() {
        java.awt.Color blockColor = Color.gray;
        Block upper = new Block(new Rectangle(new Point(0, this.getWidthORHeight()), this.getGuiWidth(),
                this.getWidthORHeight()),
                blockColor);
        Block lower = new Block(new Rectangle(new Point(this.getWidthORHeight(), this.getGuiHeight()
                + 20),
                this.getGuiWidth() - 2 * this.getWidthORHeight(), 1), blockColor);

        lower.addHitListener(this.getBallRemover()); //turns the lower/bottom block into the DEATH block..

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
        ScoreIndicator scoreTrackingListener = new ScoreIndicator(this.getCurrentScore(), indicatorBlock,
                this.getLevelInformation().levelName());
        scoreTrackingListener.addToGame(this);


    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, this.getSprites())); // countdown before turn starts.
        this.running = true;
        this.runner.run(this);
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        // game-specific logic
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
            this.runner.run(new KeyPressStoppableAnimation(this.getKeyboard(),
                    KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.getRunning();
    }


}
