package Example.model.units.examples;

import Example.model.Combat;
import Example.model.Name;
import Example.model.units.MeleeUnit;

public class Bandit extends MeleeUnit {
    public Bandit(int x, int y, Name name, Combat combat) {
        super(x, y, name, combat);
        setBaseParameters(15, 2, 1, 3, 5, 2);
    }

    @Override
    public String toString() {
        return "Разбойник " + name + ", запас здоровья: " + hp;
    }
}
