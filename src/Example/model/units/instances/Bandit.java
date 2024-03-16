package Example.model.units.instances;

import Example.model.Combat;
import Example.model.Name;
import Example.model.units.MeleeUnit;

public class Bandit extends MeleeUnit {
    public Bandit(int x, int y, Name name, Combat combat) {
        super(x, y, name, combat);
        setBaseParameters(10, 1, 1, 6, 4, 2);
    }

    @Override
    public String toString() {
        return "Разбойник " + name + ", запас здоровья: " + hp;
    }
}