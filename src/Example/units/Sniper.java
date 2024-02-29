package Example.units;

import Example.Name;

public class Sniper extends Shooter {
    protected int shots;

    public Sniper(int x, int y, Name name) {
        super(x, y, name);

        maxHp = 10;
        hp = maxHp;
        defence = 2;

        attackDistance = 1;
        damageSize = 2;

        speed = 3;

        shotDistance = 10;
        shotDamage = 5;
        shotAccuracy = 1.0;
    }

    @Override
    public String toString() {
        return "Снайпер " + name + ", запас здоровья: " + hp + ", выстрелов осталось: " + shots;
    }
}
