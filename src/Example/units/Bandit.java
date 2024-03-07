package Example.units;

import Example.Name;

public class Bandit extends MeleeUnit {
    public Bandit(int x, int y, Name name) {
        super(x, y, name);
        setBaseParameters(15, 2, 1, 3, 5, 2);
    }

    @Override
    public String toString() {
        return "Разбойник " + name + ", запас здоровья: " + hp;
    }
}
