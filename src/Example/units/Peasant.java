package Example.units;

import Example.Name;

import java.util.ArrayList;

public class Peasant extends Unit {
    public Peasant(int x, int y, Name name) {
        super(x, y, name);
        setBaseParameters(10, 0, 1, 1, 5, 0);
    }

    @Override
    public void step(ArrayList<Unit> targets) {
        System.out.println("\nХодит " + this);
    }

    @Override
    public String toString() {
        return "Крестьянин " + name + ", запас здоровья: " + hp;
    }
}
