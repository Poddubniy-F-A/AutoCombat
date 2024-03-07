package Example.units;

import Example.Name;

import java.util.ArrayList;

public class Priest extends Unit {
    public Priest(int x, int y, Name name) {
        super(x, y, name);
        setBaseParameters(15, 0, 1, 1, 5, 1);
    }

    @Override
    public void step(ArrayList<Unit> allies, ArrayList<Unit> enemies) {
        if (isAlive) {
            System.out.println("\nХодит " + this);
        }
    }

    private void heal(Unit target) {
        final int healDistance = 2, healValue = 5;

        if (checkAlive() && checkDistance(target, healDistance) && checkTargetAlive(target)) {
            target.getHealing(healValue);
        }
    }

    @Override
    public String toString() {
        return "Монах " + name + ", запас здоровья: " + hp;
    }
}
