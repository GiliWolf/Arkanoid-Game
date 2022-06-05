//Gili Wolf 315144907

/**.
 * this class keeps score according to the blocks being hit.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter scoreCounter;
    private GameLevel gameLevel;

    /**
     * constructor initialise the score of the game.
     * @param gameLevel game
     * @param score score
     */
    public ScoreTrackingListener(GameLevel gameLevel, Counter score) {
        this.gameLevel = gameLevel;
        this.scoreCounter = score;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.scoreCounter.increase(15);
    }
}
