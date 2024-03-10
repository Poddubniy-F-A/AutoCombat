package Example.model;

import Example.model.units.Unit;
import Example.model.units.examples.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Team {
    private final Combat combat;
    private final String name;
    private final ArrayList<Unit> units;

    public Team(Combat combat, int size, int y) {
        this.combat = combat;

        Scanner scanner = new Scanner(System.in);

        System.out.print("\nВведите название команды: ");
        name = scanner.next();

        units = new ArrayList<>();
        for (int x = 0; x < size; x++) {
            units.add(inputUnit(x, y, scanner));
        }
    }

    private Unit inputUnit(int x, int y, Scanner scanner) {
        final String PEASANT_CODE = "Крестьянин",
                SPEARMAN_CODE = "Копейщик",
                BANDIT_CODE = "Разбойник",
                MAGICIAN_CODE = "Колдун",
                PRIEST_CODE = "Монах",
                CROSSBOWMAN_CODE = "Арбалетчик",
                SNIPER_CODE = "Снайпер";

        System.out.println("Введите тип добавляемого на (" + x + ", " + y + ") персонажа");
        switch (scanner.next()) {
            case PEASANT_CODE -> {
                return new Peasant(x, y, generateName(), this);
            }
            case SPEARMAN_CODE -> {
                return new Spearman(x, y, generateName(), this);
            }
            case BANDIT_CODE -> {
                return new Bandit(x, y, generateName(), this);
            }
            case MAGICIAN_CODE -> {
                return new Magician(x, y, generateName(), this);
            }
            case PRIEST_CODE -> {
                return new Priest(x, y, generateName(), this);
            }
            case CROSSBOWMAN_CODE -> {
                return new Crossbowman(x, y, generateName(), this);
            }
            case SNIPER_CODE -> {
                return new Sniper(x, y, generateName(), this);
            }
            default -> {
                System.out.println("Введено некорректное наименование типа персонажа");
                return inputUnit(x, y, scanner);
            }
        }
    }

    private Name generateName() {
        final Name[] names = Name.values();

        return names[new Random().nextInt(names.length)];
    }

    public boolean checkTeamAlive() {
        for (Unit unit : units) {
            if (unit.isAlive()) {
                return true;
            }
        }

        System.out.println("\n" + name + " побеждены");
        return false;
    }

    public ArrayList<Unit> getOpponents() {
        return combat.getOpponentsOf(this).getUnits();
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public int getCombatMapSize() {
        return combat.getMapSize();
    }

    public String getName() {
        return name;
    }
}
