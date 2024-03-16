package Example.model.units;

import Example.model.Combat;
import Example.model.Name;

import java.util.ArrayList;

public abstract class Attacker extends Unit {
    protected Attacker(int x, int y, Name name, Combat combat) {
        super(x, y, name, combat);
    }

    protected Unit getNearestTarget(ArrayList<Unit> targets) {
        Unit nearestTarget = null;
        double minDist = Double.MAX_VALUE;

        for (Unit target : targets) {
            if (target.isAlive()) {
                double dist = getDistance(target);

                if (minDist > dist) {
                    nearestTarget = target;
                    minDist = dist;
                }
            }
        }

        return nearestTarget;
    }
}
