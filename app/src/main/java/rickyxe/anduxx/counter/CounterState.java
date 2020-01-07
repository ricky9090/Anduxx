package rickyxe.anduxx.counter;

import rickyxe.anduxx.lib.StateObject;

public class CounterState implements StateObject {

    public int count;

    public CounterState(int count) {
        this.count = count;
    }

    @Override
    public StateObject copy(StateObject old) {
        if (old instanceof CounterState) {
            return new CounterState(((CounterState) old).count);
        }
        throw new IllegalArgumentException();
    }
}
