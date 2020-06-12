package gameobjects.animation;

import biuoop.DrawSurface;
import interfaces.Animation;

public class PauseScreen implements Animation {
//    private boolean stop;

//    public PauseScreen() {
//        this.stop = false;
//    }
///
//    public boolean isStop() {
//        return stop;
//    }
//
//    public void setStop(boolean stop) {
//        this.stop = stop;
//    }

    public void doOneFrame(DrawSurface d) {
        if (d != null) {
            d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        }

//        if (d == null) {
//            return;
//        }
//        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
//            this.stop = true;
//        }

    }

    public boolean shouldStop() {
        return false;
    }
}