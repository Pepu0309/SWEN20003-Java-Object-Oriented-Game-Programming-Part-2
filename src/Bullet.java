import java.util.*;
/**
 * Bullet class which the player possesses; inherits from the MovableEntity class (which inherits from the
 * GameEntity class)
 */
public class Bullet extends MovableEntity{

    // Constant: bullet's step size as defined in the project specification
    private static final double BULLET_STEP_SIZE = 25;
    // Constant: energy consumed when a player shoots a bullet
    private static final int PLAYER_SHOOT_ENERGY_CONSUMED = 3;
    // Constant: distance used to determine when a bullet meets a zombie
    private static final double BULLET_MEET_CONDITION = 25;
    // Constant: the point used to instantiate the bullet object
    private static final Point DEFAULT_BULLET_POSITION = new Point(0,0);

    /*
     * boolean attribute which determines whether to draw the bullet on screen. Bullet is only drawn if it has been
     * shot by a player and has not hit a zombie.
     */
    private boolean toDraw = false;

    /**
     * Default constructor (only constructor) for the bullet object. Player will instantiate the bullet object using
     * this constructor and it's position will be at (0,0) initially. The rest of it's behaviour will be implemented
     * with regard to the Player class and Algorithm 1 in the ShadowTreasure class. Direction is left initialised
     * at (0,0) at the start as well (similar to the Player object) as well.
     */
    public Bullet(){
        super(DEFAULT_BULLET_POSITION.getX(), DEFAULT_BULLET_POSITION.getY(), "res/images/shot.png");
    }

    @Override
    /**
     * Overridden method that draws the bullet in the game window. Method has more specific behaviour in which the
     * bullet object goes through a check of whether it needs to be drawn before it is actually drawn.
     */
    public void drawEntity() {
        if(toDraw){
            super.drawEntity();
        }
    }

    /**
     * Method that determines if the bullet kills a zombie.
     *
     * @param zombieArrayList the array list containing all the zombies in the game
     * @return boolean value true if the bullet kills a zombie, false otherwise
     */
    public boolean killsZombie(ArrayList<Zombie> zombieArrayList){
        if (entityInteractsWithOneOfGroup(zombieArrayList, BULLET_MEET_CONDITION)){
            this.toDraw = false;
            return true;
        }
        return false;
    }

    /**
     * Getter method for toDraw attribute of Bullet class.
     *
     * @return toDraw attribute of the current Bullet object
     */
    public boolean toDraw() {
        return toDraw;
    }

    /**
     * Setter method that sets a bullet object's toDraw attribute to a boolean value of either true or false.
     *
     * @param toDraw a boolean value true or false to set the Bullet object's toDraw attribute to
     */
    public void setToDraw(boolean toDraw) {
        this.toDraw = toDraw;
    }

    /**
     * Getter method for the PLAYER_SHOOT_ENERGY_CONSUMED constant of the Bullet class.
     *
     * @return PLAYER_SHOOT_ENERGY_CONSUMED constant of the Bullet class
     */
    public static int getPlayerShootEnergyConsumed() {
        return PLAYER_SHOOT_ENERGY_CONSUMED;
    }

    /**
     * Getter method for the BULLET_STEP_SIZE constant of the Bullet class.
     *
     * @return BULLET_STEP_SIZE constant of the Bullet class.
     */
    public static double getBulletStepSize() {
        return BULLET_STEP_SIZE;
    }
}
