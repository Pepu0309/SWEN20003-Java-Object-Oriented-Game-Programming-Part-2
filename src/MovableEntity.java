import java.util.ArrayList;

public abstract class MovableEntity<T extends GameEntity> extends GameEntity{

    private Point direction = new Point(0,0);

    public MovableEntity(double x, double y, String imageFileSource) {
        super(x, y, imageFileSource);
    }

    // Method called to move the movable entity towards a certain point, called once every step
    public void moveStep(double step_size){
        this.getPoint().setX(this.getPoint().getX() + step_size * direction.getX());
        this.getPoint().setY(this.getPoint().getY() + step_size * direction.getY());
    }

    // Determines whether a movable entity meets another entity using the condition defined in project 1 specification
    public boolean meet(Point point2, double meet_condition){
        if(this.getPoint().distanceTo(point2) < meet_condition){
            return true;
        }
        return false;
    }

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

    public Point getDirection() {
        return direction;
    }

    public void setDirection(Point direction) {
        this.direction = direction;
    }
}
