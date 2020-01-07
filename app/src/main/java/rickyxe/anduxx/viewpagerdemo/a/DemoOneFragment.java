package rickyxe.anduxx.viewpagerdemo.a;

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
import rickyxe.anduxx.R;
import rickyxe.anduxx.lib.StateChangeListener;
import rickyxe.anduxx.lib.Storeable;

public class DemoOneFragment extends Fragment implements StateChangeListener<DemoPageState> {

    private static final String LOG_TAG = "DemoOneFragment";

    private Storeable<DemoPageState> store;
    private String name;

    private TextView titleText;

    public static DemoOneFragment createFragment(String tag) {
        Bundle bundle = new Bundle();
        bundle.putString("name", tag);
        DemoOneFragment fragment = new DemoOneFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            name = getArguments().getString("name");
        }

        if (getActivity() instanceof DemoOneActivity) {
            this.store = ((DemoOneActivity) getActivity()).getStore();
            this.store.addListener(this);
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
        store.removeListener(this);
        store = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (store != null) {
            DemoPageState state = (DemoPageState) store.getCurrentState();
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

}
