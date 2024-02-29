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

        System.out.println("\nКоманда сил света");
        for (int i = 0; i < TEAM_SIZE; i++) {
            team1.add(inputUnit(i, 0));
        }
        System.out.println("\nКоманда сил тьмы");
        for (int i = 0; i < TEAM_SIZE; i++) {
            team2.add(inputUnit(i, MAP_SIZE - 1));
        }

        for (Unit unit: team1) {
            if (unit instanceof Shooter) {
                ((Shooter) unit).distAttack(((Shooter) unit).getNearestTarget(team2));
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
