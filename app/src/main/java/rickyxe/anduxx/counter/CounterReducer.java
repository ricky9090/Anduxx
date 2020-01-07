package rickyxe.anduxx.counter;

import rickyxe.anduxx.lib.Action;
import rickyxe.anduxx.lib.Reducer;

public class CounterReducer implements Reducer<CounterState> {

    @Override
    public CounterState reduce(CounterState currentState, Action action) {
        if (action.isTypeOf(CounterAction.INCREASE)) {
            return new CounterState(currentState.count + 1);
        } else if (action.isTypeOf(CounterAction.DECREASE)) {
            return new CounterState(currentState.count - 1);
        }
        return currentState;
    }
}
