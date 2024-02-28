package Example.units;

public abstract class Unit {
    protected int hp, maxHp, defence,
            attackDistance, damageSize,
            speed;
    private double x, y;
    private boolean isAlive;

    protected Unit(int x, int y) {
        this.x = x;
        this.y = y;

        isAlive = true;
    }

    public void showInfo() {
        System.out.println(this);
    }

    protected double getDistance(double x, double y) {
        return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
    }

    public double getDistance(Unit unit) {
        return getDistance(unit.getX(), unit.getY());
    }

    private boolean check(boolean res, String notify) {
        if (!res) {
            System.out.println(notify);
        }
        return res;
    }

    protected boolean checkAlive() {
        return check(isAlive, "Юнит мёртв");
    }

    protected boolean checkDistance(Unit target, int maxDist) {
        return check(getDistance(target) <= maxDist, "До цели слишком далеко");
    }

    protected boolean checkTargetAlive(Unit target) {
        return check(target.isAlive(), "Цель уже мертва");
    }

    public void changeLocation(int x, int y) {
        if (checkAlive()) {
            if (getDistance(x, y) <= speed) {
                this.x = x;
                this.y = y;
            } else {
                System.out.println("Поле вне зоны досягаемости");
            }
        }
    }

    public void baseAttack(Unit target) {
        if (checkAlive() && checkDistance(target, attackDistance) && checkTargetAlive(target)) {
            target.getDamage(damageSize);
        }
    }

    protected void getDamage(int damageSize) {
        hp = Math.max(0, hp - Math.max(0, damageSize - defence));
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
