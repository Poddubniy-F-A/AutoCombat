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

        fillStepsMap(MAP_START_CODE);
    }

    private void fillStepsMap(int curSteps) {
        int nextSteps = curSteps + 1;

        boolean mapWasUpdated = false;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j] == curSteps) {
                    if (i > 0) {
                        mapWasUpdated = fieldWasVoid(i - 1, j, nextSteps);
                    }
                    if (i < size - 1) {
                        mapWasUpdated = fieldWasVoid(i + 1, j, nextSteps);
                    }
                    if (j > 0) {
                        mapWasUpdated = fieldWasVoid(i, j - 1, nextSteps);
                    }
                    if (j < size - 1) {
                        mapWasUpdated = fieldWasVoid(i, j + 1, nextSteps);
                    }
                }
            }
        }

        if (mapWasUpdated) {
            fillStepsMap(nextSteps);
        }
    }

    private boolean fieldWasVoid(int i, int j, int nextSteps) {
        if (map[i][j] == MAP_VOID_CODE) {
            map[i][j] = nextSteps;
            return true;
        }
        return false;
    }

    public ArrayList<Field> getReachableFieldsAround(Field target) {
        ArrayList<Field> result = new ArrayList<>();
        int x = target.getX(), y = target.getY();

        if (x > 0) {
            addFieldIfItIsReachable(x - 1, y, result);
        }
        if (x < size - 1) {
            addFieldIfItIsReachable(x + 1, y, result);
        }
        if (y > 0) {
            addFieldIfItIsReachable(x, y - 1, result);
        }
        if (y < size - 1) {
            addFieldIfItIsReachable(x, y + 1, result);
        }

        return result;
    }

    private void addFieldIfItIsReachable(int x, int y, ArrayList<Field> result) {
        int needSteps = map[x][y];
        if (needSteps != MAP_OBSTACLE_CODE && needSteps != MAP_VOID_CODE) {
            result.add(new Field(x, y));
        }
    }

    public Field chooseEasiestReachable(Field f1, Field f2) {
        return chooseNearestToFromEasiestReachable(f1, f2, field);
    }

    public Field chooseNearestToFromEasiestReachable(Field f1, Field f2, Field target) {
        int f1NeedSteps = map[f1.getX()][f1.getY()], f2NeedSteps = map[f2.getX()][f2.getY()];

        return f1NeedSteps < f2NeedSteps ||
                (f1NeedSteps == f2NeedSteps && (field.getDistance(f1) < field.getDistance(f2) ||
                        (field.getDistance(f1) == field.getDistance(f2) && target.getDistance(f1) < target.getDistance(f2)))) ?
                f1 : f2;
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
            if (x > 0) {
                continueFillingIfFieldIsTooFar(x - 1, y, needSteps, maxSteps, result);
            }
            if (x < size - 1) {
                continueFillingIfFieldIsTooFar(x + 1, y, needSteps, maxSteps, result);
            }
            if (y > 0) {
                continueFillingIfFieldIsTooFar(x, y - 1, needSteps, maxSteps, result);
            }
            if (y < size - 1) {
                continueFillingIfFieldIsTooFar(x, y + 1, needSteps, maxSteps, result);
            }
        }
    }

    private void continueFillingIfFieldIsTooFar(int x, int y, int needSteps, int maxSteps, ArrayList<Field> result) {
        if (map[x][y] == needSteps - 1) {
            fillEasiestReachableFieldsOfWaysTo(new Field(x, y), maxSteps, result);
        }
    }
}
