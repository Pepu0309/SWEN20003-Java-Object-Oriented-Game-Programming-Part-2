/**
 * Treasure class which contains all attributes and behaviours associated with Treasure; inherits from the
 * GameEntity class
 */
public class Treasure extends GameEntity{

    // attribute which indicates that the treasure is reachable by the player or not
    private boolean isReachable = false;
    // attribute which indicates that the player has reached the treasure
    private boolean playerHasReached = false;

    /**
     * Constructor of the Treasure class which takes x and y coordinates of the position in which the Treasure object
     * is supposed to be created on. Calls the constructor of the GameEntity superclass with the image argument
     * res/images/treasure.png as that is to be the image of Treasure objects as defined by project specification.
     *
     * @param x double value indicating x-coordinate of the position of the Treasure object
     * @param y double value indicating y-coordinate of the position of the Treasure object
     */
    public Treasure(double x, double y ){
        super(x, y, "res/images/treasure.png");
    }

    /**
     * Getter method for the isReachable attribute of the Treasure class.
     *
     * @return the isReachable attribute of a Treasure object
     */
    public boolean isReachable() {
        return isReachable;
    }

    /**
     * Setter method for the isReachable attribute of the Treasure class.
     *
     * @param reachable a boolean value to set the isReachable attribute of the Treasure object to
     */
    public void setReachable(boolean reachable) {
        isReachable = reachable;
    }

    /**
     * Getter method for the playerHasReached attribute of the Treasure class.
     *
     * @return the playerHasReached attribute of a Treasure object
     */
    public boolean playerHasReached() {
        return playerHasReached;
    }

    /**
     * Setter method for the playerHasReached attribute of the Treasure class.
     *
     * @param playerHasReached a boolean value to set the playerHasReached attribute of the Treasure object to
     */
    public void setPlayerHasReached(boolean playerHasReached) {
        this.playerHasReached = playerHasReached;
    }
}
