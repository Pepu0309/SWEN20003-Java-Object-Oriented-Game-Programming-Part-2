public class Treasure extends GameEntity{

    private boolean isReachable = false;
    private boolean playerHasReached = false;

    public Treasure(double x, double y ){
        super(x, y, "res/images/treasure.png");
    }

    public boolean isReachable() {
        return isReachable;
    }

    public void setReachable(boolean reachable) {
        isReachable = reachable;
    }

    public boolean isPlayerHasReached() {
        return playerHasReached;
    }

    public void setPlayerHasReached(boolean playerHasReached) {
        this.playerHasReached = playerHasReached;
    }
}
