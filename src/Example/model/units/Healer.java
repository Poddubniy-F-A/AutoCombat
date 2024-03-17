package Example.model.units;

import Example.model.Combat;
import Example.model.Name;

import java.util.ArrayList;

public abstract class Healer extends Unit {
    private final int maxMana;
    protected int mana;

    public Healer(int x, int y, Name name, Combat combat, int maxHp, int defence, int initiative, int maxMana) {
        super(x, y, name, combat, maxHp, defence, initiative);
        this.maxMana = maxMana;
        mana = maxMana;
    }

    @Override
    public void step() {
        final double reviveNeedingLosses = getAllies().size() / 3.0;
        final int healingCost = 2, revivalCost = 10;

        if (isAlive) {
            System.out.println("Ходит " + this);

            ArrayList<Unit> deadTeamMates = getDeadTeamMates();
            Unit healed = getPreferredHealed(),
                    revived = getPreferredRevived(deadTeamMates);
            if (deadTeamMates.size() < reviveNeedingLosses && healed != null) {
                if (mana >= healingCost) {
                    System.out.println("Лечение!");
                    healed.getHealing();
                    mana -= healingCost;
                } else {
                    regenerateMana();
                }
            } else if (revived != null) {
                if (mana >= revivalCost) {
                    System.out.println("Воскрешение!");
                    revived.getRevival();
                    mana -= revivalCost;
                } else {
                    regenerateMana();
                }
            } else {
                regenerateMana();
            }
        }
    }

    private ArrayList<Unit> getDeadTeamMates() {
        ArrayList<Unit> result = new ArrayList<>();
        for (Unit unit : getAllies()) {
            if (!unit.isAlive()) {
                result.add(unit);
            }
        }

        return result;
    }

    private Unit getPreferredHealed() {
        Unit result = null;
        int resHpDelta = 0;
        for (Unit unit : getNeedHealingTeamMates()) {
            int hpDelta = unit.getHpDelta();
            if (result == null || hpDelta > resHpDelta ||
                    (hpDelta == resHpDelta && getDistance(unit) < getDistance(result))) {
                result = unit;
                resHpDelta = hpDelta;
            }
        }

        return result;
    }

    private ArrayList<Unit> getNeedHealingTeamMates() {
        ArrayList<Unit> result = new ArrayList<>();
        for (Unit unit : getAllies()) {
            if (unit.isAlive() && unit.getHpDelta() > 0) {
                result.add(unit);
            }
        }

        return result;
    }

    private Unit getPreferredRevived(ArrayList<Unit> deadTeamMates) {
        Unit result = null;
        int resHp = 0;
        for (Unit unit : deadTeamMates) {
            if (unit.isRevivable()) {
                int hp = unit.getMaxHp();
                if (result == null || hp > resHp ||
                        (hp == resHp && getDistance(unit) < getDistance(result))) {
                    result = unit;
                    resHp = hp;
                }
            }
        }

        return result;
    }

    private void regenerateMana() {
        mana = Math.min(maxMana, mana + 1);
        System.out.println("Восстановление маны");
    }
}
