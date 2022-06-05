//Gili Wolf 315144907
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * this method holds Level 1 information of the game.
 */
public class Level2 implements LevelInformation {
    private final int numOfBalls = 10;
    private final int numOfBlocks = 15;
    @Override
    public int numberOfBalls() {
        return numOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> listOfVelocities = new ArrayList<Velocity>();
        double angle = 35;
        double speed = 4.0;

        for (int i = 0; i < numOfBalls; i++) {
            listOfVelocities.add(Velocity.fromAngleAndSpeed(angle, speed));
            angle += 10;
            if (i == 5) {
                angle += 10;
            }
        }
        return listOfVelocities;
    }

    @Override
    public int paddleSpeed() {
        return 2;
    }

    @Override
    public int paddleWidth() {
        return 400;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        Block background =  new Block(new Point(0, 0), 800, 600);
        background.setColor(Color.WHITE);
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> listOfBlocks = new ArrayList<Block>();
        int xCoordinate = 25;
        int yCoordinate = 200;
        int widthOfBlocks = 50;
        for (int i = 0; i < numOfBlocks; i++) {
            Block block = new Block(new Point(xCoordinate, yCoordinate), widthOfBlocks, 30);
            setColors(i, block);
            listOfBlocks.add(block);
            xCoordinate += widthOfBlocks;
        }
        return listOfBlocks;

    }

    @Override
    public int numberOfBlocksToRemove() {
        return numOfBlocks;
    }

    @Override
    public Color getColorOfText() {
        return Color.BLACK;
    }

    private void setColors(int i, Block block) {
        if (i == 0 || i == 1) {
            block.setColor(Color.RED);
        } else if (i == 2 || i == 3) {
            block.setColor(Color.orange);
        } else if (i == 4 || i == 5) {
            block.setColor(Color.YELLOW);
        } else if (i == 6 || i == 7 || i == 8) {
            block.setColor(Color.green);
        } else if (i == 9 || i == 10) {
            block.setColor(Color.blue);
        } else if (i == 11 || i == 12) {
            block.setColor(Color.pink);
        } else {
            block.setColor(Color.CYAN);
        }

    }
}
