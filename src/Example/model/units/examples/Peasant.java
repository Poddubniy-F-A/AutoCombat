package Example.model.units.examples;

import Example.model.Name;
import Example.model.Team;
import Example.model.units.MeleeUnit;

public class Peasant extends MeleeUnit {
    public Peasant(int x, int y, Name name, Team team) {
        super(x, y, name, team);
        setBaseParameters(10, 0, 1, 1, 5, 0);
    }

    @Override
    public String toString() {
        return "Крестьянин " + name + ", запас здоровья: " + hp;
    }
}
