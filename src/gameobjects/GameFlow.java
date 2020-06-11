package gameobjects;

import biuoop.KeyboardSensor;
import gameobjects.animation.AnimationRunner;
import gameobjects.animation.EndScreen;
import gameobjects.gamelevels.GameLevel;
import interfaces.LevelInformation;

import java.util.List;

public class GameFlow {
    private Counter currentScore;
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;

    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.currentScore = new Counter();
    }

    public void runLevels(List<LevelInformation> levels) {
        if (levels == null) {
            return;
        }
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.animationRunner, this.keyboardSensor, this.currentScore);

            level.initialize();

            while (level.getRemainingBalls().getValue() > 0 && level.getRemainingBlocks().getValue() > 0) {
                level.run();
            }

            if (level.getRemainingBalls().getValue() == 0) {
                animationRunner.run(new EndScreen(this.keyboardSensor, this.currentScore, "Game Over. Your Score is "));
                animationRunner.getGui().close();
                break;
            }

        }
        animationRunner.run(new EndScreen(this.keyboardSensor, this.currentScore, "You Win! Your score is "));
        animationRunner.getGui().close();
    }
}