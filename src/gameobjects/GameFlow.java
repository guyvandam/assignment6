package gameobjects;

import biuoop.KeyboardSensor;
import gameobjects.animation.AnimationRunner;
import gameobjects.animation.KeyPressStoppableAnimation;
import gameobjects.animation.endscreens.GameOverScreen;
import gameobjects.animation.endscreens.WinScreen;
import gameobjects.gamelevels.GameLevel;
import interfaces.LevelInformation;

import java.util.List;

/**
 * @author Guy Vandam 325133148 <guyvandam@gmail.com>
 * @version 1.0
 * @since 2020-06-12.
 */
public class GameFlow {
    private Counter currentScore;
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;

    /**
     * constructor function.
     *
     * @param ar an AnimationRunner function.
     * @param ks a BIU-OOP Keyboard Sensor function.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.currentScore = new Counter();
    }

    /**
     * runs the levels in the input list.
     * <p>
     * for each level, create a GameLevel object, runs it until the player takes out all of the blocks in the
     * level. if so passes to the next level. if the player loses all if it's ball, the loop breaks, and a "Game
     * over" message (a GameOverScreen object) is presented withe player score. if the player mange to pass all the
     * level, i.e. take out all of the black in all of the levels, a "You Win" messages (a WinScreen object) is
     * presented with the player score.
     * </p>
     *
     * @param levels a List of LevelInformation object. the levels of the game.
     */
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
                animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY,
                        new GameOverScreen(this.currentScore)));

                animationRunner.getGui().close();
                break;
            }

        }
        animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                new WinScreen(this.currentScore)));

        animationRunner.getGui().close();
    }
}