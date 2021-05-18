/**
 * Class that represents a point (a pair of 2D (x,y) coordinates) in the game window.
 */
public class Point {

    private double x;
    private double y;

    /**
     * Constructor for the Point class which creates a point object with the x and y coordinates given as parameters.
     * @param x double value indicating x-coordinate of a point to be created in the game window
     * @param y double value indicating y-coordinate of a point to be created in the game window
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the euclidean distance between the calling Point object to the another Point passed as a parameter.
     *
     * @param point2 the other point intended to be used in the calculation
     * @return a double value indicating the euclidean distance between the calling Point object to the point parameter
     */
    public double distanceTo(Point point2){
        return Math.sqrt(Math.pow((this.x - point2.getX()), 2) + Math.pow((this.y - point2.getY()), 2));
    }

    /**
     * Getter method for the x attribute of the Point class.
     *
     * @return a double value indicating the x attribute of the Point object.
     */
    public double getX() {
        return x;
    }

    /**
     * Setter method for the x attribute of the Point class.
     *
     * @param x a double value to set the x attribute of the Point object to.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Getter method for the y attribute of the Point class.
     *
     * @return a double value indicating the y attribute of the Point object.
     */
    public double getY() {
        return y;
    }

    /**
     * Setter method for the y attribute of the Point class.
     *
     * @param y a double value to set the x attribute of the point object to.
     */
    public void setY(double y) {
        this.y = y;
    }
}
