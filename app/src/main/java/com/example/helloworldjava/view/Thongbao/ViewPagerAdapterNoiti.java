package com.example.helloworldjava.view.Thongbao;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.helloworldjava.view.Menu.HomeFragment;
import com.example.helloworldjava.view.Menu.TestFragment;
import com.example.helloworldjava.view.Search.SearchFragment;

public class ViewPagerAdapterNoiti extends FragmentStateAdapter {
    private ViewPager2 viewPager;

    public ViewPagerAdapterNoiti(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
//    // Thêm tham số ViewPager2 vào constructor
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Return the fragment instance corresponding to the position
        switch (position) {
            case 0:
                return new ThongBaoFragment();
            case 1:
                return new TinNhanFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
    // Phương thức để cập nhật trang hiện tại của ViewPager2
    public void setCurrentItem(int position) {
        viewPager.setCurrentItem(position);
    }

}
