//Gili Wolf 315144907

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * this method holds Level 4 information of the game.
 */
public class Level4 implements LevelInformation {
    private final int numOfBalls = 3;
    private final int numberOfColumns = 15;
    private final int numOfRows = 7;

    @Override
    public int numberOfBalls() {
        return numOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> listOfVelocities = new ArrayList<Velocity>();
        listOfVelocities.add(new Velocity(-3, -5));
        listOfVelocities.add(new Velocity(0, -6));
        listOfVelocities.add(new Velocity(3, -5));
        return listOfVelocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        Block background =  new Block(new Point(0, 0), 800, 600);
        background.setColor(Color.BLUE);
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> listOfBlocks = new ArrayList<Block>();
        int widthOfBlocks = 50;
        int heightOfBlocks = 30;

        Random rand = new Random();
        for (int i = 0; i < numOfRows; i++) {
            Color color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            for (int j = 0; j < numberOfColumns; j++) {
                Block block = new Block(new Point(25 + j * widthOfBlocks, 100 + i * heightOfBlocks),
                        widthOfBlocks, heightOfBlocks);
                block.setColor(color);
                listOfBlocks.add(block);
            }
        }
        return listOfBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return numberOfColumns * numOfRows;
    }

    @Override
    public Color getColorOfText() {
        return Color.BLACK;
    }
}
