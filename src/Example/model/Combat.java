package Example.model;

import Example.model.units.Unit;
import Example.view.View;

import java.util.ArrayList;

public class Combat {
    private final int mapSize, teamSize;
    private final Team leftTeam, rightTeam;

    private int step = 0;

    public Combat(int mapSize, int teamSize) {
        this.mapSize = mapSize;
        this.teamSize = teamSize;

        leftTeam = new Team(this, teamSize, 0);
        rightTeam = new Team(this, teamSize, mapSize - 1);
    }

    public void emulateCombat() {
        ArrayList<Unit> units = new ArrayList<>();
        units.addAll(leftTeam.getUnits());
        units.addAll(rightTeam.getUnits());
        units.sort((o1, o2) -> o2.getInitiative() - o1.getInitiative());

        System.out.println("\nСражение");
        View view = new View(leftTeam, rightTeam, teamSize, mapSize);

        int i = 0;
        while (true) {
            view.view(step++);

            if (leftTeam.isDead()) {
                System.out.println("\nПобедили " + rightTeam.getName());
                break;
            } else if (rightTeam.isDead()) {
                System.out.println("\nПобедили " + leftTeam.getName());
                break;
            } else if (leftTeam.isNotAbleToContinueCombat()) {
                if (rightTeam.isNotAbleToContinueCombat()) {
                    System.out.println("\nНичья");
                } else {
                    System.out.println("\n" + leftTeam.getName() + " сдались");
                }
                break;
            } else if (rightTeam.isNotAbleToContinueCombat()) {
                System.out.println("\n" + rightTeam.getName() + " сдались");
                break;
            }

            Unit unit;
            do {
                if (i == units.size()) {
                    i = 0;
                }
                unit = units.get(i++);
            } while (!unit.isAlive());

            unit.step();
        }
    }

    public ArrayList<Unit> getAlliesOf(Unit unit) {
        if (leftTeam.getUnits().contains(unit)) {
            return leftTeam.getUnits();
        } else if (rightTeam.getUnits().contains(unit)) {
            return rightTeam.getUnits();
        } else {
            return null;
        }
    }

    public ArrayList<Unit> getOpponentsOf(Unit unit) {
        if (leftTeam.getUnits().contains(unit)) {
            return rightTeam.getUnits();
        } else if (rightTeam.getUnits().contains(unit)) {
            return leftTeam.getUnits();
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
