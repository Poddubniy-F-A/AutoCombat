package Example.model.units;

import Example.model.Combat;
import Example.model.Name;

import java.util.Random;

public abstract class Shooter extends Attacker {
    protected int shots, shotDistance;
    protected double shotAccuracy;

    public Shooter(int x, int y, Name name, Combat combat, int maxHp, int defence, int initiative, int damageSize,
                   int shots, int shotDistance, double shotAccuracy) throws tooBigMapException {
        super(x, y, name, combat, maxHp, defence, initiative, damageSize);

        if (combat.getMapSize() * Math.sqrt(2) > shotDistance) {
            throw new tooBigMapException();
        } else {
            this.shots = shots;
            this.shotDistance = shotDistance;
            this.shotAccuracy = shotAccuracy;
        }
    }

    @Override
    public void step() {
        if (isAlive) {
            System.out.println("Ходит " + this);

            if (shots > 0) {
                Unit target = getNearestTarget();
                if (target != null) {
                    if (new Random().nextDouble() >= (1 - shotAccuracy) * getDistance(target) / shotDistance) {
                        System.out.println("Выстрел!");
                        target.getDamage(damageSize);
                    } else {
                        System.out.println("Промах!");
                    }
                    shots--;
                } else {
                    System.out.println("Все противники мертвы");
                }
            } else {
                System.out.println("Кончились снаряды");
            }
        }
    }

    public void receiveShot() {
        shots++;
        showInfo();
    }

    public int getShots() {
        return shots;
    }

    public static class tooBigMapException extends Exception {}
}
