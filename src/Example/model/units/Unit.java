package Example.model.units;

import Example.model.Combat;
import Example.model.Name;
import Example.model.metric.Field;

import java.util.ArrayList;

public abstract class Unit implements Stepable {
    protected final Field field;
    protected final Name name;
    protected final Combat combat;

    protected boolean isAlive;
    protected int deathTime;

    protected int hp, maxHp, defence,
            maxAttackDistance, damageSize,
            speed, initiative;

    protected Unit(int x, int y, Name name, Combat combat) {
        field = new Field(x, y);
        this.name = name;
        this.combat = combat;

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

    protected void changeLocation(Field target) {
        if (checkAlive() && !target.equals(field)) {
            int x = target.getX(), y = target.getY();
            System.out.println("Перемещается в " + x + ", " + y);

            this.field.setX(x);
            this.field.setY(y);
        }
    }

    public void baseAttack(Unit target) {
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

    public Field getField() {
        return field;
    }

    protected boolean checkTargetAlive(Unit target) {
        return check(target.isAlive(), "Цель уже мертва");
    }

    public boolean isAlive() {
        return isAlive;
    }

    protected boolean check(boolean res, String notify) {
        if (!res) {
            System.out.println(notify);
        }
        return res;
    }

    public void getDamage(int damageSize) {
        hp = Math.max(0, hp - Math.max(0, damageSize - defence));
        if (hp == 0) {
            isAlive = false;
            deathTime = combat.getStep();
        }
        showInfo();
    }

    public void getRevival() {
        isAlive = true;
        getHealing();
    }

    public void getHealing() {
        hp = maxHp;
        showInfo();
    }

    protected void showInfo() {
        System.out.println(this);
    }

    protected ArrayList<Unit> getAllies() {
        return combat.getAlliesOf(this);
    }

    protected ArrayList<Unit> getEnemies() {
        return combat.getOpponentsOf(this);
    }

    protected int getHpDelta() {
        return maxHp - hp;
    }

    protected int getHp() {
        return hp;
    }

    public int getInitiative() {
        return initiative;
    }

    public int getDeathTime() {
        return deathTime;
    }
}
