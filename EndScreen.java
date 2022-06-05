//Gili Wolf 315144907

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * this method represent the End Screen by an animation writing the score on the GUI screen.
 */
public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private boolean win;
    private Counter score;

    /**
     * constructor.
     * @param keyboard keyboard of the user
     * @param win boolean win - true/ loose -false
     * @param score counter of the score
     */
    public EndScreen(KeyboardSensor keyboard,  boolean win, Counter score) {
        this.keyboard = keyboard;
        this.win = win;
        this.score = score;
    }

    /**
     * printing "you win" and a smiley face when winning, printing "game over" when loosing.
     * @param d draw-surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        if (win) {
            d.drawText(d.getWidth() / 5, d.getHeight() / 2,
                    "You Win! -  Your score is " + score.getValue(), 32);
            d.setColor(Color.YELLOW);
            d.drawCircle(400, 400, 70);
            d.fillCircle(400, 400, 70);
            d.setColor(Color.BLACK);
            d.fillCircle(400, 400, 50);
            d.setColor(Color.yellow);
            d.fillCircle(400, 400, 40);
            d.fillRectangle(350, 350, 100, 60);
            d.setColor(Color.BLACK);
            d.fillCircle(380, 375, 10);
            d.fillCircle(420, 375, 10);
        } else {
            d.drawText(d.getWidth() / 4, d.getHeight() / 2,
                    "Game Over - Your score is " + score.getValue(), 32);

        }

    }
    @Override
    public boolean shouldStop() {
        return false;
    }
}
