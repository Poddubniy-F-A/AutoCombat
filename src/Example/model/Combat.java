package Example.model;

import Example.model.units.Unit;
import Example.view.View;

import java.util.ArrayList;
import java.util.Iterator;

public class Combat {
    private final int mapSize;
    private final Team leftTeam, rightTeam;

    public Combat(int mapSize, int teamSize) {
        this.mapSize = mapSize;

        leftTeam = new Team(this, teamSize, 0);
        rightTeam = new Team(this, teamSize, mapSize - 1);
    }

    public void emulateCombat() {
        ArrayList<Unit> units = new ArrayList<>();
        units.addAll(leftTeam.getUnits());
        units.addAll(rightTeam.getUnits());
        units.sort((o1, o2) -> o2.getInitiative() - o1.getInitiative());

        System.out.println("\nСражение");
        View view = new View(leftTeam, rightTeam);

        Iterator<Unit> it = units.iterator();
        while (true) {
            view.view();

            Unit unit;
            do {
                if (!it.hasNext()) {
                    it = units.iterator();
                }
                unit = it.next();
            } while (!unit.isAlive());

            unit.step();

            if (leftTeam.isDead()) {
                System.out.println("\nПобедили " + rightTeam.getName());
                break;
            } else if (rightTeam.isDead()) {
                System.out.println("\nПобедили " + leftTeam.getName());
                break;
            }
        }
    }

    public Team getOpponentsOf(Team team) {
        if (team == leftTeam) {
            return rightTeam;
        } else if (team == rightTeam) {
            return leftTeam;
        } else {
            return null;
        }
    }

    public int getMapSize() {
        return mapSize;
    }
}
