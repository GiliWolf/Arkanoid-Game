//Gili Wolf 315144907
import biuoop.DrawSurface;

/**
 * this class represent a pause screen to be chown when the user hits "p" on the keyboards.
 */
public class PauseScreen  implements Animation {

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }
    @Override
    public boolean shouldStop() {
        return false;
    }


}