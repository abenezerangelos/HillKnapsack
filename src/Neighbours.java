import java.util.ArrayList;
import java.util.List;

public class Neighbours {

    public List<State> getNeighbours(State s) {
        ArrayList<State> result = new ArrayList<>();
        boolean[] pickups = s.getPickups();

        // Add or Reduce items
        for (int i = 0; i < pickups.length; i++) {
            State c = s.clone();
            c.getPickups()[i] = !c.getPickups()[i]; // Add or remove ith item
            result.add(c);
        }

        // Swap two items
        for (int i = 0; i < pickups.length; i++) {
            for (int j = i+1; j < pickups.length; j++) {
                if(pickups[i] != pickups[j]) {
                    State c = s.clone();
                    c.getPickups()[i] = !c.getPickups()[i];
                    c.getPickups()[j] = !c.getPickups()[j];
                    result.add(c);
                }
            }
        }

        return result;
    }

}
