import bagel.Image;

/**
 * GameEntity class which all other entity subclasses inherit from. Defines all common attributes and methods of the
 * other entities.
 */
public abstract class GameEntity {

    // Point that provides information of the player's coordinates
    private Point point;
    private Image entityImage;

    /**
     * Constructor for the GameEntity class which takes x and y coordinates of the position in which the GameEntity is
     * supposed to be created at and an imageFileSource to represent the Image attribute of the GameEntity object.
     * To be called by subclasses of GameEntity.
     *
     * @param x double value indicating x-coordinate of the position of the GameEntity object
     * @param y double value indicating y-coordinate of the position of the GameEntity object
     * @param imageFileSource the file source of the Image of the GameEntity object
     */
    public GameEntity(double x, double y, String imageFileSource) {
        this.point = new Point(x, y);
        this.entityImage = new Image(imageFileSource);
    }

    /**
     * Draws entity on screen based on their coordinates
     */
    public void drawEntity(){
        entityImage.draw(point.getX(), point.getY());
    }

    /**
     * Getter method for the Point object of the GameEntity class.
     *
     * @return the Point object of the GameEntity class
     */
    public Point getPoint() {
        return point;
    }


}
