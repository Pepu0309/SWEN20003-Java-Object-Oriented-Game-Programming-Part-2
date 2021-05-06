import bagel.Image;

public abstract class GameEntity {
    /*
     * Superclass of GameEntity which all subclasses inherit from; defines common attributes and methods of all the
     * other entities
     */

    // Point that provides information of the player's coordinates
    private Point point;
    private Image entityImage;

    public GameEntity(double x, double y, String imageFileSource) {
        this.point = new Point(x, y);
        this.entityImage = new Image(imageFileSource);
    }

    // Draws entity on screen based on their coordinates
    public void drawEntity(){
        entityImage.draw(point.getX(), point.getY());
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Image getEntityImage() {
        return entityImage;
    }

}
