package Example.model.units.examples;

import Example.model.Combat;
import Example.model.Name;
import Example.model.units.Healer;

public class Priest extends Healer {
    public Priest(int x, int y, Name name, Combat combat) {
        super(x, y, name, combat);
        setBaseParameters(15, 0, 1, 1, 5, 1);
        setManaParameters(10);
    }

    @Override
    public String toString() {
        return "Монах " + name + ", запас здоровья: " + hp + ", запас маны: " + mana;
    }
}
