package gameobjects;

import biuoop.KeyboardSensor;
import gameobjects.animation.AnimationRunner;
import gameobjects.animation.KeyPressStoppableAnimation;
import gameobjects.animation.endscreens.GameOverScreen;
import gameobjects.animation.endscreens.WinScreen;
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
//                animationRunner.run(new EndScreen(this.keyboardSensor, this.currentScore, "Game Over. Your Score is "));
//                animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY,
//                        new EndScreen("Game Over. Your Score is ", this.currentScore)));

                animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY,
                        new GameOverScreen(this.currentScore)));

                animationRunner.getGui().close();
                break;
            }

        }
//        animationRunner.run(new EndScreen(this.keyboardSensor, this.currentScore, "You Win! Your score is "));
//        animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor,KeyboardSensor.SPACE_KEY,
//                new EndScreen("You Win! Your score is ", this.currentScore)));
        animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                new WinScreen(this.currentScore)));
        animationRunner.getGui().close();
    }
}