package gameobjects.gamelevels;

import constants.Constants;

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-06-12.
 * <p>
 * an abstract class to hold all the needed information of a specific level. created to prevent duplicate code.
 * </p>
 */
public abstract class Level {
    private int paddleSpeed = Constants.PADDLE_SPEED;
    private int paddleWidth = Constants.PADDLE_WIDTH;
    private String levelName;

    /**
     * constructor function.
     *
     * @param levelName a String. the level name.
     */
    public Level(String levelName) {
        this.levelName = levelName;
    }

    /**
     * @return an Integer.
     */
    public int getPaddleSpeed() {
        return paddleSpeed;
    }

    /**
     * @param paddleSpeed1 an Integer. the desired paddle speed.
     */
    public void setPaddleSpeed(int paddleSpeed1) {
        this.paddleSpeed = paddleSpeed1;
    }

    /**
     * @return an Integer. the paddle width.
     */
    public int getPaddleWidth() {
        return paddleWidth;
    }

    /**
     * @param paddleWidth1 an Integer. the desired paddle width.
     */
    public void setPaddleWidth(int paddleWidth1) {
        this.paddleWidth = paddleWidth1;
    }

    /**
     * @return a String. the level name.
     */
    public String getLevelName() {
        return levelName;
    }

}
