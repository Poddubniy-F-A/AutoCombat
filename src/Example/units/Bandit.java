package Example.units;

import Example.Name;

import java.util.ArrayList;

public class Bandit extends Unit {
    public Bandit(int x, int y, Name name) {
        super(x, y, name);
        setBaseParameters(15, 2, 1, 3, 5, 2);
    }

    @Override
    public void step(ArrayList<Unit> targets) {
        System.out.println("\nХодит " + this);
    }

    @Override
    public String toString() {
        return "Разбойник " + name + ", запас здоровья: " + hp;
    }
}
