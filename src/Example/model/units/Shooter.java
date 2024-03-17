package Example.model.units;

import Example.model.Combat;
import Example.model.Name;

import java.util.Random;

public abstract class Shooter extends Attacker {
    private final double shotAccuracy;
    protected int shots;

    public Shooter(int x, int y, Name name, Combat combat, int maxHp, int defence, int initiative, int damageSize,
                   double shotAccuracy, int shots) {
        super(x, y, name, combat, maxHp, defence, initiative, damageSize);

        this.shotAccuracy = shotAccuracy;
        this.shots = shots;
    }

    @Override
    public void step() {
        if (isAlive) {
            System.out.println("Ходит " + this);

            if (shots > 0) {
                Unit target = getNearestEnemy();
                if (target != null) {
                    if (new Random().nextDouble() >= 1 - shotAccuracy) {
                        System.out.println("Выстрел!");
                        attack(target);
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
}
