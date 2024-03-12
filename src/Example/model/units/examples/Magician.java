package Example.model.units.examples;

import Example.model.Combat;
import Example.model.Name;
import Example.model.units.Healer;

public class Magician extends Healer {
    public Magician(int x, int y, Name name, Combat combat) {
        super(x, y, name, combat);
        setBaseParameters(15, 2, 2, 3, 3, 1);
        setManaParameters(10);
    }

    @Override
    public String toString() {
        return "Волшебник " + name + ", запас здоровья: " + hp + ", запас маны: " + mana;
    }
}
