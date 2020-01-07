package rickyxe.anduxx.viewpagerdemo.reducer;

import android.util.Log;

import rickyxe.anduxx.viewpagerdemo.DemoPageState;
import rickyxe.anduxx.lib.Action;
import rickyxe.anduxx.lib.Reducer;

import static rickyxe.anduxx.viewpagerdemo.DemoPageAction.CHANGE_TYPE;

public class ChangeTypeReducer implements Reducer<DemoPageState> {

    public static final String LOG_TAG = "ChangeTypeReducer";

    @Override
    public DemoPageState reduce(DemoPageState currentState, Action action) {
        if (action.isTypeOf(CHANGE_TYPE)) {
            Log.d(LOG_TAG, "ChangeTypeReducer reduce action !!!");
            int time = currentState.year;
            String type = (String) action.data;
            return new DemoPageState(time, type);
        }

        return currentState;
    }
}
