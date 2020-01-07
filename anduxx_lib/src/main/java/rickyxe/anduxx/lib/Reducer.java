package rickyxe.anduxx.lib;

public interface Reducer<T extends StateObject> {

    T reduce(T currentState, Action action);
}
