package Example.model.metric;

public class Field {
    private int x, y;

    public Field(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double getDistance(Field target) {
        return Math.sqrt(Math.pow(x - target.getX(), 2) + Math.pow(y - target.getY(), 2));
    }

    public boolean equals(Field field) {
        return field.getX() == x && field.getY() == y;
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
