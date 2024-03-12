package Example.model.units.examples;

import Example.model.Combat;
import Example.model.Name;
import Example.model.units.Shooter;

public class Crossbowman extends Shooter {
    public Crossbowman(int x, int y, Name name, Combat combat) {
        super(x, y, name, combat);
        setBaseParameters(10, 2, 1, 2, 3, 3);
        setShotParameters(15, 10, 10, 0.5);
    }

    @Override
    public String toString() {
        return "Арбалетчик " + name + ", запас здоровья: " + hp + ", выстрелов осталось: " + shots;
    }
}
