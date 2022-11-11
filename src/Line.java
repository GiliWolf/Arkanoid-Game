//Gili Wolf 315144907

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**.
 *this class represents line by two points
 */

public class Line {
    private Point start; //initialize
    private Point end; //initialize
    /**.
     *constructor
     * @param start
     * @param end
     */
    public Line(Point start, Point end) {
        if (start == null) {
            start = new Point(0, 0);
        }
        if (end == null) {
            end = new Point(10, 10);
        }
        this.start = start;
        this.end = end;
        // CHECK IF NEEDS EXCEPTIONS
    }

    /**.
     * constructor
     * @param x1 - x value of point start
     * @param y1 - y value of point start
     * @param x2 - x value of point end
     * @param y2 - y value of point end
     **/
    public Line(double x1, double y1, double x2, double y2) {
        if (x1 < 0) {
            x1 = 0;
        }
        if (x2 < 0) {
            x2 = 10;
        }
        if (y1 < 0) {
            y1 = 0;
        }
        if (y2 < 0) {
            y2 = 10;
        }
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**.
     * return length of the line
     * @return length length
     */
    public double length() {
        return start.distance(end);
    }

    /**.
     *calculats value of middle point of the line
     * @return middlePoint middle point
     */
    public Point middle() {
        double middleX = ((start.getX() + end.getX()) / 2);
        double middleY = ((start.getY() + end.getY()) / 2);
        Point middlePoint = new Point(middleX, middleY);
        return middlePoint;
    }

    /**.
     * getter
     * @return start point
     */
    public Point start() {
        return start;
    }
    /**.
     *getter
     * @return end point
     */
    public Point end() {
        return end;
    }
    /**.
     *this method calculates the slope considering all the extreme cases
     * @return slope
     */
    public Double calculateSlope() {
        Double slope;
        slope = null;

        // line is a point (distance =0)
        if ((this.start.equals(this.end))) {
            slope = Double.POSITIVE_INFINITY;
        } else if ((this.start.equals(this.end)) || (this.start.getY() - this.end.getY() == 0)) {
            slope = 0.0; //horizontal
        } else if ((this.start.getX() - this.end.getX()) == 0) {
            slope = null; //vertical
        } else {
            slope = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
            //by equation
        }
        return slope;
    }

    /**.
     * calculate y intercept of the line's equation
     * @return yIntercept y value of point
     */

    public double yIntercept() {
        return (this.start.getY() - (this.calculateSlope() * this.start.getX()));
    }

    /**.
     * return max X value of line
     * @return point max x value point
     */
    public Point maxXValue() {
        if (this.start.getX() >= this.end.getX()) {
            return this.start;
        } else {
            return this.end;
        }
    }
    /**.
     * return min X value of line
     * @return point min x value point
     */
    public Point minXValue() {
        if (this.start.getX() <= this.end.getX()) {
            return this.start;
        } else {
            return this.end;
        }
    }
    /**.
     * return max y value of line
     * @return point max y  value point
     */
    public Point maxYValue() {
        if (this.start.getY() >= this.end.getY()) {
            return this.start;
        } else {
            return this.end;
        }
    }
    /**.
     * return min y value of line
     * @return point min y value point
     */
    public Point minYValue() {
        if (this.start.getY() <= this.end.getY()) {
            return this.start;
        } else {
            return this.end;
        }
    }

    /**.
     * calculates and return values of the lines' equations intersection point
     * @param other line
     * @return point interxectionEquationPoint
     */
    private Point intersectionEquationPoint(Line other) {
        if (this.calculateSlope() == null && other.calculateSlope() == null) {
            if (this.maxYValue().equals(other.minYValue())) {
                return this.maxYValue();
            } else if (this.minYValue().equals(other.maxYValue())) {
                return this.minYValue();
            } else {
                return null;
            }
        }
        if (this.calculateSlope() == null) {
            return new Point(this.start.getX(), other.calculateSlope() * this.start.getX() + other.yIntercept());
        } else if (other.calculateSlope() == null) {
            return new Point(other.start().getX(), this.calculateSlope() * other.start().getX() + this.yIntercept());
        } else if (this.calculateSlope() == Double.POSITIVE_INFINITY && this.start.isOnLIne(other)) {
           return this.start;
       } else if (other.calculateSlope() == Double.POSITIVE_INFINITY && other.start().isOnLIne(this)) {
           return other.start;
       } else if (this.calculateSlope() == Double.POSITIVE_INFINITY
               && other.calculateSlope() == Double.POSITIVE_INFINITY && this.start.equals(other.start())) {
           return this.start;
       } else if (this.calculateSlope().equals(other.calculateSlope())) {
            if (this.maxXValue().equals(other.minXValue())) {
                return this.maxXValue();
            } else if (this.minXValue().equals(other.maxXValue())) {
                return this.minXValue();
            }
           return null;
       }

        //comparing the two line equation- and calculating the X intersection value
        //by the equation- x = (y2-y1) / (m1 -m2)
        double xValue = (other.yIntercept() - this.yIntercept()) / (this.calculateSlope() - other.calculateSlope());
        // calculating the Y intersection value by the
        // equation- y = m*x + b
        double yValue = (this.calculateSlope() * xValue) + this.yIntercept();
        return new Point(xValue, yValue);
    }

    /**.
     * return true if the lines are intersecting, false otherwise
     * @param other line
     * @return true or false
     */
    public boolean isIntersecting(Line other) {
        double xValueOfEquationIntersection = 0;
        if (this.intersectionEquationPoint(other) != null) {
            xValueOfEquationIntersection = this.intersectionEquationPoint(other).getX();
        }
        // SAME EQUATION LINES

        // parallel
        if (Objects.equals(this.calculateSlope(), other.calculateSlope())) {
            //horizontal
            // terms = same slope, horizontal,  same b values, lines overlap

            if (this.calculateSlope() == null) {
                // same y doesnt matter start or end X values
                if ((this.start.getX() == other.start().getX())
                        && (Math.min(this.maxYValue().getY(), other.maxYValue().getY())
                        >= Math.max(this.minYValue().getY(), other.minYValue().getY()))) {
                    return true;
                }
            } else if (this.calculateSlope() == 0 && (this.yIntercept() == other.yIntercept())
                    && (Math.min(this.maxXValue().getX(), other.maxXValue().getX())
                    >= Math.max(this.minXValue().getX(), other.minXValue().getX()))) {
                return true;
                // VERTICAL
                //terms =  vertical, same X values, lines overlap
            } else {
                // parallel lines - NOT VERTICAL- NOT horizontal
                // terms = same slope, not vertical, not horizontal,  same b values, lines overlap
            if ((this.yIntercept() == other.yIntercept())
                    && (Math.min(this.maxYValue().getY(), other.maxYValue().getY())
                    >= Math.max(this.minYValue().getY(), other.minYValue().getY()))) {
                return true;
            }
        }
        } else { // non-parallel non vertical
             double yValueOfEquationIntersection = this.intersectionEquationPoint(other).getY();
            if (this.calculateSlope() == null) {
                if (xValueOfEquationIntersection >= other.minXValue().getX()
                    && xValueOfEquationIntersection <= other.maxXValue().getX()
                && yValueOfEquationIntersection >= this.minYValue().getY()
                && yValueOfEquationIntersection <= this.maxYValue().getY()) {
                    return true;
                }
            } else if (other.calculateSlope() == null) {
                if (xValueOfEquationIntersection >= this.minXValue().getX()
                    && xValueOfEquationIntersection <= this.maxXValue().getX()
                    && yValueOfEquationIntersection >= other.minYValue().getY()
                    && yValueOfEquationIntersection <= other.maxYValue().getY()) {
                return true;
            }
            } else {
                return (xValueOfEquationIntersection >= this.minXValue().getX())
                        && xValueOfEquationIntersection <= this.maxXValue().getX()
                        && xValueOfEquationIntersection >= other.minXValue().getX()
                        && xValueOfEquationIntersection <= other.maxXValue().getX();
            }
        }
        //not in the right terms
        return false;

    }


    /**.
     * calculates and return values of the intersection point
     * @param other line
     * @return point oof intersection
     */
    public Point intersectionWith(Line other) {
        if ((this.isIntersecting(other)) || this.intersectionEquationPoint(other) != null) {
            return this.intersectionEquationPoint(other);
        } else {
            return null;
        }
    }

    /** the method returns null, if this line does not intersect with the rectangle.
     * Otherwise, returns the closest intersection point to the
     *  start of the line.
     * @param rect rectangle
     * @return point closest Intersection Point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> listOfIntersectionPoints = new ArrayList<Point>();
        listOfIntersectionPoints = rect.intersectionPoints(this);
        if (rect.intersectionPoints(this) != null) {
            double distanceFirstPoint = this.start.distance(listOfIntersectionPoints.get(0));
            double distanceSecPoint = 0;
            //checks sec point isn't null in case there is only one intersection point
            if (listOfIntersectionPoints.size() > 1) {
                distanceSecPoint = this.start.distance(listOfIntersectionPoints.get(1));
            }
            //checks which distance is bigger and returns the suitable point
           if (distanceFirstPoint >= distanceSecPoint) {
               return listOfIntersectionPoints.get(0);
           } else {
               return listOfIntersectionPoints.get(1);
           }
        }
        return null;
    }
}