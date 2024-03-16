package Example.model.units;

import Example.model.Combat;
import Example.model.Name;
import Example.model.metric.Field;

import java.util.ArrayList;

public abstract class Unit implements Presentable, Stepable {
    protected final Field field;
    protected final Name name;
    protected final Combat combat;
    protected final int maxHp, defence, initiative;

    protected int hp, deathTime;
    protected boolean isAlive;

    protected Unit(int x, int y, Name name, Combat combat, int maxHp, int defence, int initiative) {
        field = new Field(x, y);
        this.name = name;
        this.combat = combat;

        this.maxHp = maxHp;
        this.defence = defence;
        this.initiative = initiative;

        hp = maxHp;
        isAlive = true;
    }

    protected double getDistance(Unit unit) {
        return field.getDistance(unit.getField());
    }

    public Field getField() {
        return field;
    }

    protected void getDamage(int damageSize) {
        hp = Math.max(0, hp - Math.max(0, damageSize - defence));
        if (hp == 0) {
            isAlive = false;
            deathTime = combat.getStep();
        }
        showInfo();
    }

    protected void getRevival() {
        isAlive = true;
        getHealing();
    }

    protected void getHealing() {
        hp = maxHp;
        showInfo();
    }

    protected void showInfo() {
        System.out.println(this);
    }

    public boolean isRevivable() {
        if (isAlive) {
            return false;
        } else {
            for (Unit unit : getAllies()) {
                if (unit != this && unit.getField().equals(field) && (unit.isAlive() ||
                        unit.getDeathTime() > deathTime)) {
                    return false;
                }
            }
            for (Unit unit : getEnemies()) {
                if (unit.getField().equals(field) && (unit.isAlive() ||
                        unit.getDeathTime() > deathTime)) {
                    return false;
                }
            }
            return true;
        }
    }

    protected ArrayList<Unit> getAllies() {
        return combat.getAlliesOf(this);
    }

    protected ArrayList<Unit> getEnemies() {
        return combat.getOpponentsOf(this);
    }

    public int getDeathTime() {
        return deathTime;
    }

    protected int getHpDelta() {
        return maxHp - hp;
    }

    protected int getMaxHp() {
        return maxHp;
    }

    public int getInitiative() {
        return initiative;
    }

    public boolean isAlive() {
        return isAlive;
    }
}
