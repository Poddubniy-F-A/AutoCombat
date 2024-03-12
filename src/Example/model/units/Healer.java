package Example.model.units;

import Example.model.Combat;
import Example.model.Name;
import Example.model.metric.Field;

import java.util.ArrayList;

public abstract class Healer extends Unit {
    private final int healingCost = 2, revivalCost = 10;

    protected int maxMana, mana;

    public Healer(int x, int y, Name name, Combat combat) {
        super(x, y, name, combat);
    }

    protected void setManaParameters(int maxMana) {
        this.maxMana = maxMana;
        mana = maxMana;
    }

    @Override
    public void step() {
        final double reviveNeedingLosses = getAllies().size() / 3.0;

        if (isAlive) {
            System.out.println("Ходит " + this);

            ArrayList<Unit> needHealingTeamMates = new ArrayList<>(),
                    deadTeamMates = new ArrayList<>();
            for (Unit unit : getAllies()) {
                if (unit.isAlive()) {
                    if (unit.getHpDelta() > 0) {
                        needHealingTeamMates.add(unit);
                    }
                } else {
                    deadTeamMates.add(unit);
                }
            }

            Unit healed = getPreferredHealed(needHealingTeamMates),
                    revived = getPreferredRevived(getRevivable(deadTeamMates));
            if (deadTeamMates.size() < reviveNeedingLosses && healed != null) {
                if (mana >= healingCost) {
                    heal(healed);
                } else {
                    regenerateMana();
                }
            } else if (revived != null) {
                if (mana >= revivalCost) {
                    revive(revived);
                } else {
                    regenerateMana();
                }
            } else {
                regenerateMana();
            }
        }
    }

    private Unit getPreferredHealed(ArrayList<Unit> needHealingTeamMates) {
        Unit result = null;
        int resHpDelta = 0;
        for (Unit unit : needHealingTeamMates) {
            int hpDelta = unit.getHpDelta();
            if (result == null || hpDelta > resHpDelta ||
                    (hpDelta == resHpDelta && getDistance(unit) < getDistance(result))) {
                result = unit;
                resHpDelta = hpDelta;
            }
        }

        return result;
    }

    private Unit getPreferredRevived(ArrayList<Unit> canBeRevived) {
        Unit result = null;
        int resHp = 0;
        for (Unit unit : canBeRevived) {
            int hp = unit.getHp();
            if (result == null || hp > resHp ||
                    (hp == resHp && getDistance(unit) < getDistance(result))) {
                result = unit;
                resHp = hp;
            }
        }

        return result;
    }

    private ArrayList<Unit> getRevivable(ArrayList<Unit> deadTeamMates) {
        ArrayList<Unit> allUnits = new ArrayList<>(getAllies());
        allUnits.addAll(getEnemies());

        ArrayList<Unit> result = new ArrayList<>(deadTeamMates);
        for (Unit dead : deadTeamMates) {
            Field field = dead.getField();
            for (Unit unit : allUnits) {
                if (unit != dead && unit.getField().equals(field) && (unit.isAlive() ||
                        unit.getDeathTime() > dead.getDeathTime())) {
                    result.remove(dead);
                    break;
                }
            }
        }

        return result;
    }

    public void heal(Unit target) {
        if (checkAlive() && checkTargetAlive(target) && checkEnoughMana(healingCost)) {
            System.out.println("Лечение!");

            target.getHealing();
            mana -= healingCost;
        }
    }

    public void revive(Unit target) {
        if (checkAlive() && checkEnoughMana(revivalCost)) {
            System.out.println("Воскрешение!");

            target.getRevival();
            mana -= revivalCost;
        }
    }

    private boolean checkEnoughMana(int needMana) {
        return check(mana >= needMana, "Недостаточно маны");
    }

    private void regenerateMana() {
        mana = Math.min(maxMana, mana + 1);
        System.out.println("Восстановление маны");
    }
}
