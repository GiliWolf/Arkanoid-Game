//Gili Wolf 315144907
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**.
 * this class represent a paddle represented by a blocks.
 * gets from the Game's proportion in order to be constructed accordingly
 */
public class Paddle implements Sprite, Collidable {
    private final Block block;
    private biuoop.KeyboardSensor keyboard;
    private GameEnvironment gameEnvironment;
    private final GUI gui;
    private final int moveSize;
    private final int widthOfFrameBlocks;
    private final int widthOfGUI;
    private final int height;

    /**.
     * constructor
     * @param gui gui of the game
     * @param widthOfFrameBlocks of the game
     * @param widthOfGUI of the game
     * @param height of the gui of the game
     * @param widthOfPaddle width of the paddle
     * @param speed of the paddle
     */
    public Paddle(GUI gui, int widthOfFrameBlocks, int widthOfGUI, int height, int widthOfPaddle, int speed) {
        if (gui != null) {
            this.gui = gui;
        } else {
            this.gui = new GUI("nullllllllllll", 100, 100);
        }

        if (widthOfFrameBlocks > 0) {
            this.widthOfFrameBlocks = widthOfFrameBlocks;
        } else {
            this.widthOfFrameBlocks = 5;
        }

        if (widthOfGUI > 0) {
            this.widthOfGUI = widthOfGUI;
        } else {
            this.widthOfGUI = 100;
        }
        if (height > 0) {
            this.height = height;
        } else {
            this.height = 100;
        }
        this.moveSize = speed;
        this.block = new Block(new Point(((int) widthOfGUI / 2) - (widthOfPaddle / 2), (int) height - 30),
                widthOfPaddle, 30);
    }

    /**.
     * move the paddle 5 coordinates to the left
     */
    public void moveLeft() {
        if (block.getCollisionRectangle().getUpperLeft().getX() - moveSize >= this.widthOfFrameBlocks) {
            block.getCollisionRectangle().setUpperLeft(new Point(block.getCollisionRectangle().getUpperLeft().getX()
                    - moveSize,
                    block.getCollisionRectangle().getUpperLeft().getY()));

        }
    }

    /**.
     * move the paddle 5 coordinates to the right
     */
    public void moveRight() {
        if (block.getCollisionRectangle().getUpperRightPoint().getX() + moveSize
                <= this.widthOfGUI - this.widthOfFrameBlocks) {
            block.getCollisionRectangle().setUpperLeft(new Point(block.getCollisionRectangle().getUpperLeft().getX()
                    + moveSize,
                    block.getCollisionRectangle().getUpperLeft().getY()));
        }
    }

