package rickyxe.anduxx.lib;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public final class Store<STATE extends StateObject> implements Storeable<STATE> {

    private STATE currentState;

    private Middleware<STATE> middleware;

    private final List<Reducer<STATE>> reducerList = new ArrayList<>();
    private final List<StateChangeListener<STATE>> listenerList = new ArrayList<>();

    public Store(@NonNull STATE initialState) {
        this.currentState = initialState;
    }

    public Store(@NonNull STATE initialState, Middleware<STATE>... middlewares) {
        this(initialState);

        if (middlewares != null && middlewares.length > 0) {
            this.middleware = middlewares[0];
            Middleware<STATE> last = null;
            for (int i = 0; i < middlewares.length; i++) {
                Middleware<STATE> m = middlewares[i];
                if (last != null) {
                    last.next = m;
                }
                last = m;
            }
        }
    }

    @Override
    public void addReducer(Reducer<STATE> reducer) {
        if (reducerList.contains(reducer)) {
            return;
        }

        reducerList.add(reducer);
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public STATE getCurrentState() {
        return (STATE) currentState.copy(currentState);
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public void dispatch(Action action) {
        STATE tmp = (STATE) this.currentState.copy(currentState);
        if (middleware != null) {
            this.currentState = middleware.dispatch(tmp, action, reducerList);
        } else {
            this.currentState = applyReducer(tmp, action, reducerList);
        }

        notifyListeners(this.currentState);
    }

    static <T extends StateObject> T applyReducer(T currentState, Action action, List<Reducer<T>> reducerList) {
        T result = currentState;
        for (Reducer<T> reducer : reducerList) {
            T reduceState = reducer.reduce(currentState, action);
            if (reduceState != null && !reduceState.equals(currentState)) {
                result = reduceState;
            }
        }

        return result;
    }

    private void notifyListeners(STATE updatedState) {
        for (StateChangeListener<STATE> listener : listenerList) {
            listener.onStateChange(updatedState);
        }
    }

    @Override
    public void addListener(StateChangeListener<STATE> listener) {
        if (listenerList.contains(listener)) {
            return;
        }

        listenerList.add(listener);
    }

    @Override
    public void removeListener(StateChangeListener<STATE> listener) {
        if (listenerList.contains(listener)) {
            int index = listenerList.indexOf(listener);
            if (index >= 0) {
                listenerList.remove(index);
            }
        }
    }

    @Override
    public void onDestroy() {
        middleware = null;
        reducerList.clear();
        listenerList.clear();
    }
}
