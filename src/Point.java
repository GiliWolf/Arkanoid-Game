//Gili Wolf 315144907

/**
 * .
 * this class gets x and y values from the user
 * can calculate distance between 2 points
 * can check if other point is equal
 */
public class Point {
    private double x;
    private double y;

    /**
     * .
     * default-ive constructor
     */
    public Point() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * .
     * constructor- must get x and y values
     *
     * @param x Gets double x for the x value
     * @param y Gets double y for the y value
     */
    public Point(double x, double y) {
        if (x >= 0 && y >= 0) {
            this.x = x;
            this.y = y;
        } else if (x < 0) { //default
            this.x = 1;
        } else if (y < 0) { //default
            this.y = 1;
        }
    }

    /**
     * .
     * calculate distance
     *
     * @param other point
     * @return distance between two points
     */
    public double distance(Point other) {
        if (this.equals(other)) {  //if the points have the same values- the distance is zero
            return 0;
        }
        double distanceValue = 0;
        distanceValue = Math.sqrt(Math.pow((this.x - other.getX()), 2) + Math.pow((this.y - other.getY()), 2));
        return distanceValue;
    }

    /**
     * .
     * checks if 2 points are equal
     *
     * @param other point
     * @return true or false
     */
    public boolean equals(Point other) {
        if ((this.x == other.getX()) && (this.y == other.getY())) {
            return true;
        }
        return false;
    }

    /**
     * .
     * getter
     *
     * @return x
     */
    public double getX() {
        return this.x;
    }

    /**
     * .
     * getter
     *
     * @return y
     */
    public double getY() {
        return this.y;
    }

    /**
     * .
     * checks if a point is on a line
     *
     * @param line line
     * @return true or false
     */
    public boolean isOnLIne(Line line) {
        //vertical+
        if (line.calculateSlope() == null) {
            if (this.x == line.start().getX() && this.y <= line.maxYValue().getY()
                    && this.y >= line.minYValue().getY()) {
                return true;
            }
        } else if (line.calculateSlope() == Double.POSITIVE_INFINITY) { //line is a point
            if (this.equals(line.start())) {
                return true;
            }
        } else {
            // calculates mx + b
            double setRightSide = (line.calculateSlope() * this.x) + line.yIntercept();
            // checks if y equals to mx + b
            if (this.y == setRightSide && this.x <= line.maxXValue().getX() && this.x >= line.minXValue().getX()) {
                return true;
            }
        }
        return false;
    }

    /**
     * .
     * setter
     *
     * @param x x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * .
     * setter
     *
     * @param y y
     */
    public void setY(double y) {
        this.y = y;
    }

}
