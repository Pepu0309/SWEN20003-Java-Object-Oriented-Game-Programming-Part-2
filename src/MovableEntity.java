import java.util.ArrayList;

/**
 * MovableEntity class which inherits from the GameEntity class to define a subset of entities that are movable in
 * the game along with the attributes and behaviours they share.
 * @param <T> a generic type parameter representing any class which inherits the GameEntity class
 */
public abstract class MovableEntity<T extends GameEntity> extends GameEntity{

    // Direction is initialised at (0,0), Algorithm 1 will decide the direction and behaviour of movable entities.
    private Point direction = new Point(0,0);

    /**
     * Constructor for the MovableEntity class which is identical to the GameEntity class. Takes x and y coordinates
     * of the position in which the MovableEntity is supposed to be created at and an imageFileSource to find
     * the Image attribute of the MovableEntity object. To be called by subclasses of MovableEntity.

     * @param x double value indicating x-coordinate of the position of the MovableEntity object
     * @param y double value indicating x-coordinate of the position of the MovableEntity object
     * @param imageFileSource the file source of the Image of the MovableEntity object
     */
    public MovableEntity(double x, double y, String imageFileSource) {
        super(x, y, imageFileSource);
    }


    /**
     * Method called to move the movable entity towards a certain point based on the step_size parameter,
     * called once every tick (to be used in conjunction with the updateTick method).
     *
     * @param step_size a double value of the step size to be used in moving the entity
     */
    public void moveStep(double step_size){
        this.getPoint().setX(this.getPoint().getX() + step_size * direction.getX());
        this.getPoint().setY(this.getPoint().getY() + step_size * direction.getY());
    }

    /**
     * Determines whether a movable entity meets another entity by comparing their points and using the meet_condition
     * parameter given.
     *
     * @param point2 the point of the other entity that is to be compared with
     * @param meet_condition a double value indicating the condition required for the movable entity and the other
     *                       entity to "meet"
     * @return boolean value true if the movable entity meets the other entity, false otherwise
     */
    public boolean meet(Point point2, double meet_condition){
        if(this.getPoint().distanceTo(point2) < meet_condition){
            return true;
        }
        return false;
    }

    /**
     * Determines if a movable entity interacts with one entity of a group (either a group of Zombie or Sandwich).
     * If the movable entity does interact with one entity of the group, that entity is removed and the ArrayList is
     * trimmed down to size.
     *
     * @param entityGroup an ArrayList of type Zombie or Sandwich
     * @param meet_condition a double value indicating the condition for the movable entity to meet another entity
     * @return boolean value true if the entity does interact with an entity of the group, false otherwise.
     */
    public boolean entityInteractsWithOneOfGroup(ArrayList<T> entityGroup, double meet_condition){
        for(T entity: entityGroup) {
            if (meet(entity.getPoint(), meet_condition)) {
                entityGroup.remove(entity);
                entityGroup.trimToSize();
                return true;
            }
        }
        return false;
    }

    /**
     * Getter method for the direction attribute of the MovableEntity class
     *
     * @return a Point object indicating the direction of the MovableEntity object.
     */
    public Point getDirection() {
        return direction;
    }

    /**
     * Setter method for the direction attribute of the MovableEntity class
     *
     * @param direction a Point object to change the direction of the MovableEntity to
     */
    public void setDirection(Point direction) {
        this.direction = direction;
    }
}
