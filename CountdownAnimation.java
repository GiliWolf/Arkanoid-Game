//Gili Wolf 315144907

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private double numOfSec;
    private int countFrom;
    private SpriteCollection gameScreen;
    private AnimationRunner runner;
    private boolean stop;
    private final biuoop.Sleeper sleeper = new biuoop.Sleeper();
    private long sleepFor;
    private final Color color;

    /**
     * Constructor.
     * @param numOfSeconds number of seconds to countdown
     * @param countFrom number to count from
     * @param gameScreen sprite collection of the animation
     * @param color color of the countdown text
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, Color color) {
        this.numOfSec = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.sleepFor = (long) (1000 * (numOfSec / countFrom));
        this.color = color;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.setColor(color);

            if (numOfSec != (-1)) {
                d.drawText(d.getWidth() / 3 + 50, d.getHeight() / 2, Integer.toString(countFrom) + "...", 150);
                this.sleeper.sleepFor(sleepFor);
                this.countFrom--;
                this.numOfSec--;
            } else if (numOfSec == (-1)) {
                d.setColor(color);
                d.drawText(d.getWidth() / 3, d.getHeight() / 2, "GO!", 150);
                //this.sleeper.sleepFor(sleepFor);
                this.countFrom--;
                this.numOfSec--;
            }
        }
        @Override
    public boolean shouldStop() {
        if (numOfSec <  -2) {
            return true;
        }
        return false;

    }

}
