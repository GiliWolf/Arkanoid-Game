//Gili Wolf 315144907
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * this class getting the score of the game and updating it on the top of the board.
 */
public class ScoreIndicator implements Sprite {
    private Counter scoreCounter;

    /**
     * constructor getting the score.
     * @param score
     */
    public ScoreIndicator(Counter score) {
        this.scoreCounter = score;
    }
    @Override
    public void drawOn(DrawSurface d) {
        DrawSurface d1 = d;
        d1.setColor(Color.CYAN);
        d1.fillRectangle(300, 0, 170, 25);
        d1.setColor(Color.BLACK);
        d1.drawRectangle(300, 0, 170, 25);
        d1.setColor(Color.BLACK);
        d.drawText(320, 20, "Score: " + Integer.toString(scoreCounter.getValue()), 25);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        if (g != null) {
            g.addSprite(this);
        }
    }
}
