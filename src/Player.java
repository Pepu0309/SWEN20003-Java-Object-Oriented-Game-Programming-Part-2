import bagel.*;
import java.util.*;

public class Player<T extends GameEntity> extends MovableEntity{
    /*
     * Player class which contains all attributes and behaviour associated with the player; inherits from the
     * MovableEntity class (which inherits from the GameEntity class)
     */

    // Constant: energy amount that the player needs to have to go towards a zombie from algorithm 1
    private static final int ENERGY_REQUIRED_MOVE_TOWARDS_ZOMBIE = 3;
    // Constant: player's step size as defined in project specification
    private static final double PLAYER_STEP_SIZE = 10;
    // Constant: the condition used to determine when the player meets another entity
    private static final double PLAYER_MEET_CONDITION = 50;
    // Constant: the maximum distance of which the player can shoot a bullet
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

    // Player shoots a bullet to the closest zombie and sets the bullet's attribute so that it is drawn
    public void shootBulletClosestZombie(Point shootDirection){
        bullet.setDirection(shootDirection);
        bullet.setToDraw(true);
    }

    /*
     * Method that finds the closest Zombie/Sandwich to the Player depending on the ArrayList parameter passed to it.
     * Returns a null reference if the ArrayList argument given doesn't contain anything; otherwise returns the point
     * of the closest Zombie/Sandwich to the Player.
     */
    public Point findClosest(ArrayList<T> entityArray){
        double minDistance = 0;
        double curDistance;
        Point closestEntityPoint = null;
        if (entityArray.size() >= 1) {
            for (T entity : entityArray) {
                curDistance = this.getPoint().distanceTo(entity.getPoint());
                if (curDistance > minDistance) {
                    minDistance = curDistance;
                    closestEntityPoint = entity.getPoint();
                }
            }
        }
        return closestEntityPoint;
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
