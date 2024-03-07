package Example.units;

import Example.Name;

import java.util.ArrayList;

public class Magician extends Unit {
    private int mana, maxMana;

    public Magician(int x, int y, Name name) {
        super(x, y, name);
        setBaseParameters(15, 2, 2, 3, 3, 1);

        maxMana = 100;
        mana = maxMana;
    }

    @Override
    public void step(ArrayList<Unit> allies, ArrayList<Unit> enemies) {
        if (isAlive) {
            System.out.println("\nХодит " + this + ", маны осталось: " + mana);
        }
    }

    private void useSpell(Unit target) {
        final int spellCost = 25, spellDistance = 4, spellDamage = 25;

        if (checkAlive() && checkDistance(target, spellDistance) && checkTargetAlive(target)) {
            if (mana >= spellCost) {
                target.getDamage(spellDamage);
                mana -= spellCost;
            } else {
                System.out.println("Недостаточно маны");
            }
        }
    }

    @Override
    public String toString() {
        return "Колдун " + name + ", запас здоровья: " + hp;
    }
}
