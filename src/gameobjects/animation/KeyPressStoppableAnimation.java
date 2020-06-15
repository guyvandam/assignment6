package gameobjects.animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;

public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed = true;
    private boolean stop = false;


    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
    }

    public KeyboardSensor getSensor() {
        return sensor;
    }

    public String getKey() {
        return key;
    }


    @Override
    public void doOneFrame(DrawSurface d) {
//        if (d != null) {
//            d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
//            if (this.getSensor().isPressed(this.getKey())) {
//                this.stop = true;
//            }
//        }

        animation.doOneFrame(d);

    }

    @Override
    public boolean shouldStop() {
//        if (this.getSensor().isPressed(this.getKey())) {
//
//        } else {
//            isAlreadyPressed = false;
//
//        }

        if (!this.getSensor().isPressed(this.getKey())) {
            isAlreadyPressed = false;
        }
        return !isAlreadyPressed && this.getSensor().isPressed(this.getKey());
//        return animation.shouldStop();
    }
}
