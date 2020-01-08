package rickyxe.anduxx.counter;

import rickyxe.anduxx.lib.Action;
import rickyxe.anduxx.lib.Reducer;

public class CounterReducer implements Reducer<CounterState> {

    @Override
    public CounterState reduce(CounterState currentState, Action action) {
        switch (action.type) {
            case CounterAction.INCREASE:
                return new CounterState(currentState.count + 1);
            case CounterAction.DECREASE:
                return new CounterState(currentState.count - 1);
            default:
                return currentState;
        }
    }
}