    /**.
     * changes the paddle location according to the user's keyboard pressing
     * using the move right and move left methods.
     */
    // Sprite
    public void timePassed() {
        //this.block.timePassed();
        this.keyboard = gui.getKeyboardSensor();
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**.
     * draws itself on the draw surface.
     * @param d draw surface
     */
    public void drawOn(DrawSurface d) {
        this.block.setColor(Color.BLACK);
        this.block.drawOn(d);
    }

    /**.
     * setter
     * @param gameEnvironment of the game
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**.
     * getter
     * @return gameEnvironment of theh game
     */
    public GameEnvironment getGameEnvironment() {
        return gameEnvironment;
    }

    /**.
     * get the Paddle's rectangle
     * @return collision rectangle
     */
    // Collidable
    public Rectangle getCollisionRectangle() {
        return this.block.getCollisionRectangle();

    }
    /**.
     * checks on which zone of the paddle the collision point is
     * @param collisionPoint of the paddle
     * @return number of the paddle zone
     */
    private int puddleZones(Point collisionPoint) {
        int widthOfBlock = (int) this.block.getCollisionRectangle().getWidth();
        double xCoordinateOfCollision = collisionPoint.getX();
        double xCoordinateOfBlock = this.block.getCollisionRectangle().getUpperLeft().getX();
        double xCoordinate = xCoordinateOfCollision + xCoordinateOfBlock;
        if ((xCoordinateOfCollision  > (double) ((widthOfBlock * 4) / 5) + xCoordinateOfBlock)
                && (xCoordinateOfCollision
                < this.block.getCollisionRectangle().getUpperRightPoint().getX() + xCoordinateOfBlock)) {
            return 5;
        } else if ((xCoordinateOfCollision  > (double) ((widthOfBlock * 3) / 5) + xCoordinateOfBlock)
                && (xCoordinateOfCollision  <= (double) ((widthOfBlock * 4) / 5) + xCoordinateOfBlock)) {
            return 4;
        } else if (xCoordinateOfCollision  >= (double) ((widthOfBlock * 2) / 5 + xCoordinateOfBlock)
                && xCoordinateOfCollision  <= (double) ((widthOfBlock * 3) / 5) + xCoordinateOfBlock) {
            return 3;
        } else if ((xCoordinateOfCollision  >= (double) (widthOfBlock / 5) + xCoordinateOfBlock)
                && (xCoordinateOfCollision  < (double) ((widthOfBlock * 2) / 5) + xCoordinateOfBlock)) {
            return 2;
        } else if ((xCoordinateOfCollision  < (double) (widthOfBlock / 5) + xCoordinateOfBlock)
                && (xCoordinateOfCollision
                >  xCoordinateOfBlock)) {
            return 1;
        } else {
            return 6;
        }




/*
        if (xCoordinateOfCollision + xCoordinateOfBlock > (double) ((widthOfBlock * 4) / 5)
                && xCoordinateOfCollision + xCoordinateOfBlock
                < this.block.getCollisionRectangle().getUpperRightPoint().getX()) {
            return 5;
        } else if (xCoordinateOfCollision + xCoordinateOfBlock > (double) ((widthOfBlock * 3) / 5)
                && xCoordinateOfCollision + xCoordinateOfBlock <= (double) ((widthOfBlock * 4) / 5)) {
            return 4;
        } else if (xCoordinateOfCollision + xCoordinateOfBlock >= (double) ((widthOfBlock * 2) / 5)
                && xCoordinateOfCollision + xCoordinateOfBlock <= (double) ((widthOfBlock * 3) / 5)) {
            return 3;
        } else if (xCoordinateOfCollision + xCoordinateOfBlock >= (double) (widthOfBlock / 5)
                && xCoordinateOfCollision + xCoordinateOfBlock < (double) ((widthOfBlock * 2) / 5)) {
            return 2;
        } else if (xCoordinateOfCollision + xCoordinateOfBlock < (double) (widthOfBlock / 5)
                && xCoordinateOfCollision + xCoordinateOfBlock
                > this.block.getCollisionRectangle().getUpperLeft().getX()) {
            return 1;
        } else {
            return 6;
        }*/
    }

    @Override
    /**
     * @param collisionPoint
     * @param currentVelocity
     * @return
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double speed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow((currentVelocity.getDy()), 2));
        if (collisionPoint != null && currentVelocity != null) {
            switch (puddleZones(collisionPoint)) {
                case 1:
                    return Velocity.fromAngleAndSpeed(150, speed);
                case 2:
                    return Velocity.fromAngleAndSpeed(120, speed);
                case 4:
                    return Velocity.fromAngleAndSpeed(60, speed);
                case 5:
                    return Velocity.fromAngleAndSpeed(30, speed);
                case 3:
                case 6:
                    return this.block.hit(hitter, collisionPoint, currentVelocity);
                default:
                    return null;
            }


                /*
                case 1:
                    return Velocity.fromAngleAndSpeed(300, speed);
                case 2:
                    return Velocity.fromAngleAndSpeed(330, speed);
                case 4:
                    return Velocity.fromAngleAndSpeed(60, speed);
                case 5:
                    return Velocity.fromAngleAndSpeed(30, speed);
                case 3:
                case 6:
                    return this.block.hit(hitter, collisionPoint, currentVelocity);
                default:
                    return null;
            }*/
        }
        return null;
    }

    /**.
     * add the padlle to the game, so it will be recognized
     * @param g game
     */
    // Add this paddle to the game.
    public void addToGame(GameLevel g) {
        if (g != null) {
            g.addSprite(this);
            g.addCollidable(this);
        }
    }
}