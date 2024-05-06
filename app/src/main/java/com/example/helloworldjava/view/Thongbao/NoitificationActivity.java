package com.example.helloworldjava.view.Thongbao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import com.example.helloworldjava.R;
import com.example.helloworldjava.view.Menu.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class NoitificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_layout);

        //tabLayout2
        TabLayout tabLayout = findViewById(R.id.tabLayoutNoiti);
        ViewPager2 viewPager = findViewById(R.id.vp_noiti);
        ViewPagerAdapterNoiti adapter = new ViewPagerAdapterNoiti(this);
        viewPager.setAdapter(adapter);
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
    }
}