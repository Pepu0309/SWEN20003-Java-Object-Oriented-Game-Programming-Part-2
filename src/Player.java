import bagel.*;
public class Player extends MovableEntity{
    // Player class which contains all attributes and behaviour associated with the player

    // Constant: energy amount that the player needs to have to go towards a zombie from algorithm 1
    private static final int ENERGY_REQUIRED_MOVE_TOWARDS_ZOMBIE = 3;
    // Constant: step size as defined in project specification
    private static final double PLAYER_STEP_SIZE = 10;
    // Constant: the condition used to determine when 2 points meet
    private static final double PLAYER_MEET_CONDITION = 50;
    // Constant: the condition used to determine when 2 points meet
    private static final double PLAYER_SHOOTING_RANGE = 150;

    private int energyLevel;
    private Bullet bullet;

    public Player(double x, double y, int energyLevel){
        super(x, y, "res/images/player.png", PLAYER_STEP_SIZE, PLAYER_MEET_CONDITION);
        this.energyLevel = energyLevel;
    }

    // Calculates normalised direction using formula in Week 5 Workshop and Project Specification
    private void calculateDirection(Point point2){
        double directionX = (point2.getX() - this.getPoint().getX())/(this.getPoint().distanceTo(point2));
        double directionY = (point2.getY() - this.getPoint().getY())/(this.getPoint().distanceTo(point2));
        this.setDirection(new Point(directionX, directionY));
    }

    public int getEnergyLevel() {
        return energyLevel;
    }

    public void setEnergyLevel(int energyLevel) {
        this.energyLevel = energyLevel;
    }

    public static int getPlayerEnergyGoesTowardsZombie() {
        return ENERGY_REQUIRED_MOVE_TOWARDS_ZOMBIE;
    }
}
