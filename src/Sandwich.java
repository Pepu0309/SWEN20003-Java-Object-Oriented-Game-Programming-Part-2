/**
 * Sandwich class which contains all attributes and behaviour associated with Sandwich; inherits from the
 * GameEntity class
 */
public class Sandwich extends GameEntity{
    // Constant: the amount of energy gained when the player consumes a sandwich from algorithm 1
    private static final int PLAYER_ENERGY_GAINED = 5;

    // Static attribute that tracks the number of Sandwich objects
    private static int numSandwiches = 0;

    /**
     * Constructor of the Sandwich class which takes x and y coordinates of the position in which the Sandwich object
     * is supposed to be created on. Calls the constructor of the GameEntity superclass with the image argument
     * res/images/sandwich.png as that is to be the image of Sandwich objects as defined by project specification.
     *
     * @param x double value indicating x-coordinate of the position of the Sandwich object
     * @param y double value indicating y-coordinate of the position of the Sandwich object
     */
    public Sandwich(double x, double y){
        super(x, y, "res/images/sandwich.png");
        numSandwiches++;
    }

    /**
     * Getter method for the PLAYER_ENERGY_GAINED constant of the Sandwich class.
     *
     * @return the PLAYER_ENERGY_GAINED constant of the Sandwich class
     */
    public static int getPlayerEnergyGained() {
        return PLAYER_ENERGY_GAINED;
    }

    /**
     * Getter method for the numSandwiches static attribute of the Sandwich class.
     *
     * @return the numSandwiches static attribute of the Sandwich class
     */
    public static int getNumSandwiches() {
        return numSandwiches;
    }

    /**
     * Setter method for the numSandwiches static attribute of the Sandwich class.
     *
     * @param numSandwiches an int value to set the numSandwiches static attribute to
     */
    public static void setNumSandwiches(int numSandwiches) {
        Sandwich.numSandwiches = numSandwiches;
    }

}

