import bagel.*;
import java.util.*;

/**
 * Player class which contains all attributes and behaviour associated with the player; inherits from the
 * MovableEntity class (which inherits from the GameEntity class)
 * @param <T> a generic type parameter representing any class which inherits the GameEntity class
 */
public class Player<T extends GameEntity> extends MovableEntity{

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

    /**
     * Constructor for the Player class which takes in double arguments x and y  to determine the initial position and
     * an int argument of energyLevel in order to determine initial energy level of the player. The direction is left
     * initialised at (0,0) on creation of the object as algorithm 1 hasn't been ran yet.
     * Algorithm 1 will be responsible for determining the direction of the player.
     * @param x x-coordinate of the initial position of the player
     * @param y y-coordinate of the initial position of the player
     * @param energyLevel initial energy level of the player
     */
    public Player(double x, double y, int energyLevel){
        super(x, y, "res/images/player.png");
        this.energyLevel = energyLevel;
    }


    /**
     * Calculates normalised direction from the player to a given Point parameter using formula in Week 5 Workshop and
     * Project Specification and returns the result of the calculation as a Point object.
     *
     * @param point2 the Point intended to be used for the calculation of direction
     * @return a Point object indicating the direction of the player to an entity in the game
     */
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

    /**
     * Method called when the player shoots a bullet towards the closest zombie. The bullet object's coordinates are
     * then set to the player's current coordinates with it's direction heading to the zombie that the player shoots at.
     * The toDraw attribute of the bullet object is then set to true such that it is drawn in the game window.
     *
     * @param shootDirection the direction in which the bullet is being shot towards
     */
    public void shootBulletClosestZombie(Point shootDirection){
        bullet.getPoint().setX(this.getPoint().getX());
        bullet.getPoint().setY(this.getPoint().getY());
        bullet.setDirection(shootDirection);
        bullet.setToDraw(true);
    }


    /**
     * Method that finds the closest Zombie/Sandwich to the Player depending on the ArrayList parameter passed to it.
     * The direction of the player is then set to the closest Zombie/Sandwich. The method checks if the ArrayList
     * parameter passed to it has actual entities before continuing with the execution, if that condition is not
     * satisfied, the method simply does nothing.
     *
     * @param entityArray an array list of type Zombie or Sandwich
     */
    public void findClosest(ArrayList<T> entityArray){
        if (entityArray.size() >= 1) {

            /*
             * If the entity Array has more than 1 entity, the closestEntity is initially set as the first entity
             * in the array and the minimum distance is set to be the distance of the player to that entity.
             */
            Point closestEntityPoint = entityArray.get(0).getPoint();
            double minDistance = this.getPoint().distanceTo(closestEntityPoint);
            double curDistance;

             // Thus, the program starts checking and comparing from every other entity onwards from the first one.
            for (int i = 1; i < entityArray.size(); i++) {
                curDistance = this.getPoint().distanceTo(entityArray.get(i).getPoint());
                if (curDistance < minDistance) {
                    minDistance = curDistance;
                    closestEntityPoint = entityArray.get(i).getPoint();
                }

            }
            // Direction of the player is set to the closestEntity of Zombie/Sandwich depending on parameter passed.
            setDirection(calculateDirection(closestEntityPoint));
        }
    }

    /**
     * Method that calls the entityInteractsWithOneOfGroup method defined in the MovableEntity class to check if
     * player meets any of the sandwiches, if she does then she eats the sandwich she meets
     * and her energy level is increased by 5 as per project spec (a constant in Sandwich class). The method then
     * returns true if the player has met with at least one sandwich and false otherwise.
     *
     * @param sandwichesArrayList an array list of type sandwich
     * @return a boolean value of true if the player successfully meets and eats a sandwich, false otherwise.
     */
    public boolean playerMeetsASandwich(ArrayList<Sandwich> sandwichesArrayList){
        // If a player meets a sandwich, the player eats it, gains energy and then the sandwich is tagged for removal.
        if (entityInteractsWithOneOfGroup(sandwichesArrayList, PLAYER_MEET_CONDITION)){
            energyLevel += Sandwich.getPlayerEnergyGained();
            return true;
        }

        return false;
    }

    /**
     * Method that checks if a player is within the shooting range of a zombie, if she is then she shoots a bullet
     * towards the zombie and her energy is reduced by 3 (as per project spec). As per project spec, the player can
     * only shoot one bullet and have one bullet flying towards the zombie at the same time, thus the method returns
     * true in the case that any one zombie is shot by the player; otherwise return false.
     *
     * @param zombiesArrayList an array list of type Zombie
     * @return boolean value true if the player can and does shoot a Zombie, false otherwise
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

    /**
     * Getter method for the energy level attribute of the Player class.
     *
     * @return energy level attribute of the Player object
     */
    public int getEnergyLevel() {
        return energyLevel;
    }

    /**
     * Setter method for the energy level attribute of the Player class.
     *
     * @param energyLevel an int value to set the Player's energy level attribute to
     */
    public void setEnergyLevel(int energyLevel) {
        this.energyLevel = energyLevel;
    }

    /**
     * Getter method for the ENERGY_REQUIRED_MOVE_TOWARDS_ZOMBIE constant of the Player class.
     *
     * @return the ENERGY_REQUIRED_MOVE_TOWARDS_ZOMBIE constant of the Player class
     */
    public static int getPlayerEnergyGoesTowardsZombie() {
        return ENERGY_REQUIRED_MOVE_TOWARDS_ZOMBIE;
    }

    /**
     * Getter method for the PLAYER_STEP_SIZE constant of the Player class.
     *
     * @return the PLAYER_STEP_SIZE constant of the Player class
     */
    public static double getPlayerStepSize() {
        return PLAYER_STEP_SIZE;
    }

    /**
     * Getter method for the PLAYER_MEET_CONDITION constant of the Player class.
     *
     * @return the PLAYER_MEET_CONDITION constant of the Player class
     */
    public static double getPlayerMeetCondition() {
        return PLAYER_MEET_CONDITION;
    }

    /**
     * Getter method for the bullet object that the Player object contains.
     *
     * @return the bullet object that the Player object contains.
     */
    public Bullet getBullet() {
        return bullet;
    }
}
