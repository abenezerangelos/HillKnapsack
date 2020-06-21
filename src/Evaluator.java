public class Evaluator {

    int[] weights;
    int[] values;
    int maxWeight;

    public Evaluator(int[] weights, int[] values, int maxWeight) {
        this.weights = weights;
        this.values = values;
        this.maxWeight = maxWeight;
    }

    public int eval(State s) {
        int totalWeight = 0;
        int totalValue = 0;
        boolean[] pickups = s.getPickups();

        for (int i = 0; i < pickups.length; i++) {
            if(pickups[i]) {
                totalWeight += weights[i];
                totalValue += values[i];
            }
        }

        if(totalWeight > maxWeight) return maxWeight - totalWeight; //weight violation
        return totalValue; //value of the knapsack
    }

}
