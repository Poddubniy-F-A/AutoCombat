package Example.units;

public class Priest extends Unit {
    public Priest(int x, int y) {
        super(x, y);

        maxHp = 10;
        hp = maxHp;
        defence = 0;

        attackDistance = 1;
        damageSize = 1;

        speed = 5;
    }

    public void heal(Unit target) {
        final int healDistance = 2, healValue = 5;

        if (checkAlive() && checkDistance(target, healDistance) && checkTargetAlive(target)) {
            target.getHealing(healValue);
        }
    }

    @Override
    public String toString() {
        return "Монах, запас здоровья: " + hp;
    }
}
