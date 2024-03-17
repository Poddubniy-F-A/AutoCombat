package Example.model;

import Example.model.units.Healer;
import Example.model.units.MeleeUnit;
import Example.model.units.Shooter;
import Example.model.units.Unit;
import Example.model.units.instances.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Team {
    private final String name;
    private final ArrayList<Unit> units;

    public Team(Combat combat, int size, int y) {
        name = inputName();
        units = inputTeam(size, y, combat);
    }

    private String inputName() {
        System.out.print("\nВведите название команды: ");
        return new Scanner(System.in).next();
    }

    private ArrayList<Unit> inputTeam(int size, int y, Combat combat) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Unit> result = new ArrayList<>();
        for (int x = 0; x < size; x++) {
            result.add(inputUnit(x, y, scanner, combat));
        }

        return result;
    }

    private Unit inputUnit(int x, int y, Scanner scanner, Combat combat) {
        final String PEASANT_CODE = "Оруженосец",
                SPEARMAN_CODE = "Копейщик",
                BANDIT_CODE = "Разбойник",
                MAGICIAN_CODE = "Волшебник",
                PRIEST_CODE = "Монах",
                CROSSBOWMAN_CODE = "Арбалетчик",
                SNIPER_CODE = "Снайпер";

        System.out.println("Введите тип добавляемого на (" + x + ", " + y + ") персонажа");
        switch (scanner.next()) {
            case PEASANT_CODE -> {
                return new Henchman(x, y, generateName(), combat);
            }
            case SPEARMAN_CODE -> {
                return new Spearman(x, y, generateName(), combat);
            }
            case BANDIT_CODE -> {
                return new Bandit(x, y, generateName(), combat);
            }
            case MAGICIAN_CODE -> {
                return new Magician(x, y, generateName(), combat);
            }
            case PRIEST_CODE -> {
                return new Priest(x, y, generateName(), combat);
            }
            case CROSSBOWMAN_CODE -> {
                return new Crossbowman(x, y, generateName(), combat);
            }
            case SNIPER_CODE -> {
                return new Sniper(x, y, generateName(), combat);
            }
            default -> {
                System.out.println("Введено некорректное наименование типа персонажа");
                return inputUnit(x, y, scanner, combat);
            }
        }
    }

    private Name generateName() {
        final Name[] names = Name.values();

        return names[new Random().nextInt(names.length)];
    }

    public boolean isNotAbleToContinueCombat() {
        boolean existAliveHealer = false;
        for (Unit unit : units) {
            if (unit instanceof Healer && unit.isAlive()) {
                existAliveHealer = true;
                break;
            }
        }
        boolean existHenchman = false;
        for (Unit unit : units) {
            if (unit instanceof Henchman && (unit.isAlive() || (existAliveHealer && unit.isRevivable()))) {
                existHenchman = true;
                break;
            }
        }

        for (Unit unit : units) {
            if ((unit.isAlive() || (existAliveHealer && unit.isRevivable())) &&
                    (unit instanceof MeleeUnit ||
                            (unit instanceof Shooter && (((Shooter) unit).getShots() > 0 || existHenchman)))) {
                return false;
            }
        }
        return true;
    }

    public boolean isDead() {
        for (Unit unit : units) {
            if (unit.isAlive()) {
                return false;
            }
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }
}
