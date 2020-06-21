import java.util.Arrays;

public class State {

    private boolean[] pickups;
    public State(boolean[] pickups) {
        this.pickups = pickups;
    }

    public boolean[] getPickups() {return pickups;}

    public State clone() {
        return new State(Arrays.copyOf(pickups,pickups.length));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pickups.length; i++) {
            if(pickups[i]) sb.append("1 ");
            else sb.append("0 ");
        }
        return sb.toString();
    }

}