package Example.units;

public class Spearman extends Unit {
    public Spearman(int x, int y) {
        super(x, y);

        maxHp = 20;
        hp = maxHp;
        defence = 5;

        attackDistance = 2;
        damageSize = 5;

        speed = 4;
    }

    @Override
    public String toString() {
        return "Копейщик, запас здоровья: " + hp;
    }
}
