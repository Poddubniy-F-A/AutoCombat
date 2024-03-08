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
        ArrayList<Field> occupiedFields = new ArrayList<>(), enemiesFields = getFieldsOccupiedBy(enemies);
        occupiedFields.addAll(getFieldsOccupiedBy(allies));
        occupiedFields.addAll(enemiesFields);

        StepsMap stepsMap = new StepsMap(occupiedFields, field);
        stepsMap.showMap();

        Field nearestFieldForAttack = null;
        for (Field enemyField : enemiesFields) {
            Field nearestFieldForEnemyAttack = null;
            for (Field f : getFieldsSuitableAttackTo(enemyField, occupiedFields)) {
                nearestFieldForEnemyAttack = stepsMap.chooseNearestFromEasiestReachable(nearestFieldForEnemyAttack, f);
            }

            nearestFieldForAttack = stepsMap.chooseNearestFromEasiestReachable(nearestFieldForAttack, nearestFieldForEnemyAttack);
        }

        Field result = null;
        if (nearestFieldForAttack != null) {
            for (Field f : stepsMap.getEasiestReachableFieldsOfWaysIn(nearestFieldForAttack, speed)) {
                result = stepsMap.chooseNearestToFromEasiestReachable(result, f, nearestFieldForAttack);
            }
        }

        return result;
    }

    private ArrayList<Field> getFieldsOccupiedBy(ArrayList<Unit> units) {
        ArrayList<Field> result = new ArrayList<>();
        for (Unit unit : units) {
            if (unit.isAlive()) {
                result.add(unit.getField());
            }
        }

        return result;
    }

    private ArrayList<Field> getFieldsSuitableAttackTo(Field target, ArrayList<Field> occupiedFields) {
        ArrayList<Field> result = new ArrayList<>();
        int x = target.getX(), y = target.getY();
        for (int newX = Math.max(0, x - maxAttackDistance); newX <= Math.min(Main.MAP_SIZE - 1, x + maxAttackDistance); newX++) {
            for (int newY = Math.max(0, y - maxAttackDistance); newY <= Math.min(Main.MAP_SIZE - 1, y + maxAttackDistance); newY++) {
                Field f = new Field(newX, newY);
                if (!f.isInArray(occupiedFields) && f.getDistance(target) <= maxAttackDistance) {
                    result.add(f);
                }
            }
        }

        return result;
    }
}
