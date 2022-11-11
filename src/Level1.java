//Gili Wolf 315144907


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * this method holds Level 1 information of the game.
 */
public class Level1 implements LevelInformation {
    private final int numOfBalls = 1;
    @Override
    public int numberOfBalls() {
        return numOfBalls;
    }
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> listOfVelocities = new ArrayList<Velocity>();
        for (int i = 0; i < numOfBalls; i++) {
            listOfVelocities.add(new Velocity(0, -4));
        }
        return listOfVelocities;
    }
    @Override
    public int paddleSpeed() {
        return 5;
    }
    @Override
    public int paddleWidth() {
        return 80;
    }
    @Override
    public String levelName() {
        return "Direct Hit";
    }
    @Override
    public Sprite getBackground() {
        Block background =  new Block(new Point(0, 0), 800, 600);
        background.setColor(Color.BLACK);
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> listOfBlocks = new ArrayList<Block>();
        Block block = new Block(new Point(375, 100), 50, 50);
        block.setColor(Color.RED);
        listOfBlocks.add(block);
        return listOfBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

    @Override
    public Color getColorOfText() {
        return Color.white;
    }
}
