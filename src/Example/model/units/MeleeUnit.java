package Example.model.units;

import Example.model.Combat;
import Example.model.Name;
import Example.model.metric.Field;
import Example.model.metric.StepsMap;

import java.util.ArrayList;

public abstract class MeleeUnit extends Attacker {
    private final int speed;

    public MeleeUnit(int x, int y, Name name, Combat combat, int maxHp, int defence, int initiative, int damageSize,
                     int speed) {
        super(x, y, name, combat, maxHp, defence, initiative, damageSize);
        this.speed = speed;
    }

    @Override
    public void step() {
        if (isAlive) {
            System.out.println("Ходит " + this);

            Unit target = getNearestEnemy();
            if (target != null) {
                if (getDistance(target) == 1) {
                    System.out.println("Атака!");
                    attack(target);
                } else {
                    Field field = getPreferredField();
                    if (field != null) {
                        int x = field.getX(), y = field.getY();

                        System.out.println("Перемещается в " + x + ", " + y);
                        this.field.setX(x);
                        this.field.setY(y);
                    } else {
                        System.out.println("Ни до одного противника невозможно добраться");
                    }
                }
            } else {
                System.out.println("Все противники мертвы");
            }
        }
    }

    private Field getPreferredField() {
        ArrayList<Field> occupiedFields = new ArrayList<>(), enemiesFields = getFieldsOccupiedBy(getEnemies());
        occupiedFields.addAll(getFieldsOccupiedBy(getAllies()));
        occupiedFields.addAll(enemiesFields);

        StepsMap stepsMap = new StepsMap(field, combat.getMapSize(), occupiedFields);

        Field nearestFieldForAttack = null;
        for (Field enemyField : enemiesFields) {
            Field nearestFieldForEnemyAttack = null;
            for (Field f : stepsMap.getReachableFieldsAround(enemyField)) {
                nearestFieldForEnemyAttack = nearestFieldForEnemyAttack == null ?
                        f : stepsMap.chooseNearestToFromEasiestReachable(nearestFieldForEnemyAttack, f, enemyField);
            }

            if (nearestFieldForEnemyAttack != null) {
                nearestFieldForAttack = nearestFieldForAttack == null ?
                        nearestFieldForEnemyAttack : stepsMap.chooseEasiestReachable(nearestFieldForAttack, nearestFieldForEnemyAttack);
            }
        }

        Field result = null;
        if (nearestFieldForAttack != null) {
            for (Field f : stepsMap.getEasiestReachableFieldsOfWaysTo(nearestFieldForAttack, speed)) {
                result = result == null ?
                        f : stepsMap.chooseNearestToFromEasiestReachable(result, f, nearestFieldForAttack);
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
}
