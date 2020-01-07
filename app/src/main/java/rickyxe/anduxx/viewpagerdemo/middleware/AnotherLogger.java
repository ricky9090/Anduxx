package rickyxe.anduxx.viewpagerdemo.middleware;

import android.util.Log;

import rickyxe.anduxx.viewpagerdemo.DemoPageState;
import rickyxe.anduxx.lib.Action;
import rickyxe.anduxx.lib.Middleware;

public class AnotherLogger extends Middleware<DemoPageState> {

    public static final String LOG_TAG = "LoggerB";

    @Override
    public void beforeReduce(DemoPageState currentState, Action action) {
        Log.d(LOG_TAG, ">>>> " + currentState.toString() + ", " + action.toString());
    }

    @Override
    public void afterReduce(DemoPageState updatedState) {
        Log.d(LOG_TAG, "<<<< " + updatedState.toString());
    }
}
