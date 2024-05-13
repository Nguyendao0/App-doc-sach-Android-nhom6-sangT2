package com.example.helloworldjava.view.Thongbao;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.helloworldjava.R;
import com.example.helloworldjava.presenter.Thongbao.NotificationFCMPresenter;
import com.example.helloworldjava.services.NotificationService;
import com.example.helloworldjava.services.ServiceBuilder;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NoitificationFragment extends Fragment {

    NotificationFCMPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notification_layout, container, false);
        //tabLayout2
        TabLayout tabLayout = view.findViewById(R.id.tabLayoutNoiti);
        ViewPager2 viewPager = view.findViewById(R.id.vp_noiti);
        FragmentActivity activity = requireActivity();
        ViewPagerAdapterNoiti adapter = new ViewPagerAdapterNoiti(activity);
        viewPager.setAdapter(adapter);
        presenter = new NotificationFCMPresenter(this.NoitificationFragment.class);
        presenter.getListSach();
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Thông báo");
                    break;
                case 1:
                    tab.setText("Tin nhắn");
                    break;

            }
        }).attach();




        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                // Xử lý khi trang được chọn
                Log.d("ViewPager", "Page selected: " + position);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    private void  setListSach()
    {

    }
}