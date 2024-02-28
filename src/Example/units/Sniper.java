package Example.units;

public class Sniper extends Crossbowman {
    public Sniper(int x, int y) {
        super(x, y);
    }

    @Override
    public void distAttack(Unit target) {
        final int shotDistance = 10, shotDamage = 5;

        if (checkAlive()) {
            if (getDistance(target) <= shotDistance) {
                if (target.isAlive()) {
                    if (shots > 0) {
                        target.getDamage(shotDamage);
                        shots--;

                        showInfo();
                    } else {
                        System.out.println("Недостаточно снарядов");
                    }
                } else {
                    System.out.println("Цель уже мертва");
                }
            } else {
                System.out.println("До цели слишком далеко");
            }
        }
    }

    @Override
    public String toString() {
        return "Снайпер, запас здоровья: " + hp + ", выстрелов осталось: " + shots;
    }
}
