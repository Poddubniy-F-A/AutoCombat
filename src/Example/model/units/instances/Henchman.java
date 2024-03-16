package Example.model.units.instances;

import Example.model.Combat;
import Example.model.Name;
import Example.model.units.Shooter;
import Example.model.units.Unit;

public class Henchman extends Unit {
    public Henchman(int x, int y, Name name, Combat combat) {
        super(x, y, name, combat);
        setBaseParameters(10, 1, 0);
    }

    @Override
    public void step() {
        if (isAlive) {
            System.out.println("Ходит " + this);

            Shooter receiver = getPreferredReceiver();
            if (receiver != null) {
                System.out.println("Пополнение запасов");
                receiver.receiveShot();
            } else {
                System.out.println("Нет живых стрелков");
            }
        }
    }

    @Override
    public String toString() {
        return "Оруженосец " + name + ", запас здоровья: " + hp;
    }

    private Shooter getPreferredReceiver() {
        Shooter result = null;
        int minShots = Integer.MAX_VALUE;
        for (Unit unit : getAllies()) {
            if (unit.isAlive() && unit instanceof Shooter shooter) {
                int shots = shooter.getShots();
                if (result == null || shots < minShots ||
                        (shots == minShots && getDistance(shooter) < getDistance(result))) {
                    result = shooter;
                    minShots = shots;
                }
            }
        }

        return result;
    }
}
