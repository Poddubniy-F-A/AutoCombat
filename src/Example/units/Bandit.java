package Example.units;

public class Bandit extends Unit {
    public Bandit(int x, int y) {
        super(x, y);

        maxHp = 15;
        hp = maxHp;
        defence = 2;

        attackDistance = 1;
        damageSize = 3;

        speed = 5;
    }

    @Override
    public String toString() {
        return "Разбойник, запас здоровья: " + hp;
    }
}
