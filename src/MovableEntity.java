public abstract class MovableEntity extends GameEntity{

    private final double STEP_SIZE;
    private final double MEET_CONDITION;

    private Point direction;

    public MovableEntity(double x, double y, String imageFileSource, double STEP_SIZE, double MEET_CONDITION) {
        super(x, y, imageFileSource);
        this.STEP_SIZE = STEP_SIZE;
        this.MEET_CONDITION = MEET_CONDITION;
    }

    // Method called to move the movable entity towards a certain point, called once every step
    public void moveStep(){
        this.getPoint().setX(this.getPoint().getX() + STEP_SIZE * direction.getX());
        this.getPoint().setY(this.getPoint().getY() + STEP_SIZE * direction.getY());
    }

    // Determines whether the player meets another entity using the condition defined in project 1 specification
    public boolean meet(Point point2){
        if(this.getPoint().distanceTo(point2) < MEET_CONDITION){
            return true;
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
