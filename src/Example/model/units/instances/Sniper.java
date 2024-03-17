package Example.model.units.instances;

import Example.model.Combat;
import Example.model.Name;
import Example.model.units.Shooter;

public class Sniper extends Shooter {
    public Sniper(int x, int y, Name name, Combat combat) {
        super(x, y, name, combat, 10, 0, 3, 5, 1.0, 10);
    }

    @Override
    public String toString() {
        return "Снайпер " + name + ", запас здоровья: " + hp + ", выстрелов осталось: " + shots;
    }
}
