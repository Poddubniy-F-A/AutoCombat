package Example.units;

public class Magician extends Unit {
    private int mana, maxMana;

    public Magician(int x, int y) {
        super(x, y);

        maxHp = 15;
        hp = maxHp;
        defence = 2;

        attackDistance = 2;
        damageSize = 3;

        speed = 3;

        maxMana = 100;
        mana = maxMana;
    }

    public void useSpell(Unit target) {
        final int spellCost = 25, spellDistance = 4, spellDamage = 25;

        if (checkAlive()) {
            if (mana >= spellCost) {
                if (getDistance(target) <= spellDistance) {
                    if (target.isAlive()) {
                        if (mana >= spellCost) {
                            target.getDamage(spellDamage);
                            mana -= spellCost;

                            showInfo();
                        } else {
                            System.out.println("Недостаточно маны");
                        }
                    } else {
                        System.out.println("Цель уже мертва");
                    }
                } else {
                    System.out.println("До цели слишком далеко");
                }
            } else {
                System.out.println("Недостаточно маны");
            }
        }
    }

    @Override
    public String toString() {
        return "Колдун, запас здоровья: " + hp + ", маны осталось: " + mana;
    }
}
