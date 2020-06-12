package gameobjects.animation.endscreens;

import gameobjects.Counter;

public class GameOverScreen extends EndScreen {
    public GameOverScreen(Counter currentScore) {
        super("Game Over. Your Score is ", currentScore);
    }
}
