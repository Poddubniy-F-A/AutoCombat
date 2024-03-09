package Example.model.units.examples;

import Example.model.Name;
import Example.model.Team;
import Example.model.units.Unit;

public class Priest extends Unit {
    public Priest(int x, int y, Name name, Team team) {
        super(x, y, name, team);
        setBaseParameters(15, 0, 1, 1, 5, 1);
    }

    @Override
    public void step() {
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
