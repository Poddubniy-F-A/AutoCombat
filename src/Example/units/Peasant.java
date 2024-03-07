package Example.units;

import Example.Name;

public class Peasant extends MeleeUnit {
    public Peasant(int x, int y, Name name) {
        super(x, y, name);
        setBaseParameters(10, 0, 1, 1, 5, 0);
    }

    @Override
    public String toString() {
        return "Крестьянин " + name + ", запас здоровья: " + hp;
    }
}
