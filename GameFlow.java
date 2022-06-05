//Gili Wolf 315144907

import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * this method is in charge of the game flow according to the Game's logic.
 */
public class GameFlow {
    private AnimationRunner ar;
    private biuoop.KeyboardSensor keyboard;
    private Counter score = new Counter();
    private final GUI gui;

    /**.
     * constructor
     * @param ar animation runner
     * @param ks keyboard sensor of the user
     * @param gui GUI to run the game on
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.ar = ar;
        this.keyboard = ks;
        this.gui = gui;
    }

    /**
     * this method gets a list of levels of the game -
     * runs each level until there are no more blocks in it
     * if there is no more balls-  loose - the game stops and a loosing end screen will show
     * if all the levels were successfully finished - the game stops and a winning end screen will show.
     * @param levels list of levels
     */
    public void runLevels(List<LevelInformation> levels) {

        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo,
                    this.keyboard,
                    this.ar,
                    this.score,
                    this.gui);

            level.initialize();

           while (level.getBallsCounter().getValue() > 0 && level.getBlocksCounter().getValue() > 0) {
                level.run();
            }

            if (level.getBallsCounter().getValue() == 0) {
                ar.run(new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY,
                        new EndScreen(keyboard, false, score)));
                gui.close();
                return;
            }

        }
        ar.run(new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY,
                new EndScreen(keyboard, true, score)));
        gui.close();
        return;
    }
}