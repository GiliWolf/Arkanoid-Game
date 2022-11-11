//Gili Wolf 315144907

import biuoop.DrawSurface;

/**.
 * this interface is for animations, binding them to have methods which determinants what one frame logic should do
 * and when the animation should stop.
 */
public interface Animation {
    /**
     * this method describes what an amition should look like in one frame.
     * @param d draw-surface
     */
    void doOneFrame(DrawSurface d);

    /**
     * returns true when the animation should stop.
     * @return boolean true whan should stop
     */
    boolean shouldStop();
}