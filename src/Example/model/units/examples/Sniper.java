package Example.model.units.examples;

import Example.model.Combat;
import Example.model.Name;
import Example.model.units.Shooter;

public class Sniper extends Shooter {
    public Sniper(int x, int y, Name name, Combat combat) {
        super(x, y, name, combat);
        setBaseParameters(10, 1, 1, 2, 4, 3);
        setShotParameters(10, 10, 5, 1.0);
    }

    @Override
    public String toString() {
        return "Снайпер " + name + ", запас здоровья: " + hp + ", выстрелов осталось: " + shots;
    }
}
