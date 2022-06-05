//Gili Wolf 315144907

import java.util.ArrayList;
import java.util.List;

/**.
 * this class represent an 2D rectangle by uts upper left point, width and height.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**.
     * constructor
     *
     * @param upperLeft
     * @param width
     * @param height
     */
    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        // CHECKS
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**.
     * Default-ive constructor
     */
    public Rectangle() {

    }

    /**
     * this method  Returns a (possibly empty) List of intersection points
     * with the specified line.
     *
     * @param line line
     * @return list of possible intersection Points with the line
     */

    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> listOfIntersectionPoints = new ArrayList<Point>();
        Line[] rectangleLines = generateLinesOfRectangle();
        for (Line line1 : rectangleLines) {
            if (line.isIntersecting(line1) && line.intersectionWith(line1) != null) {
                listOfIntersectionPoints.add(line.intersectionWith(line1));
            }
        }
        if (listOfIntersectionPoints.isEmpty()) {
            return null;
        }
        return listOfIntersectionPoints;
    }

    /**.
     * this method collects all the lines of the rectangle and return an array with all the lines
     * @return lines of the rectangle
     */
    public Line[] generateLinesOfRectangle() {
        Line[] lines = new Line[4];
        lines[0] = this.getUpperLine();
        lines[1] = this.getLeftLine();
        lines[2] = this.getBottomLine();
        lines[3] = this.getRightLine();
        return lines;
    }

    /**
     * .
     * getter
     *
     * @return width of rectangle
     */
    public double getWidth() {
        return width;
    }

    /**
     * .
     * getter
     *
     * @return height of rectangle
     */
    public double getHeight() {
        return height;
    }

    /**
     * .
     * getter
     *
     * @return upperLeft point of rectangle
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**.
     * setter
     *
     * @param width of this rectangle
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**.
     * setter
     *
     * @param height of this rectangle
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**.
     * setter
     *
     * @param upperLeft of this rectangle
     */
    public void setUpperLeft(Point upperLeft) {
        this.upperLeft = upperLeft;
    }


    /**.
     *this method returns the upper right point of the rectangle
     * @return point
     */
    public Point getUpperRightPoint() {
        return new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
    }

    /**.
     *this method returns the botoom left point of the rectangle
     * @return point
     */
    public Point getBottomLeftPoint() {
        return new Point(this.upperLeft.getX(), this.upperLeft.getY() + height);
    }

    /**.
     *this method returns the bottom right point of the rectangle
     * @return point
     */
    public Point getBottomRightPoint() {
        return new Point(this.upperLeft.getX() + width, this.upperLeft.getY() + height);
    }

    /**.
     * getter
     *
     * @return line - upper line of the rectangle
     */
    public Line getUpperLine() {
        return new Line(this.upperLeft, this.getUpperRightPoint());
    }

    /**.
     * getter
     * @return line - left line of the rectangle
     */
    public Line getLeftLine() {
        return new Line(this.upperLeft, this.getBottomLeftPoint());
    }

    /**.
     * getter
     * @return line - bottom line of the rectangle
     */
    public Line getBottomLine() {
        return new Line(this.getBottomLeftPoint(), this.getBottomRightPoint());
    }

    /**.
     * getter
     * @return line - right line of the rectangle
     */
    public Line getRightLine() {
        return new Line(this.getUpperRightPoint(), this.getBottomRightPoint());
    }
}
