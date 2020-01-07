package rickyxe.anduxx.lib;

import java.util.List;

public abstract class Middleware<T extends StateObject> {

    protected Middleware<T> next;

    @SuppressWarnings(value = "unchecked")
    protected T dispatch(T currentState, Action action, List<Reducer<T>> reducerList) {
        T tmpBefore = (T) currentState.copy(currentState);
        beforeReduce(tmpBefore, action);

        T updatedState;
        if (next == null) {
            updatedState = Store.applyReducer(currentState, action, reducerList);
        } else {
            updatedState = next.dispatch(currentState, action, reducerList);
        }

        T tmpAfter = (T) updatedState.copy(updatedState);
        afterReduce(tmpAfter);

        return updatedState;
    }

    public void beforeReduce(T currentState, Action action) {}

    public void afterReduce(T updatedState) {}
}
