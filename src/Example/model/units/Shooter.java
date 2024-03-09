package Example.model.units;

import Example.model.Name;
import Example.model.Team;

import java.util.Random;

public abstract class Shooter extends Unit {
    protected int shots,
            shotDistance, shotDamage;
    protected double shotAccuracy;

    public Shooter(int x, int y, Name name, Team team) {
        super(x, y, name, team);
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
            System.out.println("\nХодит " + this + ", выстрелов осталось: " + shots);

            if (shots > 0) {
                Unit nearestTarget = getNearestTarget(team.getOpponents());
                if (nearestTarget != null) {
                    distAttack(nearestTarget);
                } else {
                    System.out.println("Все противники мертвы");
                }
            } else {
                System.out.println("Недостаточно снарядов");
            }
        }
    }

    private void distAttack(Unit target) {
        if (checkDistance(target, shotDistance)) {
            if (new Random().nextDouble() >= (1 - shotAccuracy) * getDistance(target) / shotDistance) {
                System.out.println("Выстрел!");
                target.getDamage(shotDamage);
            } else {
                System.out.println("Промах!");
            }
            shots--;
        }
    }
}
