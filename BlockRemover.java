//Gili Wolf 315144907

/**.
 * this class is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**.
     * constructor initialise this game and counter
     * @param gameLevel game
     * @param removedBlocks remaining blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }
    /**
     * removes blocks which being hit, and updating the counter.
     * @param beingHit block
     * @param hitter ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
        beingHit.removeHitListener(this);
        remainingBlocks.decrease(1);
    }
}