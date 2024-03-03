package Example.units;

import Example.Name;

import java.util.ArrayList;

public class Spearman extends Unit {
    public Spearman(int x, int y, Name name) {
        super(x, y, name);
        setBaseParameters(20, 4, 2, 5, 4, 2);
    }

    @Override
    public void step(ArrayList<Unit> targets) {
        System.out.println("\nХодит " + this);
    }

    @Override
    public String toString() {
        return "Копейщик " + name + ", запас здоровья: " + hp;
    }
}
