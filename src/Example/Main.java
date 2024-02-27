package Example;

import Example.units.*;

public class Main {

    public static void main(String[] args) {
        Peasant peasant = new Peasant(1, 1);
        Bandit bandit = new Bandit(1, 2);
        Spearman spearman = new Spearman(0, 2);
        Magician magician = new Magician(0, 3);
        Priest priest = new Priest(2, 2);
        Crossbowman crossbowman = new Crossbowman(5, 0);
        Sniper sniper = new Sniper(16, 0);

        peasant.showInfo();
        bandit.showInfo();
        spearman.showInfo();
        magician.showInfo();
        priest.showInfo();
        crossbowman.showInfo();
        sniper.showInfo();

        System.out.println("---Сражение---");

        peasant.attack(bandit);
        bandit.attack(peasant);
        spearman.attack(peasant);
        magician.useSpell(bandit);
        priest.heal(peasant);
        crossbowman.distAttack(priest);
        sniper.distAttack(crossbowman);
        sniper.changeLocation(15, 0);
        sniper.distAttack(crossbowman);
    }
}
