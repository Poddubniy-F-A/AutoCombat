package Example.units;

public abstract class Unit {
    int hp, maxHp, defence,
            attackDistance, damageSize,
            speed;
    protected double x, y;
    protected boolean isAlive;

    protected Unit(int x, int y) {
        this.x = x;
        this.y = y;

        isAlive = true;
    }

    public void showInfo() {
        System.out.println(this);
    }

    protected boolean checkAlive() {
        if (!isAlive) {
            System.out.println("Юнит мёртв");
        }
        return isAlive;
    }

    protected double getDistance(double x, double y) {
        return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
    }

    protected double getDistance(Unit unit) {
        return getDistance(unit.getX(), unit.getY());
    }

    public void changeLocation(int x, int y) {
        if (checkAlive()) {
            if (getDistance(x, y) <= speed) {
                this.x = x;
                this.y = y;
            }
        }
    }

    public void baseAttack(Unit target) {
        if (checkAlive()) {
            if (getDistance(target) <= attackDistance) {
                if (target.isAlive()) {
                    target.getDamage(damageSize);
                } else {
                    System.out.println("Цель уже мертва");
                }
            } else {
                System.out.println("До цели слишком далеко");
            }
        }
    }

    protected void getDamage(int damageSize) {
        hp -= Math.min(hp, Math.max(0, damageSize - defence));
        if (hp == 0) {
            isAlive = false;
        }

        showInfo();
    }

    protected void getHealing(int healSize) {
        hp = Math.min(maxHp, hp + healSize);

        showInfo();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isAlive() {
        return isAlive;
    }
}
