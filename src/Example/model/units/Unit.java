package Example.model.units;

import Example.model.Combat;
import Example.model.Name;
import Example.model.metric.Field;

import java.util.ArrayList;

public abstract class Unit implements Stepable {
    protected final Field field;
    protected final Name name;
    protected final Combat combat;

    protected int hp, maxHp, defence, initiative;

    protected boolean isAlive;
    protected int deathTime;

    protected Unit(int x, int y, Name name, Combat combat) {
        field = new Field(x, y);
        this.name = name;
        this.combat = combat;

        isAlive = true;
    }

    protected void setBaseParameters(int maxHp, int defence, int initiative) {
        hp = maxHp;
        this.maxHp = maxHp;
        this.defence = defence;

        this.initiative = initiative;
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

    protected ArrayList<Unit> getAllies() {
        return combat.getAlliesOf(this);
    }

    protected ArrayList<Unit> getEnemies() {
        return combat.getOpponentsOf(this);
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

    public int getDeathTime() {
        return deathTime;
    }
}
