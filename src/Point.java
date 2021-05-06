public class Point {
    // Class that represents a coordinate

    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Calculates the distance between two points
    public double distanceTo(Point point2){
        return Math.sqrt(Math.pow((this.x - point2.getX()), 2) + Math.pow((this.y - point2.getY()), 2));
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
