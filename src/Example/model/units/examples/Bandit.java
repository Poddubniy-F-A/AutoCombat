package Example.model.units.examples;

import Example.model.Name;
import Example.model.Team;
import Example.model.units.MeleeUnit;

public class Bandit extends MeleeUnit {
    public Bandit(int x, int y, Name name, Team team) {
        super(x, y, name, team);
        setBaseParameters(15, 2, 1, 3, 5, 2);
    }

    @Override
    public String toString() {
        return "Разбойник " + name + ", запас здоровья: " + hp;
    }
}
