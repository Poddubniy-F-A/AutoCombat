package Example.units;

import Example.Name;

public class Crossbowman extends Shooter {
    public Crossbowman(int x, int y, Name name) {
        super(x, y, name);
        setBaseParameters(10, 2, 1, 2, 3, 3);
        setShotParameters(15, 10, 10, 0.5);
    }

    @Override
    public String toString() {
        return "Арбалетчик " + name + ", запас здоровья: " + hp;
    }
}
