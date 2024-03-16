package Example.model.units;

import Example.model.Combat;
import Example.model.Name;

public abstract class Attacker extends Unit {
    protected final int damageSize;

    protected Attacker(int x, int y, Name name, Combat combat, int maxHp, int defence, int initiative,
                       int damageSize) {
        super(x, y, name, combat, maxHp, defence, initiative);
        this.damageSize = damageSize;
    }

    protected Unit getNearestEnemy() {
        Unit result = null;
        double resDist = Double.MAX_VALUE;

        for (Unit target : getEnemies()) {
            if (target.isAlive()) {
                double dist = getDistance(target);

                if (resDist > dist) {
                    result = target;
                    resDist = dist;
                }
            }
        }

        return result;
    }

    protected void attack(Unit unit) {
        unit.getDamage(damageSize);
    }
}
