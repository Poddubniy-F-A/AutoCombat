package Example.units;

import java.util.Random;

public class Crossbowman extends Unit implements Shooter{
    protected int shots;

    public Crossbowman(int x, int y) {
        super(x, y);

        maxHp = 10;
        hp = maxHp;
        defence = 2;

        attackDistance = 1;
        damageSize = 2;

        speed = 3;

        shots = 10;
    }

    @Override
    public void distAttack(Unit target) {
        final int shotDistance = 10, shotDamage = 5;
        final double shotAccuracy = 0.5;

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

    @Override
    public String toString() {
        return "Арбалетчик, запас здоровья: " + hp + ", выстрелов осталось: " + shots;
    }
}
