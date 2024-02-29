package Example.units;

import Example.Name;

public class Crossbowman extends Shooter{
    public Crossbowman(int x, int y, Name name) {
        super(x, y, name);

        maxHp = 10;
        hp = maxHp;
        defence = 2;

        attackDistance = 1;
        damageSize = 2;

        speed = 3;

        shots = 15;
        shotDistance = 10;
        shotDamage = 10;
        shotAccuracy = 0.5;
    }

    @Override
    public String toString() {
        return "Арбалетчик " + name + ", запас здоровья: " + hp + ", выстрелов осталось: " + shots;
    }
}
