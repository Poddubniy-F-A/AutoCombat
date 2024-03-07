package Example.units;

import Example.Name;

import java.util.ArrayList;

public abstract class Unit implements Stepable {
    protected final Field field;
    protected final Name name;

    protected boolean isAlive;
    protected int hp, maxHp, defence,
            maxAttackDistance, damageSize,
            speed, initiative;

    protected Unit(int x, int y, Name name) {
        field = new Field(x, y);
        this.name = name;

        isAlive = true;
    }

    protected void setBaseParameters(int maxHp, int defence,
                                     int attackDistance, int damageSize,
                                     int speed, int initiative) {
        hp = maxHp;
        this.maxHp = maxHp;
        this.defence = defence;

        this.maxAttackDistance = attackDistance;
        this.damageSize = damageSize;

        this.speed = speed;
        this.initiative = initiative;
    }

    protected Unit getNearestTarget(ArrayList<Unit> targets) {
        Unit nearestTarget = null;
        double minDist = Double.MAX_VALUE;

        for (Unit target : targets) {
            if (target.isAlive()) {
                double dist = getDistance(target);

                if (minDist > dist) {
                    nearestTarget = target;
                    minDist = dist;
                }
            }
        }

        return nearestTarget;
    }

    protected void changeLocation(Field field) {
        if (checkAlive() && !field.equals(this.field) && check(this.field.getDistance(field) <= speed, "Поле вне зоны досягаемости")) {
            int x = field.getX(), y = field.getY();
            System.out.println("Перемещается в " + x + ", " + y);

            this.field.setX(x);
            this.field.setY(y);
        }
    }

    protected void baseAttack(Unit target) {
        if (checkAlive() && checkDistance(target, maxAttackDistance) && checkTargetAlive(target)) {
            System.out.println("Атака!");
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
        return field.getDistance(unit.getField());
    }

    protected Field getField() {
        return field;
    }

    protected boolean checkTargetAlive(Unit target) {
        return check(target.isAlive(), "Цель уже мертва");
    }

    public boolean isAlive() {
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
