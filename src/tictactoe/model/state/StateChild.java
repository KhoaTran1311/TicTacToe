package tictactoe.model.state;

public class StateChild {
    private final int[] action;
    private final State state;

    public StateChild(State state) {
        action = null;
        this.state = state;
    }

    public StateChild(State state, int[] action) {
        this.state = state;
        this.action = action;
    }

    public State getState() {
        return state;
    }

    public int[] getAction() {
        return action;
    }
}
