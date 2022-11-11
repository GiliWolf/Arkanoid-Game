//Gili Wolf 315144907

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * this class showing the levels of the game using the level's name, and adding itself to the game.
 */
public class LevelIndicator implements Sprite {
    private LevelInformation levelInformation;

    /**
     * constructor.
     * @param levelInformation level
     */
    public LevelIndicator(LevelInformation levelInformation) {
        this.levelInformation = levelInformation;
    }


    @Override
    public void drawOn(DrawSurface d) {

        d.setColor(Color.YELLOW);
        d.fillRectangle(500, 0, 250, 25);
        d.setColor(Color.BLACK);
        d.drawRectangle(500, 0, 250, 25);
        d.setColor(Color.BLACK);
        d.drawText(520, 20, "Level Name: " + levelInformation.levelName(), 20);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);

    }
}
