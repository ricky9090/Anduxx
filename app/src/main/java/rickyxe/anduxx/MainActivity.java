package rickyxe.anduxx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import rickyxe.anduxx.counter.CounterReducer;
import rickyxe.anduxx.counter.CounterState;
import rickyxe.anduxx.counter.a.CounterDemoOneActivity;
import rickyxe.anduxx.counter.b.CounterDemoTwoActivity;
import rickyxe.anduxx.lib.ActivityWithStoreContract;
import rickyxe.anduxx.lib.StateChangeListener;
import rickyxe.anduxx.lib.Store;
import rickyxe.anduxx.lib.Storeable;
import rickyxe.anduxx.viewpagerdemo.a.DemoOneActivity;
import rickyxe.anduxx.viewpagerdemo.b.DemoTwoActivity;

public class MainActivity extends AppCompatActivity implements
        ActivityWithStoreContract<CounterState>,
        StateChangeListener<CounterState>, View.OnClickListener {

    public static final String GLOBAL_COUNTER_STORE_ID = "global_counter";

    TextView counterText;

    TextView counterOne;
    TextView counterTow;
    TextView viewPagerOne;
    TextView viewPagerTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createStore();

        counterText = findViewById(R.id.counter_value);
        counterOne = findViewById(R.id.counter_one);
        counterOne.setOnClickListener(this);
        counterTow = findViewById(R.id.counter_two);
        counterTow.setOnClickListener(this);
        viewPagerOne = findViewById(R.id.demo_one);
        viewPagerOne.setOnClickListener(this);
        viewPagerTwo = findViewById(R.id.demo_two);
        viewPagerTwo.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        onStateChange(getStore().getCurrentState());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyStore();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent = new Intent();
        switch (id) {
            case R.id.counter_one:
                intent.setClass(this, CounterDemoOneActivity.class);
                startActivity(intent);
                break;
            case R.id.counter_two:
                intent.setClass(this, CounterDemoTwoActivity.class);
                startActivity(intent);
                break;
            case R.id.demo_one:
                intent.setClass(this, DemoOneActivity.class);
                startActivity(intent);
                break;
            case R.id.demo_two:
                intent.setClass(this, DemoTwoActivity.class);
                startActivity(intent);
                break;
        }
    }

    @NonNull
    @Override
    @SuppressWarnings(value = "unchecked")
    public Storeable<CounterState> createStore() {
        if (MyApplication.getAppStoreManager(this).getStoreById(GLOBAL_COUNTER_STORE_ID) == null) {
            CounterState initialState = new CounterState(0);
            Store<CounterState> store = new Store<>(initialState);
            CounterReducer reducer = new CounterReducer();
            store.addReducer(reducer);
            MyApplication.getAppStoreManager(this).addStore(store, GLOBAL_COUNTER_STORE_ID);
            return store;
        }
        return MyApplication.getAppStoreManager(this).getStoreById(GLOBAL_COUNTER_STORE_ID);
    }

    @Override
    public void destroyStore() {
        if (MyApplication.getAppStoreManager(this).getStoreById(GLOBAL_COUNTER_STORE_ID) != null) {
            MyApplication.getAppStoreManager(this).getStoreById(GLOBAL_COUNTER_STORE_ID).onDestroy();
            MyApplication.getAppStoreManager(this).removeStore(GLOBAL_COUNTER_STORE_ID);
        }
    }

    @NonNull
    @Override
    @SuppressWarnings(value = "unchecked")
    public Storeable<CounterState> getStore() {
        if (MyApplication.getAppStoreManager(this).getStoreById(GLOBAL_COUNTER_STORE_ID) == null) {
            createStore();
        }
        return MyApplication.getAppStoreManager(this).getStoreById(GLOBAL_COUNTER_STORE_ID);
    }

    @Override
    public void onStateChange(CounterState state) {
        String countStr = getStore().getCurrentState().count + "";
        counterText.setText(countStr);
    }
}
