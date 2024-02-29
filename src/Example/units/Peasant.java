package Example.units;

import Example.Name;

public class Peasant extends Unit {
    public Peasant(int x, int y, Name name) {
        super(x, y, name);

        maxHp = 10;
        hp = maxHp;
        defence = 0;

        attackDistance = 1;
        damageSize = 1;

        speed = 5;
    }

    @Override
    public String toString() {
        return "Крестьянин " + name + ", запас здоровья: " + hp;
    }
}
