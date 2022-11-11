//Gili Wolf 315144907
import java.util.ArrayList;
import java.util.List;

/**.
 * this class represents the game environment by a list of collidable objects
 */
public class GameEnvironment {
    private List<Collidable> listOfCollidableObjects = new ArrayList<Collidable>();

    /**
     * this method adds the given collidable to the Gams's environment.
     *
     * @param c collidable object
     */
    // add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        this.listOfCollidableObjects.add(c);
    }

    /**.
     ** this method removes the given collidable to the Gams's environment.
     * @param c collidable
     */
    public void removeCollidable(Collidable c) {
        this.listOfCollidableObjects.remove(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     * @param trajectory of the object
     * @return collision info of the collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // if the line is null or there are no collidable Objects in the Environment
        if (trajectory == null || listOfCollidableObjects.isEmpty()) {
            return null;
        }
        //initialize first potential to start point of line
        Point closestToStart = trajectory.end();
        //initialize the closest collision object
        Collidable closestCollisionObject = null;
        //flag
        boolean collisionHappened = false;
        for (Collidable collidableObject : listOfCollidableObjects) {
            Point potentialPoint
                    = trajectory.closestIntersectionToStartOfLine(collidableObject.getCollisionRectangle());
            if (potentialPoint != null) {
                if (potentialPoint.distance(trajectory.start()) <= closestToStart.distance(trajectory.start())) {
                    closestToStart = potentialPoint;
                    closestCollisionObject = collidableObject;
                    collisionHappened = true;
                }
            }
        }
        if (collisionHappened) {
            return new CollisionInfo(closestToStart, closestCollisionObject);
        }
        return null;
    }

}
