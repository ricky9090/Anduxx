package rickyxe.anduxx.viewpagerdemo.a;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import rickyxe.anduxx.viewpagerdemo.DemoPageAction;
import rickyxe.anduxx.viewpagerdemo.DemoPageState;
import rickyxe.anduxx.R;
import rickyxe.anduxx.lib.Action;
import rickyxe.anduxx.lib.ActivityWithStore;
import rickyxe.anduxx.lib.StateChangeListener;
import rickyxe.anduxx.lib.Store;
import rickyxe.anduxx.lib.Storeable;
import rickyxe.anduxx.viewpagerdemo.middleware.AnotherLogger;
import rickyxe.anduxx.viewpagerdemo.middleware.Logger;
import rickyxe.anduxx.viewpagerdemo.reducer.ChangeTimeReducer;
import rickyxe.anduxx.viewpagerdemo.reducer.ChangeTypeReducer;

public class DemoOneActivity extends ActivityWithStore<DemoPageState> implements StateChangeListener<DemoPageState> {

    String[] yearArray = {"2017", "2018", "2019"};
    String[] typeArray = {"Android", "iOS"};

    TextView descText;

    TextView yearText;
    TextView typeText;
    TextView headerText;

    TabLayout tabLayout;
    ViewPager2 viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_demo);

        initViews();

        Storeable<DemoPageState> store = getStore();
        yearText.setText(store.getCurrentState().year + "年");
        typeText.setText(store.getCurrentState().type + " 类型");

        headerText.setText(store.getCurrentState().toString());

        getStore().addListener(this);
    }

    @NonNull
    @Override
    public Storeable<DemoPageState> createStore() {
        DemoPageState initState = new DemoPageState(2017, "Android");
        ChangeTimeReducer timeReducer = new ChangeTimeReducer();
        ChangeTypeReducer typeReducer = new ChangeTypeReducer();
        Storeable<DemoPageState> store = new Store<>(initState, new Logger(), new AnotherLogger());
        store.addReducer(timeReducer);
        store.addReducer(typeReducer);
        return store;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getStore().removeListener(this);
        getStore().onDestroy();
    }

    @Override
    public void onStateChange(DemoPageState state) {
        yearText.setText(state.year + "年");
        typeText.setText(state.type + " 类型");
        headerText.setText(state.toString());
    }

    private void initViews() {
        descText = findViewById(R.id.desc_text);
        descText.setText(R.string.viewpager_demo_one_desc);

        yearText = findViewById(R.id.year_button);
        typeText = findViewById(R.id.type_button);
        headerText = findViewById(R.id.header_text);
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);

        yearText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showYearDialog();
            }
        });

        typeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTypeDialog();
            }
        });


        // ---------- init viewpager ----------
        DemoOneFragmentAdapter adapter = new DemoOneFragmentAdapter(this);
        viewPager.setAdapter(adapter);
        TabLayoutMediator.TabConfigurationStrategy strategy = new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                int name = position + 1;
                tab.setText("Tab: " + name);
            }
        };
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, strategy);
        tabLayoutMediator.attach();
    }

    private void showYearDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DemoOneActivity.this)
                .setItems(yearArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                getStore().dispatch(Action.create(DemoPageAction.CHANGE_TIME, 2017));
                                break;
                            case 1:
                                getStore().dispatch(Action.create(DemoPageAction.CHANGE_TIME, 2018));
                                break;
                            case 2:
                                getStore().dispatch(Action.create(DemoPageAction.CHANGE_TIME, 2019));
                                break;
                        }
                    }
                });
        builder.show();
    }

    private void showTypeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DemoOneActivity.this)
                .setItems(typeArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                getStore().dispatch(Action.create(DemoPageAction.CHANGE_TYPE, "Android"));
                                break;
                            case 1:
                                getStore().dispatch(Action.create(DemoPageAction.CHANGE_TYPE, "iOS"));
                                break;
                        }
                    }
                });
        builder.show();
    }
}
