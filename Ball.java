// Gili Wolf 315144907

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * .
 * this class generate balls represented by the center Point, color and radius
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity;
    private Limit limit;
    private GameEnvironment gameEnvironment;
    private Line trajectory;
    private Paddle paddle;

    /**
     * .
     * default constructor
     */
    public Ball() {
        this.limit = new Limit(0, 0, 100, 100);
        this.center = new Point((double) this.limit.getWidth() / 2, (double) this.limit.getHeight() / 2);
        this.radius = 10;
        this.color = Color.BLACK;
    }

    /**
     * .
     * constructor
     *
     * @param center center point
     * @param r      radius
     * @param color  color
     */
    public Ball(Point center, int r, Color color) {
        //default
        if (r <= 0) {
            r = 30;
        }
        if (center == null) {
            center = new Point(30, 30);
        }
        if (color == null) {
            color = Color.BLACK;
        }
        //input
        this.radius = r;
        this.center = center;
        this.color = color;
    }

    /**
     * .
     * constructor
     *
     * @param x     x value of center point
     * @param y     y value of center point
     * @param r     radius
     * @param color color
     */
    public Ball(double x, double y, int r, Color color) {
        //default
        if (x < 0) {
            x = 30;
        }
        if (y < 0) {
            y = 30;
        }
        if (r <= 0) {
            r = 30;
        }
        if (color == null) {
            color = Color.BLACK;
        }

        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
    }

    /**
     * .
     * getter
     *
     * @return int x value
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * .
     * getter
     *
     * @return int y value
     */

    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * .
     * setter
     *
     * @param x x value
     */
    public void setX(double x) {
        this.center.setX(x);
    }

    /**
     * .
     * setter
     *
     * @param y y value
     */
    public void setY(double y) {
        this.center.setY(y);
    }

    /**
     * .
     * getter
     *
     * @return int size
     */
    public int getSize() {
        return (int) this.radius;
    }

    /**.
     * getter
     *
     * @return GameEnvironment OF THE BALL
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * .
     * setter
     *
     * @param radius radius
     */
    public void setSize(int radius) {
        this.radius = radius;
    }

    /**
     * .
     * getter
     *
     * @return color colo
     */
    public Color getColor() {
        return this.color;
    }

    /**.
     * @return trajectory of the ball
     */
    public Line getTrajectory() {
        return trajectory;
    }

    /**.
     *this method sets the trajectory of the ball according to its velocity
     */
    public void setTrajectory() {
        this.trajectory = new Line(this.center.getX(), this.center.getY(),
                this.velocity.getDx() + this.center.getX(), this.velocity.getDy() + this.center.getY());
    }

    /**
     * .
     * setter
     *
     * @param color colo
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * .
     * draws the ball on the given DrawSurface
     *
     * @param surface surface
     */
    public void drawOn(DrawSurface surface) {
        DrawSurface d = surface;
        d.setColor(this.color);
        d.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
        d.setColor(Color.BLACK);
        d.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }

    /**
     * .
     * setter
     *
     * @param v velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
        this.setTrajectory();
    }

    /**
     * setter.
     * @param paddle paddle of the game
     */
    public void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }

    /**
     * .
     * setter
     *
     * @param dx x difference
     * @param dy y difference
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
        this.setTrajectory();
    }

    /**
     * .
     * getter
     *
     * @return velocity velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * .
     * setter
     *
     * @param limit limit
     */
    public void setLimit(Limit limit) {
        this.limit = limit;
    }

    /**.
     * setter
     *
     * @param gameEnvironment of the ball
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * .
     * getter
     *
     * @return limit limit
     */
    public Limit getLimit() {
        return limit;
    }

    /**.
     * getter
     * @return center of the ball
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * setter.
     * @param center of the ball
     */
    public void setCenter(Point center) {
        this.center = center;
    }

    /**.
     * this method checks if the ball is inside the limits of the paddle
     * @return true or false
     */
    private boolean inPaddleLimits() {
        double xRightCoordinate = this.paddle.getCollisionRectangle().getUpperRightPoint().getX();
        double xLeftCoordinate = this.paddle.getCollisionRectangle().getUpperLeft().getX();
        double yCoordinate = this.paddle.getCollisionRectangle().getUpperRightPoint().getY();
        double xBallLocation = this.center.getX();
        double yBallLocation = this.center.getY();
        if ((xBallLocation < xRightCoordinate && yBallLocation > yCoordinate
                && xBallLocation > xLeftCoordinate)) {
            return true;
        }
        return false;
        }
    /**.
     * this method sets the ball location to get outside the paddle limits
     */
    private void setOutsideOfPaddle() {
        double reverseX = this.center.getX() - this.velocity.getDx();
        double reverseY = this.center.getY() - this.velocity.getDy();
        Boolean cont = true;
        Line newTrajectory = new Line(this.center.getX(), this.center.getY(), reverseX, reverseY);
        Point reversePoint;
        double rightDifference = this.paddle.getCollisionRectangle().getUpperRightPoint().getX() - this.center.getX();
        double leftDifference = this.center.getX() - this.paddle.getCollisionRectangle().getUpperLeft().getX();
        double yDifference = this.center.getY() - this.paddle.getCollisionRectangle().getUpperLeft().getY();
        double paddleHeight = this.paddle.getCollisionRectangle().getUpperLeft().getY();
        double angleInRadians = Math.toRadians(90 - this.velocity.getAngle());
        double addRadiusToX = Math.abs(Math.cos(angleInRadians) * radius);
        double addRadiusToY = Math.abs(Math.sin(angleInRadians) * radius);
        boolean intersectWithUpperLine
                = newTrajectory.isIntersecting(this.paddle.getCollisionRectangle().getUpperLine());
        if (rightDifference >= leftDifference && intersectWithUpperLine) {
            reversePoint = newTrajectory.intersectionWith(this.paddle.getCollisionRectangle().getUpperLine());
            this.center.setX(reversePoint.getX() - addRadiusToX - 2);
            //this.center.setY(reversePoint.getY() - addRadiusToY - 2);
        } else if (leftDifference > rightDifference && intersectWithUpperLine) {
            reversePoint = newTrajectory.intersectionWith(this.paddle.getCollisionRectangle().getUpperLine());
            this.center.setX(reversePoint.getX() + addRadiusToX + 2);
            //this.center.setY(reversePoint.getY() - addRadiusToY - 2);

        } else if (rightDifference >= leftDifference) {
            reversePoint = newTrajectory.intersectionWith(this.paddle.getCollisionRectangle().getLeftLine());
            this.center.setX(reversePoint.getX() - addRadiusToX - 2 - radius);
            this.velocity.setDy(Math.abs(this.velocity.getDy()));
            return;
        } else {
            reversePoint = newTrajectory.intersectionWith(this.paddle.getCollisionRectangle().getRightLine());
            this.center.setX(reversePoint.getX() + addRadiusToX + 2 + radius);
            this.velocity.setDy(Math.abs(this.velocity.getDy()));
            return;
        }
        this.center.setY(reversePoint.getY() - addRadiusToY - 2);

    }
    /**
     * .
     * this method checks if the ball is out of limits
     * if so- changes its angle by 90 degrees
     */
    public void moveOneStep() {
        //trajectory from now ball's coordinates to the next move coordinates considering its radius
        Line trajectory = this.trajectory;
        if (inPaddleLimits()) {
            setOutsideOfPaddle();

        }
        //no intersection happens
        if (this.gameEnvironment.getClosestCollision(trajectory) == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
            this.setTrajectory();
        } else {
            Point collisionPoint = this.gameEnvironment.getClosestCollision(trajectory).collisionPoint();
            Collidable collisionBlock = this.gameEnvironment.getClosestCollision(trajectory).collisionObject();
            double differenceDistance = collisionPoint.distance(trajectory.end());
            int xDifference = (int) (Math.abs(Math.cos(this.velocity.getAngle())) * (radius + differenceDistance));
            int yDifference = (int) (Math.abs(Math.sin(this.velocity.getAngle())) * (radius + differenceDistance));
            //this.center = this.getVelocity().applyToPoint(this.center);
            if (this.velocity.getDx() < 0 && this.velocity.getDy() < 0) {
                //xDifference = (int) (collisionPoint.getX() - trajectory.end().getX());
                this.center.setX(this.getX() + xDifference + 0.5);
                this.center.setY(collisionPoint.getY() + yDifference + 0.5);
                // this.setTrajectory();
            } else if (this.velocity.getDx() < 0 && this.velocity.getDy() > 0) {
                this.center.setX(this.getX() + xDifference + 0.5);
                this.center.setY(collisionPoint.getY() - yDifference - 0.5);
                // this.setTrajectory();
            } else if (this.velocity.getDx() > 0 && this.velocity.getDy() > 0) {
                this.center.setX(this.getX() - xDifference - 0.5);
                this.center.setY(collisionPoint.getY() - yDifference - 0.5);
                //  this.setTrajectory();
            } else if (this.velocity.getDx() > 0 && this.velocity.getDy() < 0) {
                this.center.setX(this.getX() - xDifference - 0.5);
                this.center.setY(collisionPoint.getY() + yDifference + 0.5);
                // this.setTrajectory();
            } else if (this.velocity.getDx() == 0 && this.velocity.getDy() < 0) {
                this.center.setY(collisionPoint.getY() + yDifference + 0.5);
                //this.setTrajectory();
            } else if (this.velocity.getDy() == 0 && this.velocity.getDx() < 0) {
                this.center.setX(this.getX() + xDifference + 0.5);
                //  this.setTrajectory();
            } else if (this.velocity.getDx() == 0 && this.velocity.getDy() > 0) {
                this.center.setY(collisionPoint.getY() - yDifference - 0.5);
                //   this.setTrajectory();
            } else if (this.velocity.getDy() == 0 && this.velocity.getDx() > 0) {
                this.center.setX(this.getX() - xDifference - 0.5);
                //  this.setTrajectory();
            }
            this.velocity = collisionBlock.hit(this, collisionPoint, this.velocity);
            this.setTrajectory();
        }

    }

    /**
     * .
     * checks if the ball radius + dx is  out of limit
     *
     * @param coordinate x or y coordinate
     * @param sizeOfBall radius of ball
     */
    private void checkLocationX(int coordinate, int sizeOfBall) {
        if (coordinate + this.velocity.getDx() + sizeOfBall > (int) this.limit.getRightBottom().getX()) {
            this.setX((int) this.limit.getRightBottom().getX() - sizeOfBall - this.velocity.getDx());
            //this.velocity.setDx(this.limit.getRightBottom().getX() - sizeOfBall - coordinate);
        } else if (coordinate + this.velocity.getDx() - sizeOfBall < (int) this.limit.getLeftTop().getX()) {
            this.setX((int) this.limit.getLeftTop().getX() + sizeOfBall - this.velocity.getDx());
            //this.velocity.setDx((this.limit.getLeftTop().getX() + sizeOfBall - coordinate));
        }
    }

    /**
     * .
     * checks if the ball radius + dy is  out of limit
     *
     * @param coordinate x or y coordinate
     * @param sizeOfBall radius of ball
     */
    private void checkLocationY(int coordinate, int sizeOfBall) {
        if (coordinate + this.velocity.getDy() + sizeOfBall > (int) this.limit.getRightBottom().getY()) {
            this.setY(this.limit.getRightBottom().getY() - sizeOfBall - this.velocity.getDy());
            //this.velocity.setDy(this.limit.getRightBottom().getY() - sizeOfBall - coordinate);
        } else if (coordinate + this.velocity.getDy() - sizeOfBall < (int) this.limit.getLeftTop().getY()) {
            this.setY((int) this.limit.getLeftTop().getY() + sizeOfBall - this.velocity.getDy());
            // this.velocity.setDy((this.limit.getLeftTop().getY() + sizeOfBall - coordinate));
        }
    }


    /**
     * .
     * checks if balls is in its limits
     *
     * @return true or false
     */
    public boolean isBallInLimit() {
        if (this.center.getX() + this.radius > this.limit.getRightBottom().getX()
                || this.center.getX() < this.radius + this.limit.getLeftTop().getX()) {
            return false;
        } else if
        (this.center.getY() + this.radius > this.limit.getRightBottom().getY()
                        || this.center.getY() < this.radius + this.limit.getLeftTop().getY()) {
            return false;
        }
        return true;
    }

    /**
     * .
     * puts ball in default x and y coordinates
     */
    public void defaultBallInLimit() {
        this.setX((double) this.limit.getWidth() / 2);
        this.setY((double) this.limit.getHeight() / 2);
    }

    /**.
     * this method add the ball to the game, so it will be recognized
     * @param g
     */
    @Override
    public void addToGame(GameLevel g) {
        if (g != null) {
            g.addSprite(this);
        }
    }

    /**.
     *this method let the ball know to "play" its move in the game
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }
    /**.
     *removes ball from game by rempoving it from the sprite collection.
     * @param gameLevel game
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);

    }
}