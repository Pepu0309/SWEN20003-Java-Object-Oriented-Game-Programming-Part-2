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
    private Bullet bullet = new Bullet();

    public Player(double x, double y, int energyLevel){
        super(x, y, "res/images/player.png", PLAYER_STEP_SIZE, PLAYER_MEET_CONDITION);
        this.energyLevel = energyLevel;
    }

    // Calculates normalised direction using formula in Week 5 Workshop and Project Specification and returns it
    public Point calculateDirection(Point point2){

        // Declare a point object that represents the direction to the point passed in as the argument
        Point directionToPoint;

        // Calculations for the direction
        double directionX = (point2.getX() - this.getPoint().getX())/(this.getPoint().distanceTo(point2));
        double directionY = (point2.getY() - this.getPoint().getY())/(this.getPoint().distanceTo(point2));

        // Make a new point object to represent the direction to the point passed in as the argument
        directionToPoint = new Point(directionX, directionY);

        return directionToPoint;
    }

    // Player shoots a bullet to the closest zombie and sets the bullet's attribute so that it is drawn
    public void shootBulletClosestZombie(Point shootDirection){
        bullet.getPoint().setX(this.getPoint().getX());
        bullet.getPoint().setY(this.getPoint().getY());
        bullet.setDirection(shootDirection);
        bullet.setToDraw(true);
    }

    /*
     * Method that finds the closest Zombie/Sandwich to the Player depending on the ArrayList parameter passed to it.
     * Returns a null reference if the ArrayList argument given doesn't contain anything; otherwise returns the point
     * of the closest Zombie/Sandwich to the Player.
     */
    public void findClosest(ArrayList<T> entityArray){
        if (entityArray.size() >= 1) {

            Point closestEntityPoint = entityArray.get(0).getPoint();
            double minDistance = this.getPoint().distanceTo(closestEntityPoint);
            double curDistance;

            for (T entity : entityArray) {
                curDistance = this.getPoint().distanceTo(entity.getPoint());
                if (curDistance < minDistance) {
                    minDistance = curDistance;
                    closestEntityPoint = entity.getPoint();
                }

            }
            setDirection(calculateDirection(closestEntityPoint));
        }
    }

    /*
     * Method used to check if player meets any of the sandwiches, if she does then she eats the sandwich she meets
     * and her energy level is increased by 5 as per project spec (a constant in Sandwich class). The method then
     * returns true the player has met with at least one sandwich and false otherwise.
     */
    public boolean playerMeetsASandwich(ArrayList<Sandwich> sandwichesArrayList){

        // boolean flag used to determine if the player has met with at least one sandwich
        boolean playerHasMetASandwich = false;
        for(Sandwich sandwich: sandwichesArrayList) {
            /*
             * If a player eats the sandwich, the player eats it, gains energy and then the sandwich is tagged
             * for removal
             */
            if (meet(sandwich.getPoint())) {
                energyLevel += Sandwich.getPlayerEnergyGained();
                playerHasMetASandwich = true;
                sandwichesArrayList.remove(sandwich);
                sandwichesArrayList.trimToSize();
                break;
            }
        }

        return playerHasMetASandwich;
    }

    /*
     * Method that checks if a player is within the shooting range of a zombie, if she is then she shoots a bullet
     * towards the zombie and her energy is reduced by 3 (as per project spec). As per project spec, the player can
     * only shoot one bullet and have one bullet flying towards the zombie at the same time, thus the method returns
     * true in the case that any one zombie is shot by the player; otherwise return false.
     */
    public boolean playerCanShootZombie(ArrayList<Zombie> zombiesArrayList){
        if(!bullet.toDraw()) {
            for (Zombie zombie : zombiesArrayList) {
                if (this.getPoint().distanceTo(zombie.getPoint()) < PLAYER_SHOOTING_RANGE) {
                    shootBulletClosestZombie(calculateDirection(zombie.getPoint()));
                    energyLevel -= Bullet.getPlayerShootEnergyConsumed();
                    return true;
                }
            }
        }
        return false;
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

    public Bullet getBullet() {
        return bullet;
    }
}
