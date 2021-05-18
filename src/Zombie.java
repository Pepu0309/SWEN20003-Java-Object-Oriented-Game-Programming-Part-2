/**
 * Zombie class which contains all attributes and behaviour associated with Zombie; inherits from the
 * GameEntity class
 */
public class Zombie extends GameEntity{

    // Static attribute that tracks the number of Zombie objects
    private static int numZombies = 0;

    /**
     * Constructor of the Zombie class which takes x and y coordinates of the position in which the Zombie object
     * is supposed to be created on. Calls the constructor of the GameEntity superclass with the image argument
     * res/images/zombie.png as that is to be the image of Zombie objects as defined by project specification.
     *
     * @param x double value indicating x-coordinate of the position of the Zombie object
     * @param y double value indicating y-coordinate of the position of the Zombie object
     */
    public Zombie(double x, double y){
        super(x, y, "res/images/zombie.png");
        numZombies++;
    }

    /**
     * Getter method for the numZombies static attribute of the Zombie class.
     *
     * @return the numZombies static attribute of the Zombie class
     */
    public static int getNumZombies() {
        return numZombies;
    }

    /**
     * Setter method for the numZombies static attribute of the Sandwich class.
     *
     * @param numZombies an int value to set the numZombies static attribute to
     */
    public static void setNumZombies(int numZombies) {
        Zombie.numZombies = numZombies;
    }
}
