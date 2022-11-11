//Gili Wolf 315144907

import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 * .
 * this class initialize all the game's sprites and run them according to the game's rules
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private final int width = 800;
    private final int height = 600;
    private final GUI gui;
    private final int widthOfFrameBlocks = 20;
    private final Paddle paddle;
    private Counter blocksCounter = new Counter();
    private Counter ballsCounter = new Counter();
    private Counter score;
    private BlockRemover blockRemover;
    private BallRemover  ballRemover;
    private final ScoreTrackingListener scoreTrackingListener;
    private ScoreIndicator scoreIndicator;
    private AnimationRunner runner;
    private boolean running;
    private final KeyboardSensor keyboard;
    private final LevelInformation level;

    /**
     * constructor.
     * @param level level information
     * @param keyboard sensor
     * @param runner animation runner
     * @param score Counter of score
     * @param gui Gui of the game
     */
    public GameLevel(LevelInformation level, KeyboardSensor keyboard, AnimationRunner runner, Counter score, GUI gui) {
        this.level = level;
        this.keyboard = keyboard;
        this.score = score;
        this.runner = runner;
        this.gui = gui;
        scoreTrackingListener = new ScoreTrackingListener(this, score);
        paddle = new Paddle(gui, widthOfFrameBlocks, width, height, level.paddleWidth(), level.paddleSpeed());
    }
    /**
     * .
     * getter
     *
     * @return gui of the game
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * .
     * this method adds collidble object to the game environment
     *
     * @param c collidable object
     */
    public void addCollidable(Collidable c) {
        if (c != null) {
            this.environment.addCollidable(c);
        }
    }

    /**
     * .
     * this method adds spirte object to the spirte collection
     *
     * @param s sprite
     */
    public void addSprite(Sprite s) {
        if (s != null) {
            this.sprites.addSprite(s);
        }
    }

    /**
     * generates bottom blocks.
     */
    private void generateBottomBlock() {
        Block b3 = new Block(new Point(widthOfFrameBlocks, height),
                width - widthOfFrameBlocks, widthOfFrameBlocks);
        b3.addToGame(this);
        b3.addHitListener(ballRemover);
    }
    /**.
     * generate the blocks of the frame of the game
     */
    private void generateFrameBlock() {
        Block b1 = new Block(new Point(0, 0), width - widthOfFrameBlocks, widthOfFrameBlocks);
        b1.addToGame(this);
        Block b2 = new Block(new Point(0, widthOfFrameBlocks), widthOfFrameBlocks, height - widthOfFrameBlocks);
        b2.addToGame(this);
        Block b4 = new Block(new Point(width - widthOfFrameBlocks, 0), widthOfFrameBlocks,
                height);
        b4.addToGame(this);
    }

    /**
     * .
     * this method generate the middle blocks of the game
     */
    private void generateMiddleBlocks() {
//        int numberOfColumns = 10;
//        int numOfRows = 6;
//        int widthOfBlocks = 50;
//        int heightOfBlocks = 30;
        //adding num of blocks to counter.
        this.blocksCounter.increase(this.level.numberOfBlocksToRemove());
        blockRemover = new BlockRemover(this, blocksCounter);
        Random rand = new Random();

        for (Block block : this.level.blocks()) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
        }
//        for (int i = 0; i < numOfRows; i++) {
//            Color color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
//            for (int j = i; j < numberOfColumns; j++) {
//                Block block = new Block(new Point(280 + j * widthOfBlocks, 100 + i * heightOfBlocks),
//                        widthOfBlocks, heightOfBlocks);
//                block.setColor(color);
//                block.addToGame(this);
//                block.addHitListener(blockRemover);
//                block.addHitListener(scoreTrackingListener);
//            }
        }

    /**
     * .
     * this method generate the balls of the game
     */
    private void generateBalls() {
        int numOfBalls = this.level.numberOfBalls();
        int ballRadius = 8;
        double xCoordinate, yCoordinate;
        xCoordinate = width / 2;
        yCoordinate = height - (2 * widthOfFrameBlocks);
        Random rand = new Random();
        ballsCounter.increase(numOfBalls);
        ballRemover = new BallRemover(this, ballsCounter);
        List<Velocity> listOfVelocities = level.initialBallVelocities();

        for (int i = 0; i < numOfBalls; i++) {

//            Color color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
//            Velocity velocity = new Velocity(rand.nextInt(3) + 2, rand.nextInt(3) + 2);
            Ball ball = new Ball(xCoordinate, yCoordinate, ballRadius, Color.white);
            ball.setVelocity(listOfVelocities.get(i));
            ball.setGameEnvironment(environment);
            ball.addToGame(this);
            ball.setPaddle(this.paddle);
        }
    }
    /**
     * generate score indicator.
     */
    private void generateScoreIndicator() {
        scoreIndicator = new ScoreIndicator(score);
        scoreIndicator.addToGame(this);
    }

    private void generateLevelIndicator() {
        LevelIndicator levelIndicator = new LevelIndicator(level);
        levelIndicator.addToGame(this);
    }

    /**
     * .
     * this methos initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        level.getBackground().addToGame(this);
        paddle.addToGame(this);
        generateBalls();
        generateFrameBlock();
        generateBottomBlock();
        generateMiddleBlocks();
        generateScoreIndicator();
        generateLevelIndicator();

    }

    /**
     * .
     * this method Run the game -- start the animation loop.
     * according to all sprites 'time passed' methods.
     */
    public void run() {
        this.running = true;
//        int framesPerSec = 60;
//        runner = new AnimationRunner(this.gui, framesPerSec);
        runner.run(new CountdownAnimation(3, 3, this.sprites, this.level.getColorOfText()));
        runner.run(this);
    }

    /**.
     * remove collidable from the environment
     * @param c
     */
    public void removeCollidable(Collidable c) {
        if (c != null) {
            this.environment.removeCollidable(c);
        }
    }

    /**.
     * remove sprite from the game.
     * @param s
     */
     public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
     }


    @Override
    public void doOneFrame(DrawSurface d) {



        if (ballsCounter.getValue() == 0) {
            this.running = false;
        }
        if (blocksCounter.getValue() == 0) {
            score.increase(100);
            this.running = false;
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * returns ball's counter.
     * @return ballsCounter
     */
    public Counter getBallsCounter() {
        return ballsCounter;
    }

    /**
     * returns blocks counter.
     * @return blocksCounter
     */
    public Counter getBlocksCounter() {
         return blocksCounter;
    }
}