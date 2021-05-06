import bagel.Image;

public class Zombie extends GameEntity{
    /*
     Zombie class which contains all attributes and behaviour associated with Sandwich; inherits from the
     GameEntity class
     */

    // Static attribute that tracks the number of Zombie objects
    private static int numZombies;

    public Zombie(double x, double y){
        super(x, y, "res/images/zombie.png");
    }

    public static int getNumZombies() {
        return numZombies;
    }

    public static void setNumZombies(int numZombies) {
        Zombie.numZombies = numZombies;
    }
}
