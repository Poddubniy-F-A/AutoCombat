package Example.units;

import Example.Name;

public class Spearman extends Unit {
    public Spearman(int x, int y, Name name) {
        super(x, y, name);

        maxHp = 20;
        hp = maxHp;
        defence = 5;

        attackDistance = 2;
        damageSize = 5;

        speed = 4;
    }

    @Override
    public String toString() {
        return "Копейщик " + name + ", запас здоровья: " + hp;
    }
}
