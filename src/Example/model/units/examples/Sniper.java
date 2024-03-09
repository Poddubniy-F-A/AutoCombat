package Example.model.units.examples;

import Example.model.Name;
import Example.model.Team;
import Example.model.units.Shooter;

public class Sniper extends Shooter {
    public Sniper(int x, int y, Name name, Team team) {
        super(x, y, name, team);
        setBaseParameters(10, 1, 1, 2, 4, 3);
        setShotParameters(10, 10, 5, 1.0);
    }

    @Override
    public String toString() {
        return "Снайпер " + name + ", запас здоровья: " + hp;
    }
}
