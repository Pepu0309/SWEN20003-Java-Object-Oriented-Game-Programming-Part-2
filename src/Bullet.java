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

    private boolean toDraw = false;

    /*
     * Default constructor (only constructor) for the bullet object. Player will instantiate the bullet object using
     * this constructor and then the rest of it's behaviour will be implemented with regard to the Player class
     * and Algorithm 1 in the ShadowTreasure class.
     */
    public Bullet(){
        super(DEFAULT_BULLET_POSITION.getX(), DEFAULT_BULLET_POSITION.getY(),
                "res/images/bullet.png", BULLET_STEP_SIZE, BULLET_MEET_CONDITION);
    }

    @Override
    public void drawEntity() {
        if(toDraw == true){
            super.drawEntity();
        }
    }

    public boolean isToDraw() {
        return toDraw;
    }

    public void setToDraw(boolean toDraw) {
        this.toDraw = toDraw;
    }
}
