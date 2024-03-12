package Example.model.units.examples;

import Example.model.Combat;
import Example.model.Name;
import Example.model.units.MeleeUnit;

public class Spearman extends MeleeUnit {
    public Spearman(int x, int y, Name name, Combat combat) {
        super(x, y, name, combat);
        setBaseParameters(20, 4, 2, 5, 4, 2);
    }

    @Override
    public String toString() {
        return "Копейщик " + name + ", запас здоровья: " + hp;
    }
}
