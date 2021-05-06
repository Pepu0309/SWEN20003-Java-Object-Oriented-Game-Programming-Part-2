import bagel.*;
import bagel.util.Colour;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.*;

/**
 * An example Bagel game.
 */
public class ShadowTreasure extends AbstractGame {

    // Some constants to be used in the operation of the ShadowTreasure Class

    // Constant: used to check whether the current frame is a multiple of 10
    private static final int FRAMES_PER_TICK = 10;
    // Constant: used with System.exit() method to terminate the game successfully
    private static final int SUCCESSFUL_TERMINATION = 0;
    // Constant: used to define the coordinates of the energy level text
    private static final Point ENERGY_LEVEL_TEXT_POINT = new Point(20, 760);
    // Constant: used to define the coordinates of the top left of the window
    private static final Point WINDOW_TOP_LEFT = new Point(0, 0);

    // for rounding double number; use this to print the location of the player
    private static DecimalFormat df = new DecimalFormat("0.00");

    // Declare entities and initialise background according to instructions from specification
    private Player player;
    private ArrayList<Zombie> zombiesArrayList = new ArrayList<Zombie>();
    private ArrayList<Sandwich> sandwichesArrayList = new ArrayList<Sandwich>();
    private Treasure treasure;
    private final Image background = new Image("res/images/background.png");

    // Counting the number of frames from 1 as instructed by the project specification
    private int frameCounter = 1;

    // Declare an object of DrawOptions class from Bagel package to be used later for displaying energy level of player
    private DrawOptions energyFontDrawOptions = new DrawOptions();
    // Initialise object of Font class from Bagel packaged as instructed by project specification
    private final Font energyFont = new Font("res/font/DejaVuSans-Bold.ttf", 20);

    public static void printInfo(double x, double y, int e) {
        System.out.println(df.format(x) + "," + df.format(y) + "," + e);
    }

    public ShadowTreasure() throws IOException {
        this.loadEnvironment("res/IO/environment.csv");
        // Add code to initialize other attributes as needed

    }

    /**
     * Load from input file
     */
    private void loadEnvironment(String filename){
        // Using buffered reader and file reader to read from the environment.csv file.
        try (BufferedReader br =
                     new BufferedReader(new FileReader(filename))) {
            String fileText;

            // Reads each row of the file until there are no rows left to read.
            while ((fileText = br.readLine()) != null) {

                /* Replaces all occurrences of anything that's not an alphanumerical, comma or dot character with an
                 * empty string, using regex expression [^,.a-zA-z0-9]+. Code referenced from
                 * Ni Ding's announcement and then modified by adding the exclusion of , and . characters to check
                 * the whole CSV row for special characters.
                 */
                fileText = fileText.replaceAll("[^,.a-zA-z0-9]+", "");
                String[] currentCSVRow = fileText.split(",");

                String entityType = currentCSVRow[0];
                // Checks which entity of the game the row is referring to in the csv file and then initializes it.
                if (entityType.equals("Player")){
                    player = new Player(Double.parseDouble(currentCSVRow[1]), Double.parseDouble(currentCSVRow[2]),
                            Integer.parseInt(currentCSVRow[3]));

                } else if (entityType.equals("Zombie")){
                    zombiesArrayList.add(new Zombie(Double.parseDouble(currentCSVRow[1]),
                            Double.parseDouble(currentCSVRow[2])));

                } else if (entityType.equals("Sandwich")){
                    sandwichesArrayList.add(new Sandwich(Double.parseDouble(currentCSVRow[1]),
                            Double.parseDouble(currentCSVRow[2])));
                } else if (entityType.equals("Treasure")){
                    treasure = new Treasure(Double.parseDouble(currentCSVRow[1]),
                            Double.parseDouble(currentCSVRow[2]));
            }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Performs a state update.
     */
    @Override
    public void update(Input input) {
        // Logic to update the game, as per specification must go here

        // Updates the game status every 10 frames
        if(frameCounter % FRAMES_PER_TICK == 0) {
            updateTick();
        }

        // Draws the images according to the coordinates that they are in
        displayAll();

        // Increment frame counter to keep track of current frame
        frameCounter++;

    }

    // Display all entities to update their positions. Displays the static background first so it's behind.
    public void displayAll(){
        background.drawFromTopLeft(WINDOW_TOP_LEFT.getX(), WINDOW_TOP_LEFT.getY());
        player.drawEntity();
        for(Zombie curZombie: zombiesArrayList){
            curZombie.drawEntity();
        }
        for(Sandwich curSandwich: sandwichesArrayList){
            curSandwich.drawEntity();
        }

        // Draws the energy level of player at coordinates (20, 760) with the colour being black
        energyFont.drawString(String.format("energy: %d", player.getEnergyLevel()), ENERGY_LEVEL_TEXT_POINT.getX(),
                ENERGY_LEVEL_TEXT_POINT.getY(), energyFontDrawOptions.setBlendColour(Colour.BLACK));

    }

    // Method called to update the game status every tick
    public void updateTick(){

        // Algorithm 1 from the specification, called every game tick
        if (player.meet(zombie.getPoint())){
            player.setEnergyLevel(player.getEnergyLevel() - Zombie.getPlayerEnergyLoss());

            // prints information of the player right before game is terminated
            printInfo(player.getPoint().getX(), player.getPoint().getY(), player.getEnergyLevel());

            // Close the window and exit the program
            Window.close();
            System.exit(SUCCESSFUL_TERMINATION);

        } else if (player.meet(sandwich.getPoint()) && !sandwich.isEaten()){
            player.setEnergyLevel(player.getEnergyLevel() + Sandwich.getPlayerEnergyGained());
            sandwich.setEaten(true);
        }

        // prints the information of the player right before moving
        printInfo(player.getPoint().getX(), player.getPoint().getY(), player.getEnergyLevel());
        if (player.getEnergyLevel() >= Player.getPlayerEnergyGoesTowardsZombie()){
            player.setPlayerDirectionTo(zombie.getPoint());
        } else {
            player.setPlayerDirectionTo(sandwich.getPoint());
        }
        player.moveStep();

    }

    /**
     * The entry point for the program.
     */
    public static void main(String[] args) throws IOException {
        ShadowTreasure game = new ShadowTreasure();
        game.run();
    }
}
