package Example.model.units;

import Example.model.Combat;
import Example.model.Name;

import java.util.Random;

public abstract class Shooter extends Unit {
    protected int shots,
            shotDistance, shotDamage;
    protected double shotAccuracy;

    public Shooter(int x, int y, Name name, Combat combat) {
        super(x, y, name, combat);
    }

    protected void setShotParameters(int shots,
                                     int shotDistance, int shotDamage,
                                     double shotAccuracy) {
        this.shots = shots;

        this.shotDistance = shotDistance;
        this.shotDamage = shotDamage;

        this.shotAccuracy = shotAccuracy;
    }

    @Override
    public void step() {
        if (isAlive) {
            System.out.println("Ходит " + this);

            if (shots > 0) {
                Unit nearestTarget = getNearestTarget(getEnemies());
                if (nearestTarget != null) {
                    distAttack(nearestTarget);
                } else {
                    System.out.println("Все противники мертвы");
                }
            } else {
                System.out.println("Кончились снаряды");
            }
        }
    }

    public void distAttack(Unit target) {
        if (checkAlive() && checkDistance(target, shotDistance) && check(shots > 0, "Недостаточно снарядов")) {
            if (new Random().nextDouble() >= (1 - shotAccuracy) * getDistance(target) / shotDistance) {
                System.out.println("Выстрел!");
                target.getDamage(shotDamage);
            } else {
                System.out.println("Промах!");
            }
            shots--;
        }
    }

    public void receiveShot() {
        shots++;
        showInfo();
    }

    public int getShots() {
        return shots;
    }
}
