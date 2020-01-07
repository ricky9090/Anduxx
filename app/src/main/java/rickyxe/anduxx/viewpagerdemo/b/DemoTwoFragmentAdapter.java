package rickyxe.anduxx.viewpagerdemo.b;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class DemoTwoFragmentAdapter extends FragmentStateAdapter {

    public DemoTwoFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int positionName = position + 1;
        return DemoTwoFragment.createFragment("Fragment No." + positionName);
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}