public class Bullet extends MovableEntity{
    /*
     * Bullet class which the player possesses; inherits from the MovableEntity class (which inherits from the
     * GameEntity class)
     */

    private static final double BULLET_STEP_SIZE = 25;
    private static final int PLAYER_SHOOT_ENERGY_CONSUMED = 3;
    private static final double BULLET_MEET_CONDITION = 25;

    public Bullet(double x, double y){
        super(x, y, "res/images/bullet.png", BULLET_STEP_SIZE, BULLET_MEET_CONDITION);
    }
}
