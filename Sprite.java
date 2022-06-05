//Gili Wolf 315144907
import biuoop.DrawSurface;

/**.
 * this interface represents a Sprite of the game
 */
public interface Sprite {
    /**.
     * draw the sprite to the screen
     * @param d draw surface
     */
    void drawOn(DrawSurface d);
    /**.
     * notify the sprite that time has passed
     */
    void timePassed();

    /**.
     * adds the aprite to the game
     * @param g game
     */
    void addToGame(GameLevel g);
}