package rickyxe.anduxx.lib;

public interface Storeable<T extends StateObject> {

    T getCurrentState();

    void addReducer(Reducer<T> reducer);

    void dispatch(Action action);

    void addListener(StateChangeListener<T> listener);

    void removeListener(StateChangeListener<T> listener);

    void onDestroy();
}
