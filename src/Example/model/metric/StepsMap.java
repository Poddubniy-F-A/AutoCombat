package Example.model.metric;

import java.util.ArrayList;

public class StepsMap {
    private final int MAP_START_CODE = 0, MAP_VOID_CODE = MAP_START_CODE - 1, MAP_OBSTACLE_CODE = MAP_VOID_CODE - 1;

    private final Field field;
    private final int size;
    private final int[][] map;

    public StepsMap(Field startField, int size, ArrayList<Field> occupiedFields) {
        field = startField;

        this.size = size;
        map = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
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
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j] == curSteps) {
                    if (i > 0 && map[i - 1][j] == MAP_VOID_CODE) {
                        map[i - 1][j] = nextSteps;
                        mapWasUpdated = true;
                    }
                    if (i < size - 1 && map[i + 1][j] == MAP_VOID_CODE) {
                        map[i + 1][j] = nextSteps;
                        mapWasUpdated = true;
                    }
                    if (j > 0 && map[i][j - 1] == MAP_VOID_CODE) {
                        map[i][j - 1] = nextSteps;
                        mapWasUpdated = true;
                    }
                    if (j < size - 1 && map[i][j + 1] == MAP_VOID_CODE) {
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

    public ArrayList<Field> getFreeFieldsAround(Field target, int range) {
        ArrayList<Field> result = new ArrayList<>();
        int x = target.getX(), y = target.getY();
        for (int newX = Math.max(0, x - range); newX <= Math.min(size - 1, x + range); newX++) {
            for (int newY = Math.max(0, y - range); newY <= Math.min(size - 1, y + range); newY++) {
                Field f = new Field(newX, newY);
                if (map[newX][newY] != MAP_OBSTACLE_CODE && f.getDistance(target) <= range) {
                    result.add(f);
                }
            }
        }

        return result;
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

    public ArrayList<Field> getEasiestReachableFieldsOfWaysTo(Field target, int maxSteps) {
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
            if (x < size && map[x + 1][y] == needSteps - 1) {
                fillEasiestReachableFieldsOfWaysTo(new Field(x + 1, y), maxSteps, result);
            }
            if (y > 0 && map[x][y - 1] == needSteps - 1) {
                fillEasiestReachableFieldsOfWaysTo(new Field(x, y - 1), maxSteps, result);
            }
            if (y < size && map[x][y + 1] == needSteps - 1) {
                fillEasiestReachableFieldsOfWaysTo(new Field(x, y + 1), maxSteps, result);
            }
        }
    }

    public void showMap() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j] >= 0 && map[i][j] < 10) {
                    System.out.print(" ");
                }
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
