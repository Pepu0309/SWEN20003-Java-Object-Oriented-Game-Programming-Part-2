import bagel.*;
public class Player{
    // Player class which contains all attributes and behaviour associated with the player

    private final Image entityImage = new Image("res/images/player.png");

    // Constant: energy amount that the player needs to have to go towards a zombie from algorithm 1
    private static final int PLAYER_ENERGY_GOES_TOWARDS_ZOMBIE = 3;
    // Constant: step size as defined in project specification
    private static final double STEP_SIZE = 10;
    // Constant: the condition used to determine when 2 points meet
    private static final double MEET_CONDITION = 50;

    // Point that provides information of the player's coordinates
    private Point point;
    private Point direction;
    private int energyLevel;

    public Player(double x, double y, int energyLevel){
        this.point = new Point(x, y);
        this.energyLevel = energyLevel;
    }


    // Method called to move the player towards a certain point, called once every step
    public void moveStep(){
        this.getPoint().setX(this.getPoint().getX() + STEP_SIZE * direction.getX());
        this.getPoint().setY(this.getPoint().getY() + STEP_SIZE * direction.getY());
    }

    public void setPlayerDirectionTo(Point point2){
        direction = calculateDirection(point2);
    }

    // Calculates normalised direction using formula in Week 5 Workshop and Project Specification
    private Point calculateDirection(Point point2){
        double directionX = (point2.getX() - this.getPoint().getX())/(this.getPoint().distanceTo(point2));
        double directionY = (point2.getY() - this.getPoint().getY())/(this.getPoint().distanceTo(point2));
        Point direction = new Point(directionX, directionY);
        return direction;
    }

    // Determines whether the player meets another entity using the condition defined in project 1 specification
    public boolean meet(Point point2){
        if(this.point.distanceTo(point2) < MEET_CONDITION){
            return true;
        }
        return false;
    }

    // Draws entity on screen based on their coordinates
    public void drawEntity(){
        entityImage.draw(point.getX(), point.getY());
    }

    public int getEnergyLevel() {
        return energyLevel;
    }

    public void setEnergyLevel(int energyLevel) {
        this.energyLevel = energyLevel;
    }

    public Point getPoint() {
        return point;
    }

    public static int getPlayerEnergyGoesTowardsZombie() {
        return PLAYER_ENERGY_GOES_TOWARDS_ZOMBIE;
    }
}
