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
                    for (Field f : getFieldsAround(new Field(i, j))) {
                        int x = f.getX(), y = f.getY();
                        if (map[x][y] == MAP_VOID_CODE) {
                            map[x][y] = nextSteps;
                            mapWasUpdated = true;
                        }
                    }
                }
            }
        }

        if (mapWasUpdated) {
            fillStepsMap(nextSteps);
        }
    }

    public ArrayList<Field> getReachableFieldsAround(Field target) {
        ArrayList<Field> result = new ArrayList<>();
        for (Field f : getFieldsAround(target)) {
            int x = f.getX(), y = f.getY(), needSteps = map[x][y];
            if (needSteps != MAP_OBSTACLE_CODE && needSteps != MAP_VOID_CODE) {
                result.add(new Field(x, y));
            }
        }

        return result;
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
        int needSteps = map[curField.getX()][curField.getY()];

        if (needSteps <= maxSteps) {
            result.add(curField);
        } else {
            for (Field f : getFieldsAround(curField)) {
                int x = f.getX(), y = f.getY();
                if (map[x][y] == needSteps - 1) {
                    fillEasiestReachableFieldsOfWaysTo(new Field(x, y), maxSteps, result);
                }
            }
        }
    }

    private ArrayList<Field> getFieldsAround(Field field) {
        ArrayList<Field> result = new ArrayList<>();
        int x = field.getX(), y = field.getY();

        if (x > 0) {
            result.add(new Field(x - 1, y));
        }
        if (x < size - 1) {
            result.add(new Field(x + 1, y));
        }
        if (y > 0) {
            result.add(new Field(x, y - 1));
        }
        if (y < size - 1) {
            result.add(new Field(x, y + 1));
        }

        return result;
    }
}
