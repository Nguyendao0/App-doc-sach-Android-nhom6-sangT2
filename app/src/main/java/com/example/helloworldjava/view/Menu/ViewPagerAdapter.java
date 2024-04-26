package com.example.helloworldjava.view.Menu;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.helloworldjava.view.Thongbao.NoitificationFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {


    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Return the fragment instance corresponding to the position
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new TestFragment();
            case 2:
                return new TestFragment();
            case 3:
                return  new TestFragment();
            case 4:
                return  new NoitificationFragment();
            default:
                return new TestFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
