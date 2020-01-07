package rickyxe.anduxx.viewpagerdemo.reducer;

import android.util.Log;

import rickyxe.anduxx.viewpagerdemo.DemoPageState;
import rickyxe.anduxx.lib.Action;
import rickyxe.anduxx.lib.Reducer;

import static rickyxe.anduxx.viewpagerdemo.DemoPageAction.CHANGE_TIME;

public class ChangeTimeReducer implements Reducer<DemoPageState> {

    public static final String LOG_TAG = "ChangeTimeReducer";

    @Override
    public DemoPageState reduce(DemoPageState currentState, Action action) {
        if (action.isTypeOf(CHANGE_TIME)) {
            Log.d(LOG_TAG, "ChangeTimeReducer reduce action !!!");
            int time = (int) action.data;
            String type = currentState.type;
            return new DemoPageState(time, type);
        }

        return currentState;
    }
}