//Gili Wolf 315144907

import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;

/**
 * this method is in charge of holding all the parameters a game should have to run successfully,
 * and taking the users input  and create accordingly  a list of levels to be sent to the Game Flow.
 */
public class GameInformation {
    private final int width = 800;
    private final int height = 600;
    private final GUI gui = new GUI("BouncingBallAnimation", width, height);
    private final AnimationRunner ar = new AnimationRunner(gui, 60);
    private final KeyboardSensor ks = gui.getKeyboardSensor();
    private final GameFlow game = new GameFlow(ar, ks, gui);
    private  List<LevelInformation> listOfLevels;

    /**
     * Constructor - getting arguments of the user and calling this class getLevels.
     * @param args strings array from the user input
     */
    public GameInformation(String[] args) {
        getLevels(args);
    }

    /**
     * this method checking if each arguments is an int - if so, chechking if it fits any number of the levels (1-4)
     * it copies it to the int array representing the level's game.
     * if no levels were found in the input, it's calling the Default levels generator.
     * the game's levels
     * @param args
     */
    private void getLevels(String[] args) {
        int[] levelsInfo = new int[args.length];
        int temp;
        int j = 0;
        boolean useDefault = true;
        for (int i = 0; i < levelsInfo.length; i++) {
            try {
                temp = Integer.parseInt(args[i]);
            } catch (NumberFormatException nfe) {
                continue;
            }
            if (temp > 0 && temp < 5) {
                useDefault = false;
                levelsInfo[j] = temp;
                j++;
            }
        }

        if (useDefault) {
            defaultLevels();
        } else {
            generateLevels(levelsInfo);
        }
    }

    /**
     * generate a list of all four levels.
     */
    private void defaultLevels() {
        listOfLevels = new ArrayList<LevelInformation>();
        listOfLevels.add(new Level1());
        listOfLevels.add(new Level2());
        listOfLevels.add(new Level3());
        listOfLevels.add(new Level4());
    }

    /**
     * generate the list of levels according to the users input, which was organised in the get levels method.
     * @param levelsInfo array of ints represent the levels
     */
    private void generateLevels(int[] levelsInfo) {
        listOfLevels = new ArrayList<LevelInformation>();
        for (int level : levelsInfo) {
            switch (level) {
                case 1: listOfLevels.add(new Level1());
                    break;
                case 2: listOfLevels.add(new Level2());
                    break;
                case 3: listOfLevels.add(new Level3());
                    break;
                case 4: listOfLevels.add(new Level4());
                    break;
                default: return;
                }
            }
        }

    /**
     * running the game by sending the list of levels to the game flow.
     */
    public void runGame() {
        game.runLevels(listOfLevels);
    }
}
