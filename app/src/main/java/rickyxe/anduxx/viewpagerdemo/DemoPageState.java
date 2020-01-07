package rickyxe.anduxx.viewpagerdemo;

import androidx.annotation.NonNull;

import rickyxe.anduxx.lib.StateObject;

public class DemoPageState implements StateObject {

    public int year;
    public String type;

    public DemoPageState(int year, String type) {
        this.year = year;
        this.type = type;
    }

    @NonNull
    @Override
    public String toString() {
        return "State: [Year: " + year + ", Type: " + type + "]";
    }


    @Override
    public StateObject copy(StateObject old) {
        if (old instanceof DemoPageState) {
            DemoPageState oldState = (DemoPageState) old;
            DemoPageState state = new DemoPageState(oldState.year, oldState.type);
            return state;
        }
        throw new IllegalArgumentException();
    }
}
