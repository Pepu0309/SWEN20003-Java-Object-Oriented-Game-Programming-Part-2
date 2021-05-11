import java.util.*;
public class Bullet extends MovableEntity{
    /*
     * Bullet class which the player possesses; inherits from the MovableEntity class (which inherits from the
     * GameEntity class)
     */

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

    /*
     * Default constructor (only constructor) for the bullet object. Player will instantiate the bullet object using
     * this constructor and then the rest of it's behaviour will be implemented with regard to the Player class
     * and Algorithm 1 in the ShadowTreasure class.
     */
    public Bullet(){
        super(DEFAULT_BULLET_POSITION.getX(), DEFAULT_BULLET_POSITION.getY(), "res/images/shot.png");
    }

    @Override
    // Bullet goes through a check of whether it needs to be drawn before it is actually drawn.
    public void drawEntity() {
        if(toDraw){
            super.drawEntity();
        }
    }

    public boolean killsZombie(ArrayList<Zombie> zombieArrayList){
        if (entityInteractsWithOneOfGroup(zombieArrayList, BULLET_MEET_CONDITION)){
            this.toDraw = false;
            return true;
        }
        return false;
    }


    public boolean toDraw() {
        return toDraw;
    }

    public void setToDraw(boolean toDraw) {
        this.toDraw = toDraw;
    }

    public static int getPlayerShootEnergyConsumed() {
        return PLAYER_SHOOT_ENERGY_CONSUMED;
    }

    public static double getBulletStepSize() {
        return BULLET_STEP_SIZE;
    }
}
