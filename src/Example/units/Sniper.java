package Example.units;

import Example.Name;

public class Sniper extends Shooter {
    public Sniper(int x, int y, Name name) {
        super(x, y, name);
        setBaseParameters(10, 1, 1, 2, 4, 3);
        setShotParameters(10, 10, 5, 1.0);
    }

    @Override
    public String toString() {
        return "Снайпер " + name + ", запас здоровья: " + hp;
    }
}
