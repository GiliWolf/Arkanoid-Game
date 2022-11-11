//Gili Wolf 315144907
import java.awt.Color;
import java.util.List;

/**
 * this interface force classes which implements it, to have all the information a levels should have.
 */
public interface LevelInformation {
    /**
     * number of balls in the levels.
     * @return num of balls
     */
    int numberOfBalls();
    /**
     * initializes velocity of each ball of the level.
     * @return list of velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * paddle's speed.
     * @return int
     */
    int paddleSpeed();

    /**
     * paddle's width.
     * @return int
     */
    int paddleWidth();
    // the level name will be displayed at the top of the screen.

    /**
     * string of the level's name.
     * @return string
     */
    String levelName();
    /**
     * sprite represent the level's background.
     * @return sprite
     */
    Sprite getBackground();
    // The Blocks that make up this level, each block contains
    // its size, color and location.

    /**
     * generate the blocks of the level.
     * each block contains - its size, color and location.
     * @return list of blocks
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed
     *  before the level is considered to be "cleared".
     *  This number should be <= blocks.size();
     * @return int
     */
    int numberOfBlocksToRemove();

    /**
     * color of the text for the countdown animation before the level, so it will be seen on the background.
     * @return color
     */
    Color getColorOfText();
}