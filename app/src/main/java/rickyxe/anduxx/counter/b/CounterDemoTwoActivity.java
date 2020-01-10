package rickyxe.anduxx.counter.b;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import rickyxe.anduxx.MainActivity;
import rickyxe.anduxx.MyApplication;
import rickyxe.anduxx.R;
import rickyxe.anduxx.counter.CounterAction;
import rickyxe.anduxx.counter.CounterState;
import rickyxe.anduxx.lib.Action;
import rickyxe.anduxx.lib.StateChangeListener;
import rickyxe.anduxx.lib.Storeable;

public class CounterDemoTwoActivity extends AppCompatActivity implements
        StateChangeListener<CounterState> {

    Button increaseButton;
    Button decreaseButton;
    TextView descText;
    TextView counterText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_counter);
        initViews();

        if (getStore() != null) {
            String countStr = getStore().getCurrentState().count + "";
            counterText.setText(countStr);
        }

        getStore().addListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getStore().removeListener(this);
    }

    @Override
    public void onStateChange(CounterState state) {
        String countStr = state.count + "";
        counterText.setText(countStr);
    }

    private void initViews() {
        descText = findViewById(R.id.desc_text);
        descText.setText(R.string.counter_demo_two_desc);

        counterText = findViewById(R.id.counter_value);
        increaseButton = findViewById(R.id.increase);
        decreaseButton = findViewById(R.id.decrease);

        increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getStore() != null) {
                    getStore().dispatch(Action.create(CounterAction.INCREASE, null));
                }

            }
        });

        decreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getStore() != null) {
                    getStore().dispatch(Action.create(CounterAction.DECREASE, null));
                }
            }
        });
    }

    @SuppressWarnings(value = "unchecked")
    private Storeable<CounterState> getStore() {
        return MyApplication.getAppStoreManager(this).getStoreById(MainActivity.GLOBAL_COUNTER_STORE_ID);
    }
}
