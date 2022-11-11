//Gili Wolf 315144907

import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * this class is in charge of runnning an amiation according to its 'doOneFrame'
 * and 'shouldStop' methods.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private final biuoop.Sleeper sleeper = new biuoop.Sleeper();

    /**
     * constructor, initialise the gui and frames per second.
     * @param gui gui of the animation
     * @param framesPerSecond of the animation
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        if (gui != null) {
            this.gui = gui;
            this.framesPerSecond = framesPerSecond;
        }
    }

    /**
     * runs the animation as long as the animation should continue.
     * @param animation running animation
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = (int) 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}