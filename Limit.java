//Gili Wolf 315144907
/**.
 * this class sets limit represented by the left top point and right bottom point
 */
public class Limit {
    private Point leftTop;
    private Point rightBottom;
    private int width;
    private int height;
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    /**.
     * constructor
     * @param x1  - x of leftTop point
     * @param y1 - y of leftTop point
     * @param x2 - x of rightBottom point
     * @param y2 - y of rightBottom point
     */
    public Limit(int x1, int y1, int x2, int y2) {
        //default
        if (x2 < x1) {
            int temp = x1;
            x1 = x2;
            x2 = temp;
        }
        if (y2 < y1) {
            int temp = y1;
            y1 = y2;
            y2 = temp;
        }
        if (x1 < 0) {
            x1 = 0;
        }
        if (x2 < 0) {
            x2 = 100;
        }
        if (y1 < 0) {
            y1 = 0;
        }
        if (y2 < 0) {
            y2 = 100;
        }
        if (x1 == x2) {
            x2 = x1 + 100;
        }
        if (y1 == y2) {
            y2 = y1 + 100;
        }
        this.leftTop = new Point(x1, y1);
        this.rightBottom = new Point(x2, y2);
        this.width = x2 - x1;
        this.height = y2 - y1;
    }
    /**.
     * getter
     * @return height height
     */
    public int getHeight() {
        return height;
    }

    /**.
     * getter
     * @return width width
     */
    public int getWidth() {
        return width;
    }
    /**.
     * getter
     * @return point left top point
     */
    public Point getLeftTop() {
        return leftTop;
    }

    /**.
     * getter
     * @return point getRightBottom right bottom point
     */
    public Point getRightBottom() {
        return rightBottom;
    }

    /**.
     * setter
     * @param leftTop left top point
     */
    public void setLeftTop(Point leftTop) {
        this.leftTop = leftTop;
    }

    /**.
     * setter
     * @param rightBottom right botoom point
     */
    public void setRightBottom(Point rightBottom) {
        this.rightBottom = rightBottom;
    }
}
