import bagel.*;
import bagel.util.Colour;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * ShadowTreasure class which is an example Bagel game.
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
    // Constant: used to define the font size
    private static final int FONT_SIZE = 20;

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
    private Font energyFont = new Font("res/font/DejaVuSans-Bold.ttf", FONT_SIZE);

    /**
     * Writes the bullet's position info to the output.csv file as per the project specification

     * @param x the current x coordinate of the bullet to be written to output.csv
     * @param y the current y coordinate of the bullet to be written to output.csv
     */
    public static void writeBulletInfo(double x, double y) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("res/IO/output.csv", true))){
            pw.println(df.format(x) + "," + df.format(y));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructor for the ShadowTreasure class which initializes a ShadowTreasure game. Writes to output.csv with
     * an empty string to wipe the file of it's previous contents.
     *
     * @throws IOException when there is an error with loading environment.csv or doing an initial write to output.csv
     *                     to wipe it
     */
    public ShadowTreasure() throws IOException {
        this.loadEnvironment("res/IO/environment.csv");
        // Add code to initialize other attributes as needed

        // Create a file writer object to wipe the contents in res/IO/output.csv
        FileWriter fw = new FileWriter("res/IO/output.csv");
        fw.write("");
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

    /**
     * Display all entities according to their current positions as well as the energy level.
     * Displays the static background first so it's behind.
     */
    public void displayAll(){
        background.drawFromTopLeft(WINDOW_TOP_LEFT.getX(), WINDOW_TOP_LEFT.getY());
        player.drawEntity();
        for(Zombie curZombie: zombiesArrayList){
            curZombie.drawEntity();
        }
        for(Sandwich curSandwich: sandwichesArrayList){
            curSandwich.drawEntity();
        }
        treasure.drawEntity();
        player.getBullet().drawEntity();

        // Draws the energy level of player at coordinates (20, 760) with the colour being black
        energyFont.drawString(String.format("energy: %d", player.getEnergyLevel()), ENERGY_LEVEL_TEXT_POINT.getX(),
                ENERGY_LEVEL_TEXT_POINT.getY(), energyFontDrawOptions.setBlendColour(Colour.BLACK));

    }

    /**
     * Checking the conditions for a victory, player must meet the treasure when it is reachable.
     *
     * @return a boolean value true if the game win conditions have been met, false otherwise
     */
    public boolean gameWin(){
        if(player.meet(treasure.getPoint(), Player.getPlayerMeetCondition()) && treasure.isReachable()){
            treasure.setPlayerHasReached(true);
            return true;
        }
        return false;
    }

    /**
     * Checking the conditions for the game to be lost, all individual conditions have to be satisfied.
     *
     * @return a boolean value true if the game lose conditions have been met, false otherwise.
     */
    public boolean gameLose(){
        if(player.getEnergyLevel() < 3 && Zombie.getNumZombies() > 0 && Sandwich.getNumSandwiches() == 0
                && !player.getBullet().toDraw()){
            return true;
        }
        return false;
    }

    /**
     * Method used to check whether the conditions have been met for the game to end. If either one of the game win or
     * game lose conditions have been satisfied, the endGame method is called.
     */
    public void checkGameEndConditions(){
        if (gameWin() || gameLose()){
            endGame();
        }
    }

    /**
     * Method that the checkGameEndConditions method calls to end the game if the conditions have been met.
     */
    public void endGame(){
        System.out.print(player.getEnergyLevel());
        if(treasure.playerHasReached()){
            System.out.println(",success!");
        }
        // Close the window and exit the program
        Window.close();
        System.exit(SUCCESSFUL_TERMINATION);
    }

    /**
     * Method which is called by the updateTick method which encompasses the whole "Player Interacts with Entities"
     * subsection of Algorithm 1.
     */
    public void playerInteractsWithEntities(){
        /*
         * If a player meets a sandwich, the logic will be handled in the Player class; the ShadowTreasure class
         * only needs to know that the player has met a sandwich, the total number of sandwiches is decreased by 1
         * and the method finishes executing if that's the case.
         */
        if(player.playerMeetsASandwich(sandwichesArrayList)){
            Sandwich.setNumSandwiches(Sandwich.getNumSandwiches() - 1);
        /*
         * Otherwise the player then checks if it's in shooting range of any zombie; the logic is handled by the
         * player class once again. Therefore, the method writes the initial position of the bullet into output.csv
         *  if the player can shoot (and does shoot) a bullet towards a zombie.
         */
        } else if (player.playerCanShootZombie(zombiesArrayList)){
            writeBulletInfo(player.getBullet().getPoint().getX(), player.getBullet().getPoint().getY());
        }

        // Similarly, if both conditions are not met, the method doesn't do anything and finishes executing

    }

    /**
     * Method which is called by the updateTick method which encompasses the whole "Set Player Moving Direction"
     * subsection of Algorithm 1.
     */
    public void setPlayerMovingDirection(){
        if(zombiesArrayList.size() == 0){
            treasure.setReachable(true);
            player.setDirection(player.calculateDirection(treasure.getPoint()));
        } else if (player.getEnergyLevel() >= Player.getPlayerEnergyGoesTowardsZombie()){
            player.findClosest(zombiesArrayList);
        } else {
            player.findClosest(sandwichesArrayList);
        }

        player.moveStep(Player.getPlayerStepSize());
    }

    /**
     * Method called to update the game status every tick
     */
    public void updateTick(){
        // Algorithm 1 from the specification, called every game tick

        // Checks if the conditions are met for the game to end, if the the conditions are met then the game is ended
        checkGameEndConditions();

        // Otherwise the program executes everything else
        playerInteractsWithEntities();
        setPlayerMovingDirection();

        /*
         * If the bullet needs to be drawn, that means it's shot by the player and the bullet needs to be moved and
         * it's position written to output.csv
         */
        if(player.getBullet().toDraw()){
            player.getBullet().moveStep(Bullet.getBulletStepSize());
            writeBulletInfo(player.getBullet().getPoint().getX(), player.getBullet().getPoint().getY());
        }

        // If bullet kills a zombie, then the number of total zombies is decreased by 1
        if(player.getBullet().killsZombie(zombiesArrayList)){
            Zombie.setNumZombies(Zombie.getNumZombies() -1);
        }

    }

    /**
     * The entry point for the program.
     */
    public static void main(String[] args) throws IOException {
        ShadowTreasure game = new ShadowTreasure();
        game.run();
    }
}
