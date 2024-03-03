package Example;

import Example.units.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    private static final int TEAM_SIZE = 10, MAP_SIZE = 10;

    public static void main(String[] args) {
        ArrayList<Unit> team1 = new ArrayList<>();
        ArrayList<Unit> team2 = new ArrayList<>();
        ArrayList<Unit> units = new ArrayList<>();

        System.out.println("\nКоманда сил света");
        for (int x = 0; x < TEAM_SIZE; x++) {
            Unit unit = inputUnit(x, 0);

            team1.add(unit);
            units.add(unit);
        }
        System.out.println("\nКоманда сил тьмы");
        for (int x = 0; x < TEAM_SIZE; x++) {
            Unit unit = inputUnit(x, MAP_SIZE - 1);

            team2.add(unit);
            units.add(unit);
        }
        units.sort((o1, o2) -> o2.getInitiative() - o1.getInitiative());

        System.out.println("\nСражение");
        for (Unit unit: units) {
            if (unit instanceof Priest) {
                if (team1.contains(unit)) {
                    unit.step(team1);
                } else {
                    unit.step(team2);
                }
            } else {
                if (team1.contains(unit)) {
                    unit.step(team2);
                } else {
                    unit.step(team1);
                }
            }
        }
    }

    private static Unit inputUnit(int x, int y) {
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
                return new Peasant(x, y, generateName());
            }
            case SPEARMAN_CODE -> {
                return new Spearman(x, y, generateName());
            }
            case BANDIT_CODE -> {
                return new Bandit(x, y, generateName());
            }
            case MAGICIAN_CODE -> {
                return new Magician(x, y, generateName());
            }
            case PRIEST_CODE -> {
                return new Priest(x, y, generateName());
            }
            case CROSSBOWMAN_CODE -> {
                return new Crossbowman(x, y, generateName());
            }
            case SNIPER_CODE -> {
                return new Sniper(x, y, generateName());
            }
            default -> {
                System.out.println("Введено некорректное наименование типа персонажа");
                return inputUnit(x, y);
            }
        }
    }

    private static Name generateName() {
        final Name[] names = Name.values();

        return names[new Random().nextInt(names.length)];
    }
}
