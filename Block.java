//Gili Wolf 315144907
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**.
 * this class represent a block bt a rectangle.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private Color color;
    private List<HitListener> hitListeners = new ArrayList<HitListener>();


    /**.
     * constructor
     * @param upperLeft point of the rectangle
     * @param width of the rectangle
     * @param height of the rectangle
     */
    public Block(Point upperLeft, double width, double height) {
        this.rectangle = new Rectangle(upperLeft, width, height);
        this.color = Color.RED;
    }

    /**.
     * getter
     *
     * @return rectangle of this Block
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**.
     * setter
     * @param color of the block
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**.
     * this method returns the new velocity an object which collides with the block should have
     *
     * @param collisionPoint  of the object with the block
     * @param currentVelocity of the object which collides with the block
     * @param hitter Ball of collision
     * @return velocity velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint.equals(this.rectangle.getUpperLeft())
                || collisionPoint.equals(this.rectangle.getUpperRightPoint())
                || collisionPoint.equals(this.rectangle.getBottomLeftPoint())
                || collisionPoint.equals(this.rectangle.getBottomRightPoint())) {
            this.notifyHit(hitter);
            return new Velocity((-1) * currentVelocity.getDx(), (-1) * currentVelocity.getDy());
        } else if (collisionPoint.isOnLIne(this.rectangle.getUpperLine())
                || collisionPoint.isOnLIne(this.rectangle.getBottomLine())) {
            this.notifyHit(hitter);
            return new Velocity(currentVelocity.getDx(), (-1) * currentVelocity.getDy());
        } else { //collide with vertical line
            this.notifyHit(hitter);
            return new Velocity((-1) * currentVelocity.getDx(), currentVelocity.getDy());
        }
        //return null;
    }

    /**.
     * draws itself in the draw surface
     * @param d draw surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.getCollisionRectangle().getWidth(), (int) this.getCollisionRectangle().getHeight());
        d.setColor(Color.BLACK);
        if (this.color.equals(Color.BLACK)) {
            d.setColor(Color.white);
        }
        d.drawRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.getCollisionRectangle().getWidth(), (int) this.getCollisionRectangle().getHeight());
    }

    /**.
     * adds the block to be recognized in the game
     * @param g game
     */
    @Override
    public void addToGame(GameLevel g) {
        if (g != null) {
            g.addSprite(this);
            g.addCollidable(this);
        }
    }

    /**.
     * notify the block time passed
     */
    @Override
    public void timePassed() {

    }

    /**
     *
     * @param gameLevel
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);

    }
    //void???????????????
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**.
     * adds listener to the hit listener.
     * @param hl hit listener
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**.
     * removes listener from the hit listener.
     * @param hl hit listener
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);

    }
}
