import java.util.List;

public class HillClimbing {

    Neighbours neighboursGenerator;
    Evaluator evaluator;
    State initialState;

    public HillClimbing(Neighbours n, Evaluator e, State initialState) {
        this.neighboursGenerator = n;
        this.evaluator = e;
        this.initialState = initialState;
    }

    public State climb() {
        State currentState = initialState;
        while(true) {
            List<State> neighbours = neighboursGenerator.getNeighbours(currentState);
            int nextEval = Integer.MIN_VALUE;
            State nextState = null;
            for(State neighbour : neighbours) {
                int eval = evaluator.eval(neighbour);
                if (eval > nextEval) {
                    nextEval = eval;
                    nextState = neighbour;
                }
            }
            if (nextEval <= evaluator.eval(currentState)) {
                //Return current state since no better neighbors exist
                System.out.println("[HC] no better neighbour!");
                return currentState;
            }
            System.out.println("[HC] updating state (Eval: " + nextEval + ")");
            currentState = nextState;
        }
    }

}
