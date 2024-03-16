package Example.model.units.instances;

import Example.model.Combat;
import Example.model.Name;
import Example.model.units.Healer;

public class Priest extends Healer {
    public Priest(int x, int y, Name name, Combat combat) {
        super(x, y, name, combat, 10, 0, 1, 10);
    }

    @Override
    public String toString() {
        return "Монах " + name + ", запас здоровья: " + hp + ", запас маны: " + mana;
    }
}
