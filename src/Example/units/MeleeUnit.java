package Example.units;

import Example.Main;
import Example.Name;

import java.util.ArrayList;

public abstract class MeleeUnit extends Unit {
    public MeleeUnit(int x, int y, Name name) {
        super(x, y, name);
    }

    @Override
    public void step(ArrayList<Unit> allies, ArrayList<Unit> enemies) {
        if (isAlive) {
            System.out.println("\nХодит " + this);

            Unit nearestTarget = getNearestTarget(enemies);
            if (nearestTarget != null) {
                if (getDistance(nearestTarget) <= maxAttackDistance) {
                    baseAttack(nearestTarget);
                } else {
                    Field field = getPreferredField(allies, enemies);
                    if (field != null) {
                        changeLocation(field);
                    } else {
                        System.out.println("Ни до одного противника невозможно добраться");
                    }
                }
            } else {
                System.out.println("Все противники мертвы");
            }
        }
    }

    private Field getPreferredField(ArrayList<Unit> allies, ArrayList<Unit> enemies) {
        ArrayList<Field> enemiesFields = getOccupiedFields(enemies), occupiedFields = new ArrayList<>();
        occupiedFields.addAll(getOccupiedFields(allies));
        occupiedFields.addAll(enemiesFields);

        int[][] map = field.getStepsMap(occupiedFields);

        Field nearestFieldForAttack = getNearestFieldForAttack(enemiesFields, occupiedFields, map);

        Field result = null;
        if (nearestFieldForAttack != null) {
            for (Field f : nearestFieldForAttack.getFirstFieldsOfWaysIn(map, speed)) {
                result = getNearestTo(nearestFieldForAttack, result, f, map);
            }
        }

        return result;
    }

    private ArrayList<Field> getOccupiedFields(ArrayList<Unit> units) {
        ArrayList<Field> result = new ArrayList<>();
        for (Unit unit : units) {
            if (unit.isAlive()) {
                result.add(unit.getField());
            }
        }

        return result;
    }

    private Field getNearestFieldForAttack(ArrayList<Field> enemiesFields, ArrayList<Field> occupiedFields, int[][] map) {
        Field result = null;
        for (Field enemyField : enemiesFields) {
            Field bestAttackField = null;
            for (Field f : getAttackSuitableFields(enemyField, occupiedFields)) {
                bestAttackField = getNearestTo(field, bestAttackField, f, map);
            }

            result = getNearestTo(field, result, bestAttackField, map);
        }

        return result;
    }

    private Field getNearestTo(Field field, Field f1, Field f2, int[][] map) {
        if (f1 == null) {
            return f2;
        } else if (f2 == null) {
            return f1;
        } else {
            int f1NeedSteps = map[f1.getX()][f1.getY()], f2NeedSteps = map[f2.getX()][f2.getY()];

            if (f1NeedSteps < f2NeedSteps ||
                    (f1NeedSteps == f2NeedSteps && field.getDistance(f1) < field.getDistance(f2))) {
                return f1;
            } else {
                return f2;
            }
        }
    }

    private ArrayList<Field> getAttackSuitableFields(Field target, ArrayList<Field> occupiedFields) {
        ArrayList<Field> result = new ArrayList<>();

        int x = target.getX(), y = target.getY();
        for (int newX = Math.max(0, x - maxAttackDistance); newX <= Math.min(Main.MAP_SIZE - 1, x + maxAttackDistance); newX++) {
            for (int newY = Math.max(0, y - maxAttackDistance); newY <= Math.min(Main.MAP_SIZE - 1, y + maxAttackDistance); newY++) {
                Field f = new Field(newX, newY);
                if (!arrayContainsField(occupiedFields, f) && f.getDistance(target) <= maxAttackDistance) {
                    result.add(f);
                }
            }
        }

        return result;
    }

    private boolean arrayContainsField(ArrayList<Field> collection, Field field) {
        int x = field.getX(), y = field.getY();

        for (Field f : collection) {
            if (f.getX() == x && f.getY() == y) {
                return true;
            }
        }
        return false;
    }
}
