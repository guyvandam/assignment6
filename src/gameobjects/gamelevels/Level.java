package gameobjects.gamelevels;

import constants.Constants;

public abstract class Level {
    protected int paddleSpeed = Constants.PADDLE_SPEED;
    protected int paddleWidth = Constants.PADDLE_WIDTH;
    protected String levelName;

    public Level(String levelName) {
        this.levelName = levelName;
    }

    public int getPaddleSpeed() {
        return paddleSpeed;
    }

    public void setPaddleSpeed(int paddleSpeed) {
        this.paddleSpeed = paddleSpeed;
    }

    public int getPaddleWidth() {
        return paddleWidth;
    }

    public void setPaddleWidth(int paddleWidth) {
        this.paddleWidth = paddleWidth;
    }

    public String getLevelName() {
        return levelName;
    }

}
