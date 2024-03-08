package Example.units;

import Example.Main;

import java.util.ArrayList;

public class StepsMap {
    private final int MAP_SIZE = Main.MAP_SIZE,
            MAP_START_CODE = 0,
            MAP_VOID_CODE = MAP_START_CODE - 1,
            MAP_OBSTACLE_CODE = MAP_VOID_CODE - 1;

    private final Field field;
    private final int[][] map;

    public StepsMap(ArrayList<Field> occupiedFields, Field startField) {
        field = startField;

        map = new int[MAP_SIZE][MAP_SIZE];

        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                map[i][j] = MAP_VOID_CODE;
            }
        }
        for (Field f : occupiedFields) {
            map[f.getX()][f.getY()] = MAP_OBSTACLE_CODE;
        }
        map[field.getX()][field.getY()] = MAP_START_CODE;

        fillStepsMap(MAP_START_CODE, map);
    }

    private void fillStepsMap(int curSteps, int[][] map) {
        int nextSteps = curSteps + 1;

        boolean mapWasUpdated = false;
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                if (map[i][j] == curSteps) {
                    if (i > 0 && map[i - 1][j] == MAP_VOID_CODE) {
                        map[i - 1][j] = nextSteps;
                        mapWasUpdated = true;
                    }
                    if (i < MAP_SIZE - 1 && map[i + 1][j] == MAP_VOID_CODE) {
                        map[i + 1][j] = nextSteps;
                        mapWasUpdated = true;
                    }
                    if (j > 0 && map[i][j - 1] == MAP_VOID_CODE) {
                        map[i][j - 1] = nextSteps;
                        mapWasUpdated = true;
                    }
                    if (j < MAP_SIZE - 1 && map[i][j + 1] == MAP_VOID_CODE) {
                        map[i][j + 1] = nextSteps;
                        mapWasUpdated = true;
                    }
                }
            }
        }

        if (mapWasUpdated) {
            fillStepsMap(nextSteps, map);
        }
    }

    public Field chooseNearestFromEasiestReachable(Field f1, Field f2) {
        return chooseNearestToFromEasiestReachable(f1, f2, field);
    }

    public Field chooseNearestToFromEasiestReachable(Field f1, Field f2, Field target) {
        if (f1 == null) {
            return f2;
        } else if (f2 == null) {
            return f1;
        } else {
            int f1NeedSteps = map[f1.getX()][f1.getY()], f2NeedSteps = map[f2.getX()][f2.getY()];

            if (f1NeedSteps < f2NeedSteps ||
                    (f1NeedSteps == f2NeedSteps && target.getDistance(f1) < target.getDistance(f2))) {
                return f1;
            } else {
                return f2;
            }
        }
    }

    public ArrayList<Field> getEasiestReachableFieldsOfWaysIn(Field target, int maxSteps) {
        ArrayList<Field> res = new ArrayList<>();
        fillEasiestReachableFieldsOfWaysTo(target, maxSteps, res);

        return res;
    }

    private void fillEasiestReachableFieldsOfWaysTo(Field curField, int maxSteps, ArrayList<Field> result) {
        int x = curField.getX(), y = curField.getY(), needSteps = map[x][y];

        if (needSteps <= maxSteps) {
            result.add(curField);
        } else {
            if (x > 0 && map[x - 1][y] == needSteps - 1) {
                fillEasiestReachableFieldsOfWaysTo(new Field(x - 1, y), maxSteps, result);
            }
            if (x < MAP_SIZE && map[x + 1][y] == needSteps - 1) {
                fillEasiestReachableFieldsOfWaysTo(new Field(x + 1, y), maxSteps, result);
            }
            if (y > 0 && map[x][y - 1] == needSteps - 1) {
                fillEasiestReachableFieldsOfWaysTo(new Field(x, y - 1), maxSteps, result);
            }
            if (y < MAP_SIZE && map[x][y + 1] == needSteps - 1) {
                fillEasiestReachableFieldsOfWaysTo(new Field(x, y + 1), maxSteps, result);
            }
        }
    }

    public void showMap() {
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                if (map[i][j] >= 0 && map[i][j] < 10) {
                    System.out.print(" ");
                }
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
