package Example.units;

public class Peasant extends Unit {
    public Peasant(int x, int y) {
        super(x, y);

        maxHp = 10;
        hp = maxHp;
        defence = 0;

        attackDistance = 1;
        damageSize = 1;

        speed = 5;
    }

    @Override
    public String toString() {
        return "Крестьянин, запас здоровья: " + hp;
    }
}
