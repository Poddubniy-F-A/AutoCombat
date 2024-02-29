package Example;

import Example.units.*;

import java.util.Random;

public class Main {
    private static final Name[] names = Name.values();

    public static void main(String[] args) {
        Peasant peasant = new Peasant(1, 1, getName());
        Bandit bandit = new Bandit(1, 2, getName());
        Spearman spearman = new Spearman(0, 2, getName());
        Magician magician = new Magician(0, 3, getName());
        Priest priest = new Priest(2, 2, getName());
        Crossbowman crossbowman = new Crossbowman(5, 0, getName());
        Sniper sniper = new Sniper(16, 0, getName());

        peasant.showInfo();
        bandit.showInfo();
        spearman.showInfo();
        magician.showInfo();
        priest.showInfo();
        crossbowman.showInfo();
        sniper.showInfo();

        System.out.println("---Сражение---");

        peasant.baseAttack(bandit);
        bandit.baseAttack(peasant);
        spearman.baseAttack(peasant);
        magician.useSpell(bandit);
        priest.heal(peasant);
        crossbowman.distAttack(priest);
        sniper.distAttack(crossbowman);
        sniper.changeLocation(15, 0);
        sniper.distAttack(crossbowman);
    }

    private static Name getName() {
        return names[new Random().nextInt(names.length)];
    }
}
