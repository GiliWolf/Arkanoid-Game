//Gili Wolf 315144907
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * this method holds Level 2 information of the game.
 */
public class Level3 implements LevelInformation {
    private final int numOfBalls = 2;
    private final int numberOfColumns = 10;
    private final int numOfRows = 5;
    @Override
    public int numberOfBalls() {
        return numOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> listOfVelocities = new ArrayList<Velocity>();
        int dx = -3;
        for (int i = 0; i < numOfBalls; i++) {
            listOfVelocities.add(new Velocity(dx, -6));
            dx *= (-1);
        }
        return listOfVelocities;
    }

    @Override
    public int paddleSpeed() {
        return 6;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        Block background =  new Block(new Point(0, 0), 800, 600);
        background.setColor(Color.green);
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
            for (int j = i; j < numberOfColumns; j++) {
                Block block = new Block(new Point(280 + j * widthOfBlocks, 100 + i * heightOfBlocks),
                        widthOfBlocks, heightOfBlocks);
                block.setColor(color);
                listOfBlocks.add(block);
            }
        } return listOfBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return numberOfColumns * numOfRows;
    }

    @Override
    public Color getColorOfText() {
        return Color.WHITE;
    }
}
