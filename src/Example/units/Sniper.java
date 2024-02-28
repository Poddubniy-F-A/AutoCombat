package Example.units;

public class Sniper extends Unit implements Shooter {
    protected int shots;

    public Sniper(int x, int y) {
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

        if (checkAlive() && checkDistance(target, shotDistance) && checkTargetAlive(target)) {
            if (shots > 0) {
                target.getDamage(shotDamage);
                shots--;

                showInfo();
            } else {
                System.out.println("Недостаточно снарядов");
            }
        }
    }

    @Override
    public String toString() {
        return "Снайпер, запас здоровья: " + hp + ", выстрелов осталось: " + shots;
    }
}
