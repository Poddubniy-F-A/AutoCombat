package Example.model.units;

import Example.model.Name;
import Example.model.Team;
import Example.model.metric.Field;
import Example.model.metric.StepsMap;

import java.util.ArrayList;

public abstract class MeleeUnit extends Unit {
    public MeleeUnit(int x, int y, Name name, Team team) {
        super(x, y, name, team);
    }

    @Override
    public void step() {
        if (isAlive) {
            System.out.println("\nХодит " + this);

            Unit nearestTarget = getNearestTarget(team.getOpponents());
            if (nearestTarget != null) {
                if (getDistance(nearestTarget) <= maxAttackDistance) {
                    baseAttack(nearestTarget);
                } else {
                    Field field = getPreferredField();
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

    private Field getPreferredField() {
        ArrayList<Field> occupiedFields = new ArrayList<>(), enemiesFields = getFieldsOccupiedBy(team.getOpponents());
        occupiedFields.addAll(getFieldsOccupiedBy(team.getUnits()));
        occupiedFields.addAll(enemiesFields);

        StepsMap stepsMap = new StepsMap(field, team.getCombatMapSize(), occupiedFields);
        //stepsMap.showMap();

        Field nearestFieldForAttack = null;
        for (Field enemyField : enemiesFields) {
            Field nearestFieldForEnemyAttack = null;
            for (Field f : stepsMap.getFreeFieldsAround(enemyField, maxAttackDistance)) {
                nearestFieldForEnemyAttack = stepsMap.chooseNearestFromEasiestReachable(nearestFieldForEnemyAttack, f);
            }

            nearestFieldForAttack = stepsMap.chooseNearestFromEasiestReachable(nearestFieldForAttack, nearestFieldForEnemyAttack);
        }

        Field result = null;
        if (nearestFieldForAttack != null) {
            for (Field f : stepsMap.getEasiestReachableFieldsOfWaysTo(nearestFieldForAttack, speed)) {
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
}
