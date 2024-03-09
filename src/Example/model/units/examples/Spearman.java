package Example.model.units.examples;

import Example.model.Name;
import Example.model.Team;
import Example.model.units.MeleeUnit;

public class Spearman extends MeleeUnit {
    public Spearman(int x, int y, Name name, Team team) {
        super(x, y, name, team);
        setBaseParameters(20, 4, 2, 5, 4, 2);
    }

    @Override
    public String toString() {
        return "Копейщик " + name + ", запас здоровья: " + hp;
    }
}
