//Gili Wolf 315144907

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * this method is a decorator for any KeyPressStoppableAnimation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor keyboard;
    private final String key;
    private final Animation animation;
    private boolean stop = false;
    private boolean isAlreadyPressed = true;

    /**
     * constructor.
     * @param sensor keyboard
     * @param key string key of stopping the animation
     * @param animation animation to be stopped
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboard = sensor;
        this.key = key;
        this.animation = animation;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.keyboard.isPressed(key)) {
            isAlreadyPressed = false;
        }
        if (!isAlreadyPressed && this.keyboard.isPressed(key)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }

}