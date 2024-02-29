package Example.units;

import Example.Name;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public abstract class Shooter extends Unit {
    protected int shots,
            shotDistance, shotDamage;
    protected double shotAccuracy;

    public Shooter(int x, int y, Name name) {
        super(x, y, name);
    }

    public Unit getNearestTarget(ArrayList<Unit> targets) {
        Map<Double, Unit> distances = new HashMap<>();
        double minDist = Double.MAX_VALUE;

        for (Unit target: targets) {
            double dist = getDistance(target);

            distances.put(dist, target);
            minDist = Double.min(minDist, dist);
        }

        return distances.get(minDist);
    }

    public void distAttack(Unit target) {
        if (checkAlive() && checkDistance(target, shotDistance) && checkTargetAlive(target)) {
            if (shots > 0) {
                if (new Random().nextDouble() >= (1 - shotAccuracy) * getDistance(target) / shotDistance) {
                    target.getDamage(shotDamage);
                } else {
                    System.out.println("Промах!");
                }
                shots--;

                showInfo();
            } else {
                System.out.println("Недостаточно снарядов");
            }
        }
    }
}
