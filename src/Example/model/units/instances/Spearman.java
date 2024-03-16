package Example.model.units.instances;

import Example.model.Combat;
import Example.model.Name;
import Example.model.units.MeleeUnit;

public class Spearman extends MeleeUnit {
    public Spearman(int x, int y, Name name, Combat combat) {
        super(x, y, name, combat);
        setBaseParameters(15, 2, 2, 8, 3, 2);
    }

    @Override
    public String toString() {
        return "Копейщик " + name + ", запас здоровья: " + hp;
    }
}
