package Example.model.units.instances;

import Example.model.Combat;
import Example.model.Name;
import Example.model.units.Shooter;

public class Crossbowman extends Shooter {
    public Crossbowman(int x, int y, Name name, Combat combat) {
        super(x, y, name, combat, 15, 1, 3, 5, 0.5, 15);
    }

    @Override
    public String toString() {
        return "Арбалетчик " + name + ", запас здоровья: " + hp + ", выстрелов осталось: " + shots;
    }
}
