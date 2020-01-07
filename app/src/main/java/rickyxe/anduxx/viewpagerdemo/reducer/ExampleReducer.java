package rickyxe.anduxx.viewpagerdemo.reducer;

import android.util.Log;

import rickyxe.anduxx.viewpagerdemo.DemoPageState;
import rickyxe.anduxx.lib.Action;
import rickyxe.anduxx.lib.Reducer;

import static rickyxe.anduxx.viewpagerdemo.DemoPageAction.CHANGE_TIME;
import static rickyxe.anduxx.viewpagerdemo.DemoPageAction.CHANGE_TYPE;

public class ExampleReducer implements Reducer<DemoPageState> {

    public static final String LOG_TAG = "ExampleReducer";

    @Override
    public DemoPageState reduce(DemoPageState currentState, Action action) {
        Log.d(LOG_TAG, "reduce action !!!");
        if (action.isTypeOf(CHANGE_TIME)) {
            int time = (int) action.data;
            String type = currentState.type;
            return new DemoPageState(time, type);
        } else if (action.isTypeOf(CHANGE_TYPE)) {
            int time = currentState.year;
            String type = (String) action.data;
            return new DemoPageState(time, type);
        }

        return currentState;
    }
}
