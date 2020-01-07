package rickyxe.anduxx.counter.a;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import rickyxe.anduxx.R;
import rickyxe.anduxx.counter.CounterAction;
import rickyxe.anduxx.counter.CounterReducer;
import rickyxe.anduxx.counter.CounterState;
import rickyxe.anduxx.lib.Action;
import rickyxe.anduxx.lib.ActivityWithStore;
import rickyxe.anduxx.lib.StateChangeListener;
import rickyxe.anduxx.lib.Store;
import rickyxe.anduxx.lib.Storeable;

public class CounterDemoOneActivity extends ActivityWithStore<CounterState>
        implements StateChangeListener<CounterState> {

    Button increaseButton;
    Button decreaseButton;
    TextView descText;
    TextView counterText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_counter);

        initViews();
        String countStr = getStore().getCurrentState().count + "";
        counterText.setText(countStr);

        getStore().addListener(this);
    }

    @NonNull
    @Override
    public Storeable<CounterState> createStore() {
        CounterState initialState = new CounterState(0);
        Store<CounterState> store = new Store<>(initialState);
        CounterReducer reducer = new CounterReducer();
        store.addReducer(reducer);
        return store;
    }

    @Override
    public void onStateChange(CounterState state) {
        String countStr = state.count + "";
        counterText.setText(countStr);
    }

    private void initViews() {
        descText = findViewById(R.id.desc_text);
        descText.setText(R.string.counter_demo_one_desc);

        counterText = findViewById(R.id.counter_value);
        increaseButton = findViewById(R.id.increase);
        decreaseButton = findViewById(R.id.decrease);

        increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getStore().dispatch(Action.create(CounterAction.INCREASE, null));
            }
        });

        decreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getStore().dispatch(Action.create(CounterAction.DECREASE, null));
            }
        });
    }
}
