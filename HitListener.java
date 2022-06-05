//Gili Wolf 315144907

/**.
 * this interface is for objects that want to be notified of hit event.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit
     * @param hitter
     */
    void hitEvent(Block beingHit, Ball hitter);
}