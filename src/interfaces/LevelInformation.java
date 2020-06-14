package interfaces;

import gameobjects.sprites.Block;
import velocity.Velocity;

import java.util.List;

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-06-12.
 */
public interface LevelInformation {
    /**
     * @return an Integer. the number of balls in the level.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     *
     * @return a Java-ArrayList of Velocity objects. the velocities of the balls.
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return an Integer. the paddle speed. see the Paddle Object for more information. defaults to Constants
     * .PADDLE_SPEED.
     */
    int paddleSpeed();

    /**
     * @return an Integer. the paddle width. defaults to Constants.PADDLE_WIDTH.
     */
    int paddleWidth();

    /**
     * @return a String. the Level name.
     */
    String levelName();


    /**
     * @return a Sprite object. the background of the level.
     */
    Sprite getBackground();


    /**
     * @return a Java-ArrayList of Block objects. the blocks that make up the level.
     */
    List<Block> blocks();

    /**
     * @return an Integer. the number of blocks to be removed.
     */
    int numberOfBlocksToRemove();
}