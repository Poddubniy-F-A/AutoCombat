package Example.model;

import Example.model.units.Unit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Combat {
    private final Scanner scanner = new Scanner(System.in);

    private final int teamSize, mapSize;
    private final Team team1, team2;

    public Combat() {
        mapSize = inputMapSize();
        teamSize = inputTeamSize();

        team1 = new Team(this, 0);
        team2 = new Team(this, mapSize - 1);
    }

    private int inputMapSize() {
        System.out.print("\nВведите размер поля: ");
        int result = scanner.nextInt();
        if (result <= 1) {
            System.out.println("Поле слишком мало, чтобы устраивать на нём сражение");
            return inputMapSize();
        } else {
            return result;
        }
    }

    private int inputTeamSize() {
        System.out.print("\nВведите размер команды: ");
        int result = scanner.nextInt();
        if (result <= 0) {
            System.out.println("Размер команды должен быть натуральным числом");
            return inputMapSize();
        } else if (result > mapSize) {
            System.out.println("Размер команды не может быть больше размеров поля");
            return inputMapSize();
        } else {
            return result;
        }
    }

    public void emulateBattle() {
        ArrayList<Unit> units = new ArrayList<>();
        units.addAll(team1.getUnits());
        units.addAll(team2.getUnits());
        units.sort((o1, o2) -> o2.getInitiative() - o1.getInitiative());

        System.out.println("\nСражение");
        Iterator<Unit> it = units.iterator();
        do {
            Unit unit;
            do {
                if (!it.hasNext()) {
                    it = units.iterator();
                }
                unit = it.next();
            } while (!unit.isAlive());

            unit.step();
        } while (team1.checkTeamAlive() && team2.checkTeamAlive());
    }

    public int getMapSize() {
        return mapSize;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public Team getOpponentsOf(Team team) {
        if (team == team1) {
            return team2;
        } else {
            return team1;
        }
    }
}
