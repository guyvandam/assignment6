import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import gameobjects.Counter;
import gameobjects.animation.AnimationRunner;
import gameobjects.animation.KeyPressStoppableAnimation;
import gameobjects.animation.PauseScreen;
import gameobjects.animation.endscreens.WinScreen;
import interfaces.Animation;

public class test {
    public static void main(String[] args) {
        GUI gui = new GUI("rr", 800, 600);
        AnimationRunner runner = new AnimationRunner(gui, 60, new Sleeper());
        Animation a1 = new WinScreen(new Counter());
        Animation a2 = new PauseScreen(); // also an Animation
        Animation a1k = new KeyPressStoppableAnimation(gui.getKeyboardSensor(), KeyboardSensor.SPACE_KEY, a1);
        Animation a2k = new KeyPressStoppableAnimation(gui.getKeyboardSensor(), KeyboardSensor.SPACE_KEY, a2);
        runner.run(a1k);
        runner.run(a2k);
        gui.close();
    }
}
