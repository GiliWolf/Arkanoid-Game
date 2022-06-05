//Gili Wolf 315144907
/**.
 * a BallRemover is in charge of removing balls from the game, as well as keeping count
 * of the number of balls that remain.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**.
     * constructor intilize this game and remaining balls.
     * @param gameLevel game
     * @param removedBalls balls
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBalls;
    }
    /**.
     * balls that are hit should be removed
     * from the game
     * @param beingHit
     * @param hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        remainingBalls.decrease(1);
    }
}
