package Example.model;

import Example.model.units.Unit;
import Example.model.units.examples.*;
import Example.view.View;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class Combat {
    private final int mapSize;
    private final String leftTeamName, rightTeamName;
    private final ArrayList<Unit> leftTeam, rightTeam;

    private int step = 0;

    public Combat(int mapSize, int teamSize) {
        this.mapSize = mapSize;

        leftTeamName = inputName();
        leftTeam = inputTeam(teamSize, 0);
        rightTeamName = inputName();
        rightTeam = inputTeam(teamSize, mapSize - 1);
    }

    public String inputName() {
        System.out.print("\nВведите название команды: ");
        return new Scanner(System.in).next();
    }

    public ArrayList<Unit> inputTeam(int size, int y) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Unit> result = new ArrayList<>();
        for (int x = 0; x < size; x++) {
            result.add(inputUnit(x, y, scanner));
        }

        return result;
    }

    private Unit inputUnit(int x, int y, Scanner scanner) {
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
                return new Henchman(x, y, generateName(), this);
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

    public void emulateCombat() {
        ArrayList<Unit> units = new ArrayList<>();
        units.addAll(leftTeam);
        units.addAll(rightTeam);
        units.sort((o1, o2) -> o2.getInitiative() - o1.getInitiative());

        System.out.println("\nСражение");
        View view = new View(leftTeam, rightTeam, leftTeamName, rightTeamName, mapSize);

        Iterator<Unit> it = units.iterator();
        while (true) {
            step++;
            view.view(step);

            Unit unit;
            do {
                if (!it.hasNext()) {
                    it = units.iterator();
                }
                unit = it.next();
            } while (!unit.isAlive());

            unit.step();

            if (isDead(leftTeam)) {
                System.out.println("\nПобедили " + rightTeamName);
                break;
            } else if (isDead(rightTeam)) {
                System.out.println("\nПобедили " + leftTeamName);
                break;
            }
        }
    }

    public boolean isDead(ArrayList<Unit> team) {
        for (Unit unit : team) {
            if (unit.isAlive()) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Unit> getAlliesOf(Unit unit) {
        if (leftTeam.contains(unit)) {
            return leftTeam;
        } else if (rightTeam.contains(unit)) {
            return rightTeam;
        } else {
            return null;
        }
    }

    public ArrayList<Unit> getOpponentsOf(Unit unit) {
        if (leftTeam.contains(unit)) {
            return rightTeam;
        } else if (rightTeam.contains(unit)) {
            return leftTeam;
        } else {
            return null;
        }
    }

    public int getMapSize() {
        return mapSize;
    }

    public int getStep() {
        return step;
    }
}
