package Example.model.units.instances;

import Example.model.Combat;
import Example.model.Name;
import Example.model.units.Healer;

public class Magician extends Healer {
    public Magician(int x, int y, Name name, Combat combat) {
        super(x, y, name, combat, 10, 1, 1, 10);
    }

    @Override
    public String toString() {
        return "Волшебник " + name + ", запас здоровья: " + hp + ", запас маны: " + mana;
    }
}
