//Gili Wolf 315144907
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**.
 * this class holds a collection of the Game's sprites
 * represented by a list of sprites
 */
public class SpriteCollection {
    private List<Sprite> listOfSprites = new ArrayList<Sprite>();

    /**.
     ** adds spirte to the collection
     * @param s
     */
    public void addSprite(Sprite s) {
        if (s != null) {
            listOfSprites.add(s);
        }
    }

    /**.
     * remove sprite to the collection
     * @param s sprite
     */
    public void removeSprite(Sprite s) {
        if (s != null) {
            listOfSprites.remove(s);
        }
    }

    /**.
     * call timePassed() on all sprites
     */
    public void notifyAllTimePassed() {
        // Make a copy of the list of sprites before iterating over them.
        List<Sprite> newListOfSprites = new ArrayList<Sprite>(this.listOfSprites);
        for (Sprite s : newListOfSprites) {
            s.timePassed();
        }
    }

    /**.
     * call drawOn(d) on all sprites
     *
     * @param d draw surface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : listOfSprites) {
            s.drawOn(d);
        }
    }
}