import bagel.Image;
public class Sandwich extends GameEntity{
    /*
     Sandwich class which contains all attributes and behaviour associated with Sandwich; inherits from the
     GameEntity class
     */

    // Constant: the amount of energy gained when the player consumes a sandwich from algorithm 1
    private static final int PLAYER_ENERGY_GAINED = 5;

    // Static attribute that tracks the number of Sandwich objects
    private static int numSandwiches;

    public Sandwich(double x, double y){
        super(x, y, "res/images/sandwich.png");
        numSandwiches++;
    }

    public static int getPlayerEnergyGained() {
        return PLAYER_ENERGY_GAINED;
    }

    public static int getNumSandwiches() {
        return numSandwiches;
    }

    public static void setNumSandwiches(int numSandwiches) {
        Sandwich.numSandwiches = numSandwiches;
    }

}

