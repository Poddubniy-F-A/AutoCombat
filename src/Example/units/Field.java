package Example.units;

import Example.Main;

import java.util.*;

class Field {
    private final int MAP_VOID_CODE = -1, MAP_OBSTACLE_CODE = MAP_VOID_CODE - 1, MAP_SIZE = Main.MAP_SIZE;

    private int x, y;

    public Field(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double getDistance(Field target) {
        return Math.sqrt(Math.pow(x - target.getX(), 2) + Math.pow(y - target.getY(), 2));
    }

    public int[][] getStepsMap(ArrayList<Field> occupiedFields) {
        int[][] res = new int[MAP_SIZE][MAP_SIZE];
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                res[i][j] = MAP_VOID_CODE;
            }
        }

        for (Field f : occupiedFields) {
            res[f.getX()][f.getY()] = MAP_OBSTACLE_CODE;
        }
        res[x][y] = 0;

        fillStepsMap(res, 0);
        return res;
    }

    public void fillStepsMap(int[][] map, int curSteps) {
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
            fillStepsMap(map, nextSteps);
        }
    }

    public ArrayList<Field> getFirstFieldsOfWaysIn(int[][] map, int maxSteps) {
        ArrayList<Field> res = new ArrayList<>();
        fillFirstFieldsOfWaysIn(res, this, map, maxSteps);

        return res;
    }

    public void fillFirstFieldsOfWaysIn(ArrayList<Field> result, Field curField, int[][] map, int maxSteps) {
        int x = curField.getX(), y = curField.getY(), needSteps = map[x][y];

        if (needSteps <= maxSteps) {
            result.add(curField);
        } else {
            if (x > 0 && map[x - 1][y] == needSteps - 1) {
                fillFirstFieldsOfWaysIn(result, new Field(x - 1, y), map, maxSteps);
            }
            if (x < MAP_SIZE && map[x + 1][y] == needSteps - 1) {
                fillFirstFieldsOfWaysIn(result, new Field(x + 1, y), map, maxSteps);
            }
            if (y > 0 && map[x][y - 1] == needSteps - 1) {
                fillFirstFieldsOfWaysIn(result, new Field(x, y - 1), map, maxSteps);
            }
            if (y < MAP_SIZE && map[x][y + 1] == needSteps - 1) {
                fillFirstFieldsOfWaysIn(result, new Field(x, y + 1), map, maxSteps);
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
