package Example.units;

public class Coordinates {
    private int x, y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double getDistance(Coordinates targetCoordinates) {
        return Math.sqrt(Math.pow(x - targetCoordinates.getX(), 2) + Math.pow(y - targetCoordinates.getY(), 2));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
