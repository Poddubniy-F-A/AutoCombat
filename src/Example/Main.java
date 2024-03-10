package Example;

import Example.model.Combat;

import java.util.Scanner;

public class Main {
    private final static Scanner scanner = new Scanner(System.in);
    private static final int mapSize = inputMapSize(), teamSize = inputTeamSize();

    public static void main(String[] args) {
        new Combat(mapSize, teamSize).emulateCombat();
    }

    private static int inputMapSize() {
        System.out.print("\nВведите размер поля: ");
        int result = scanner.nextInt();
        if (result <= 1) {
            System.out.println("Поле слишком мало, чтобы устраивать на нём сражение");
            return inputMapSize();
        } else {
            return result;
        }
    }

    private static int inputTeamSize() {
        System.out.print("\nВведите размер команды: ");
        int result = scanner.nextInt();
        if (result <= 0) {
            System.out.println("Размер команды должен быть натуральным числом");
            return inputTeamSize();
        } else if (result > mapSize) {
            System.out.println("Размер команды не может быть больше размеров поля");
            return inputTeamSize();
        } else {
            return result;
        }
    }
}
