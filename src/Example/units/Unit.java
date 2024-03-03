package Example.units;

import Example.Name;

public abstract class Unit implements Stepable {
    private final Coordinates coordinates;
    protected final Name name;

    private boolean isAlive;
    protected int hp, maxHp, defence,
            attackDistance, damageSize,
            speed, initiative;

    protected Unit(int x, int y, Name name) {
        coordinates = new Coordinates(x, y);
        this.name = name;

        isAlive = true;
    }

    protected void setBaseParameters(int maxHp, int defence,
                                     int attackDistance, int damageSize,
                                     int speed, int initiative) {
        hp = maxHp;
        this.maxHp = maxHp;
        this.defence = defence;

        this.attackDistance = attackDistance;
        this.damageSize = damageSize;

        this.speed = speed;
        this.initiative = initiative;
    }

    protected void changeLocation(int x, int y) {
        if (checkAlive()) {
            if (coordinates.getDistance(new Coordinates(x, y)) <= speed) {
                coordinates.setX(x);
                coordinates.setY(y);
            } else {
                System.out.println("Поле вне зоны досягаемости");
            }
        }
    }

    protected void baseAttack(Unit target) {
        if (checkAlive() && checkDistance(target, attackDistance) && checkTargetAlive(target)) {
            target.getDamage(damageSize);
        }
    }

    protected boolean checkAlive() {
        return check(isAlive, "Юнит мёртв");
    }

    protected boolean checkDistance(Unit target, int maxDist) {
        return check(getDistance(target) <= maxDist, "До цели слишком далеко");
    }

    protected double getDistance(Unit unit) {
        return coordinates.getDistance(unit.getCoordinates());
    }

    private Coordinates getCoordinates() {
        return coordinates;
    }

    protected boolean checkTargetAlive(Unit target) {
        return check(target.isAlive(), "Цель уже мертва");
    }

    protected boolean isAlive() {
        return isAlive;
    }

    private boolean check(boolean res, String notify) {
        if (!res) {
            System.out.println(notify);
        }
        return res;
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

    private void showInfo() {
        System.out.println(this);
    }

    public int getInitiative() {
        return initiative;
    }
}
