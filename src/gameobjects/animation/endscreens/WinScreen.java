package gameobjects.animation.endscreens;

import gameobjects.Counter;

public class WinScreen extends EndScreen {
    public WinScreen(Counter currentScore) {
        super("You Win! Your score is ", currentScore);
    }
}
