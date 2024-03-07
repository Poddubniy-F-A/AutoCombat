package Example.units;

import java.util.ArrayList;

public interface Stepable {
    void step(ArrayList<Unit> allies, ArrayList<Unit> enemies);
}
