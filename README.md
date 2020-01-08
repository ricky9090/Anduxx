# Anduxx
A simple implementation of Redux for Android App

### Store in Activity
1. Create ``State``, ``Action``
```java
public class CounterState implements StateObject {

    // data variable

    @Override
    public StateObject copy(StateObject old) {
        // copy old state
    }
}

// define actions somewhere
public static final String INCREASE = "increase";
public static final String DECREASE = "decrease";
```

2. Implement ``Reducer``
```java
public class CounterReducer implements Reducer<CounterState> {

    @Override
    public CounterState reduce(CounterState currentState, Action action) {
        // handle action
    }
}
```

3. Implement Activity, extends ``ActivityWithStore``, implement ``StateChangeListener``, add Listener
```java
public class CounterDemoOneActivity extends ActivityWithStore<CounterState>
        implements StateChangeListener<CounterState> {

    Button increaseButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ...
        increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // dispatch action
                getStore().dispatch(Action.create(CounterAction.INCREASE, null));
            }
        });

        // add listener
        getStore().addListener(this);
    }

    @NonNull
    @Override
    public Storeable<CounterState> createStore() {
        // create store in activity
    }

    @Override
    public void onStateChange(CounterState state) {
        // update UI
    }
}

```

### Store in Application
1. Create ``State``, ``Action``
2. Create ``StoreManager`` in Application
```java
public class MyApplication extends Application {

    private StoreManager storeManager;

    @Override
    public void onCreate() {
        super.onCreate();

        storeManager = new StoreManager();
    }

    public StoreManager getStoreManager() {
        return storeManager;
    }

    public static StoreManager getAppStoreManager(Context context) {
        Application app = (Application) context.getApplicationContext();
        return ((MyApplication) app).getStoreManager();
    }
}

```
3. Implement ``ActivityWithStoreContract``, manually call methods, add store to ``StoreManager``
```java
public class MainActivity extends AppCompatActivity implements
        ActivityWithStoreContract<CounterState>,
        StateChangeListener<CounterState> {

    // unique ID for store
    public static final String GLOBAL_COUNTER_STORE_ID = "global_counter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ...
        // create store
        createStore();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // destore store when necessary
        destroyStore();
    }

    @NonNull
    @Override
    public Storeable<CounterState> createStore() {
        // create store, add into application's storeManager
        MyApplication.getAppStoreManager(this).addStore(store, GLOBAL_COUNTER_STORE_ID);
        // ...
    }

    @Override
    public void destroyStore() {
        // destroy store, remove from application's storeManager
        if (MyApplication.getAppStoreManager(this).getStoreById(GLOBAL_COUNTER_STORE_ID) != null) {
            MyApplication.getAppStoreManager(this).getStoreById(GLOBAL_COUNTER_STORE_ID).onDestroy();
            MyApplication.getAppStoreManager(this).removeStore(GLOBAL_COUNTER_STORE_ID);
        }
    }

    @NonNull
    @Override
    public Storeable<CounterState> getStore() {
        // return store in application
        return MyApplication.getAppStoreManager(this).getStoreById(MainActivity.GLOBAL_COUNTER_STORE_ID);

    }

    @Override
    public void onStateChange(CounterState state) {
        // update UI
    }
}
``` 
