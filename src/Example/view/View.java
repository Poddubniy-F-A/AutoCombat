package Example.view;

import Example.model.units.Unit;

import java.util.ArrayList;
import java.util.Collections;

public class View {
    private final ArrayList<Unit> leftTeam, rightTeam, allTeamUnits;
    private final String leftTeamName, rightTeamName;
    private final int mapSize, teamSize;

    private final String top, midl, bottom;

    public View(ArrayList<Unit> leftTeam, ArrayList<Unit> rightTeam, String leftTeamName, String rightTeamName, int mapSize) {
        this.leftTeam = leftTeam;
        this.rightTeam = rightTeam;

        allTeamUnits = new ArrayList<>();
        allTeamUnits.addAll(this.leftTeam);
        allTeamUnits.addAll(this.rightTeam);

        this.leftTeamName = leftTeamName;
        this.rightTeamName = rightTeamName;

        this.mapSize = mapSize;
        teamSize = leftTeam.size();

        top = formatDiv("a") + String.join("", Collections.nCopies(mapSize - 1, formatDiv("-b"))) + formatDiv("-c");
        midl = formatDiv("d") + String.join("", Collections.nCopies(mapSize - 1, formatDiv("-e"))) + formatDiv("-f");
        bottom = formatDiv("g") + String.join("", Collections.nCopies(mapSize - 1, formatDiv("-h"))) + formatDiv("-i");
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

    public void view(int step) {
        int[] lengths = {0};

        System.out.print(AnsiColors.ANSI_YELLOW + "\nХод " + step + AnsiColors.ANSI_RESET);
        allTeamUnits.forEach((o) -> lengths[0] = Math.max(lengths[0], o.toString().length()));
        System.out.println("_".repeat(lengths[0] * 2));

        System.out.println(top + "\t" + leftTeamName + " ".repeat(lengths[0] - leftTeamName.length()) + ":" + "\t" + rightTeamName);

        for (int i = 0; i < teamSize; i++) {
            printTableString(i);
            System.out.print(leftTeam.get(i));
            tabSetter(leftTeam.get(i).toString().length(), lengths[0]);
            System.out.println(rightTeam.get(i) + "\n" + midl);
        }
        for (int i = teamSize; i < mapSize - 1; i++) {
            printTableString(i);
            System.out.println("\n" + midl);
        }
        printTableString(mapSize - 1);
        System.out.println("\n" + bottom);
    }

    private void printTableString(int i) {
        for (int j = 0; j < mapSize; j++) {
            System.out.print(getChar(i, j));
        }
        System.out.print("|\t");
    }

    private String getChar(int x, int y) {
        String out = "| ";
        int maxDeathTime = 0;
        for (Unit unit : allTeamUnits) {
            if (unit.getField().getX() == x && unit.getField().getY() == y) {
                if (!unit.isAlive()) {
                    int unitDeathTime = unit.getDeathTime();
                    if (unitDeathTime > maxDeathTime) {
                        out = "|" + AnsiColors.ANSI_RED + unit.toString().charAt(0) + AnsiColors.ANSI_RESET;
                        maxDeathTime = unitDeathTime;
                    }
                } else {
                    out = "|";
                    if (leftTeam.contains(unit)) {
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
