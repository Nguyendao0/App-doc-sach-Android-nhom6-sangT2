package com.example.helloworldjava.view.Menu;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.helloworldjava.view.Library.LibraryFragment;
import com.example.helloworldjava.view.Search.SearchFragment;
import com.example.helloworldjava.view.Thongbao.NoitificationFragment;
import com.example.helloworldjava.view.dangtruyen.DangTruyenFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {


    private ViewPager2 viewPager;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
//    // Thêm tham số ViewPager2 vào constructor
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, ViewPager2 viewPager) {
        super(fragmentActivity);
        this.viewPager = viewPager; // Lưu trữ ViewPager2
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Return the fragment instance corresponding to the position
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new SearchFragment();
            case 2:
                return new LibraryFragment();
            case 3:
                return  new DangTruyenFragment();
            case 4:
                return  new NoitificationFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    // Phương thức để cập nhật trang hiện tại của ViewPager2
    public void setCurrentItem(int position) {
        viewPager.setCurrentItem(position);
    }
}
