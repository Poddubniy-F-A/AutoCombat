package Example.view;

import Example.model.Team;
import Example.model.units.Unit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class View {
    private final ArrayList<Unit> leftTeamUnits, rightTeamUnits, allTeamUnits;
    private final HashMap<Unit, Integer> deathTimes;
    private final String leftTeamName, rightTeamName;
    private final int mapSize, teamSize;

    private int step = 0;

    public View(Team leftTeam, Team rightTeam) {
        leftTeamUnits = leftTeam.getUnits();
        rightTeamUnits = rightTeam.getUnits();

        allTeamUnits = new ArrayList<>();
        allTeamUnits.addAll(this.leftTeamUnits);
        allTeamUnits.addAll(this.rightTeamUnits);

        deathTimes = new HashMap<>();

        leftTeamName = leftTeam.getName();
        rightTeamName = rightTeam.getName();

        mapSize = leftTeam.getCombatMapSize();
        teamSize = leftTeamUnits.size();
    }

    private String formatDiv(String str) {
        return str.replace('a', '\u250c')
                .replace('b', '\u252c')
                .replace('c', '\u2510')
                .replace('d', '\u251c')
                .replace('e', '\u253c')
                .replace('f', '\u2524')
                .replace('g', '\u2514')
                .replace('h', '\u2534')
                .replace('i', '\u2518')
                .replace('-', '\u2500');
    }

    public void view() {
        String top = formatDiv("a") + String.join("", Collections.nCopies(mapSize - 1, formatDiv("-b"))) + formatDiv("-c"),
                midl = formatDiv("d") + String.join("", Collections.nCopies(mapSize - 1, formatDiv("-e"))) + formatDiv("-f"),
                bottom = formatDiv("g") + String.join("", Collections.nCopies(mapSize - 1, formatDiv("-h"))) + formatDiv("-i");
        int[] l = {0};

        System.out.print(AnsiColors.ANSI_YELLOW + "\nХод " + ++step + AnsiColors.ANSI_RESET);
        allTeamUnits.forEach((v) -> l[0] = Math.max(l[0], v.toString().length()));

        System.out.println("_".repeat(l[0] * 2));

        System.out.print(top);
        System.out.print("\t" + leftTeamName);
        System.out.print(" ".repeat(l[0] - leftTeamName.length()));
        System.out.println(":\t" + rightTeamName);

        for (Unit unit : allTeamUnits) {
            if (!unit.isAlive() && !deathTimes.containsKey(unit)) {
                deathTimes.put(unit, step);
            }
        }

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                System.out.print(getChar(i, j));
            }
            System.out.print("|\t");
            if (i < teamSize) {
                System.out.print(leftTeamUnits.get(i));
                tabSetter(leftTeamUnits.get(i).toString().length(), l[0]);
                System.out.println(rightTeamUnits.get(i));
            } else {
                System.out.println();
            }
            if (i < mapSize - 1) {
                System.out.println(midl);
            } else {
                System.out.println(bottom);
            }
        }
    }

    private String getChar(int x, int y) {
        String out = "| ";
        int maxDeathTime = 0;
        for (Unit unit : allTeamUnits) {
            if (unit.getField().getX() == x && unit.getField().getY() == y) {
                if (!unit.isAlive()) {
                    int unitDeathTime = deathTimes.get(unit);
                    if (unitDeathTime > maxDeathTime) {
                        out = "|" + AnsiColors.ANSI_RED + unit.toString().charAt(0) + AnsiColors.ANSI_RESET;
                        maxDeathTime = unitDeathTime;
                    }
                } else {
                    out = "|";
                    if (leftTeamUnits.contains(unit)) {
                        out += AnsiColors.ANSI_BLUE;
                    } else {
                        out += AnsiColors.ANSI_GREEN;
                    }
                    out += unit.toString().charAt(0) + AnsiColors.ANSI_RESET;
                    break;
                }
            }
        }

        return out;
    }

    private void tabSetter(int cnt, int max) {
        int dif = max - cnt + 2;
        if (dif > 0) {
            System.out.printf("%" + dif + "s", ":\t");
        } else {
            System.out.print(":\t");
        }
    }
}
