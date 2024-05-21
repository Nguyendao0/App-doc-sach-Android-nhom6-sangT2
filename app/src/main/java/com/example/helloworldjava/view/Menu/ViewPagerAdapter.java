package com.example.helloworldjava.view.Menu;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.helloworldjava.presenter.LibraryContractInterface.LibraryContract;
import com.example.helloworldjava.presenter.NotificationContractInterface.Notification;
import com.example.helloworldjava.view.Library.LibraryFragment;
import com.example.helloworldjava.view.Search.SearchFragment;
import com.example.helloworldjava.view.Thongbao.NoitificationFragment;
import com.example.helloworldjava.view.dangtruyen.ThuVienCaNhanFragment;
import com.example.helloworldjava.view.home.HomeFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {


    private ViewPager2 viewPager;

    private LibraryContract.View libraryFragment;
    private Notification.View noitificationFragment;
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, ViewPager2 viewPager) {
      // Lưu trữ ViewPager2
        super(fragmentActivity);

        noitificationFragment = new NoitificationFragment();
        libraryFragment = new LibraryFragment();

        this.viewPager = viewPager;
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                switch (position) {

                    case 2:
                        libraryFragment.initData();
                        break;
                    case 4:
                        noitificationFragment.initData();
                          break;
                    default:
                       break;
                }
            }
        });
    }
//    // Thêm tham số ViewPager2 vào constructor

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
                return (Fragment) libraryFragment;
            case 3:
                return new ThuVienCaNhanFragment();
            case 4:
                return (Fragment) noitificationFragment;
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
