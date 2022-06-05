//Gili Wolf 315144907

/**
 * .
 * this class is velocity represented by dx - x difference and dy - y difference
 */
public class Velocity {
    // Velocity specifies the change in position on the `x` and the `y` axes.
    private double dx;
    private double dy;
    private double angle;
   // final private double speed;

    /**
     * .
     * constructor
     *
     * @param dx - x difference
     * @param dy - y difference
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
        this.angle = Math.tan(dx / dy);
        //this.speed = new Point(0, 0).distance(new Point(dx, dy));
    }

    /**
     * .
     * convert angle and speed to dx and dy by calculating sin and cos of the angle
     *
     * @param angle angle
     * @param speed speed - the difference between to points
     * @return new velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double angleInRadians = Math.toRadians(angle);
        //calculates dx from the equation cos(a) = dx/speed
        double dx = Math.cos(angleInRadians) * speed;
        //calculates dy from the equation sin(a) = dy/speed * (-1) for the right direction
        double dy = Math.sin(angleInRadians) * speed * (-1);
        //setSpeed(speed);
        return new Velocity(dx, dy);
    }
/*
    public Velocity fromAngleOnly(double angle) {
        double angleInRadians = Math.toRadians(angle);
        //calculates dx from the equation cos(a) = dx/speed
        double dx = Math.cos(angleInRadians) * this.speed;
        //calculates dy from the equation sin(a) = dy/speed * (-1) for the right direction
        double dy = Math.sin(angleInRadians) * this.speed * (-1);
        return new Velocity(dx, dy);
    } */

    /**
     * this method retirns the angle of the velocity.
     * @return angle
     */
    public double getAngle() {
        return Math.tan(this.dx / this.dy);

    }



   /* public void setSpeed(double speed) {
        this.speed = speed;
    }*/

   /* public double getSpeed() {
        return this.speed;

    }
    */


    /**
     * .
     * Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy)
     *
     * @param p point
     * @return new point after applying velocity
     */
    public Point applyToPoint(Point p) {
        Point finalPoint = new Point(p.getX() + this.dx, p.getY() + this.dy);
        return finalPoint;
    }

    /**
     * .
     * getter
     *
     * @return dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * .
     * getter
     *
     * @return dy
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * .
     * setter
     *
     * @param dx
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * .
     * setter
     *
     * @param dy
     */
    public void setDy(double dy) {
        this.dy = dy;
    }
}
