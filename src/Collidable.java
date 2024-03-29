//Gili Wolf 315144907
import biuoop.DrawSurface;

/**.
 * this interface represents a collidable object.
 */
public interface Collidable {
    /**.
     * Return the "collision shape" of the object
     * @return rectangle of the collidable
     */
    Rectangle getCollisionRectangle();

    /**.
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     * @param collisionPoint of the object
     * @param currentVelocity of the object
     * @param hitter ball of collision
     * @return velocity expected after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**.
     * draws itself on a given draw surface
     * @param d draw surface
     */
    void drawOn(DrawSurface d);
}