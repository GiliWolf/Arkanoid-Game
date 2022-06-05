//Gili Wolf 315144907

/**.
 * this class holds the collision point of an object
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**.
     * constructor
     * @param collisionPoint of the object
     * @param collisionObject object
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**.
     * the point at which the collision occurs.
     * @return point of collision
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**.
     * the collidable object involved in the collision.
     * @return collidable object
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
