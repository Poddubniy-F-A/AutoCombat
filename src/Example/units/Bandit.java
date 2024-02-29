package Example.units;

import Example.Name;

public class Bandit extends Unit {
    public Bandit(int x, int y, Name name) {
        super(x, y, name);

        maxHp = 15;
        hp = maxHp;
        defence = 2;

        attackDistance = 1;
        damageSize = 3;

        speed = 5;
    }

    @Override
    public String toString() {
        return "Разбойник " + name + ", запас здоровья: " + hp;
    }
}
