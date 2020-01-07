package rickyxe.anduxx.viewpagerdemo.b;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import rickyxe.anduxx.viewpagerdemo.DemoPageState;
import rickyxe.anduxx.MyApplication;
import rickyxe.anduxx.R;
import rickyxe.anduxx.lib.StateChangeListener;
import rickyxe.anduxx.lib.Storeable;

public class DemoTwoFragment extends Fragment implements StateChangeListener<DemoPageState> {

    private static final String LOG_TAG = "DemoTwoFragment";
    private String name;

    private TextView titleText;

    static DemoTwoFragment createFragment(String tag) {
        Bundle bundle = new Bundle();
        bundle.putString("name", tag);
        DemoTwoFragment fragment = new DemoTwoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            name = getArguments().getString("name");
        }

        if (getStore() != null) {
            getStore().addListener(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_viewpager_demo, container, false);

        titleText = rootView.findViewById(R.id.fragment_text);

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getStore() != null) {
            getStore().removeListener(this);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (getStore() != null) {
            DemoPageState state = getStore().getCurrentState();
            onStateChange(state);
        }
    }

    @Override
    public void onStateChange(DemoPageState state) {
        if (!isVisible()) {
            Log.d(LOG_TAG, this.toString() + "  not visible !!! update cancel");
            return;

        }
        Log.d(LOG_TAG, this.toString() + "  update data !!!");
        titleText.setText("Fragment : " + name + "\n" + state.toString());

    }

    @SuppressWarnings(value = "unchecked")
    private Storeable<DemoPageState> getStore() {
        try {
            if (getActivity() != null) {
                return (Storeable<DemoPageState>) MyApplication.getAppStoreManager(getContext())
                        .getStoreById(DemoTwoActivity.STORE_ID);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}