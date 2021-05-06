import bagel.Image;
public class Sandwich{
    // Sandwich class which contains all attributes and behaviour associated with Sandwich

    private final Image entityImage = new Image("res/images/sandwich.png");

    // Constant: the amount of energy gained when the player consumes a sandwich from algorithm 1
    private static final int PLAYER_ENERGY_GAINED = 5;

    // Point that provides information for the player's coordinates
    private Point point;
    private boolean isEaten;

    public Sandwich(double x, double y){
        this.point = new Point(x, y);
        this.isEaten = false;
    }

    // Draws entity on screen based on their coordinates
    public void drawEntity(){
        // Only continue to draw the sandwich if it has not been eaten
        if(!isEaten()) {
            entityImage.draw(point.getX(), point.getY());
        }
    }

    // Check if sandwich has been eaten by player
    public boolean isEaten() {
        return isEaten;
    }

    public void setEaten(boolean eaten) {
        isEaten = eaten;
    }

    public Point getPoint() {
        return point;
    }

    public static int getPlayerEnergyGained() {
        return PLAYER_ENERGY_GAINED;
    }
}

