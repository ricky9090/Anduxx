package rickyxe.anduxx.viewpagerdemo.a;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class DemoOneFragmentAdapter extends FragmentStateAdapter {

    public DemoOneFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int positionName = position + 1;
        return DemoOneFragment.createFragment("Fragment No." + positionName);
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
