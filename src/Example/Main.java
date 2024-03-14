package Example;

import Example.model.Combat;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int mapSize = inputMapSize();
        new Combat(mapSize, inputTeamSize(mapSize)).emulateCombat();
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

    private static int inputTeamSize(int mapSize) {
        System.out.print("\nВведите размер команды: ");
        int result = scanner.nextInt();
        if (result <= 0) {
            System.out.println("Размер команды должен быть натуральным числом");
            return inputTeamSize(mapSize);
        } else if (result > mapSize) {
            System.out.println("Размер команды не может быть больше размеров поля");
            return inputTeamSize(mapSize);
        } else {
            return result;
        }
    }
}
