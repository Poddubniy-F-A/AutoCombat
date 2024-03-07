package Example.units;

import Example.Name;

public class Spearman extends MeleeUnit {
    public Spearman(int x, int y, Name name) {
        super(x, y, name);
        setBaseParameters(20, 4, 2, 5, 4, 2);
    }

    @Override
    public String toString() {
        return "Копейщик " + name + ", запас здоровья: " + hp;
    }
}
