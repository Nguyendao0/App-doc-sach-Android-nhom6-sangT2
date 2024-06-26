package com.example.helloworldjava.view.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.helloworldjava.R;
import com.example.helloworldjava.services.FirebaseAuthManager;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import androidx.viewpager2.widget.ViewPager2;

public class MenuActivity extends AppCompatActivity {
    private FirebaseAuthManager firebaseAuthManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_layout);


        //tabLayout2
        TabLayout tabLayout = findViewById(R.id.tabLayout2);
        ViewPager2 viewPager = findViewById(R.id.view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this, viewPager);
        viewPager.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    //tab.setText("Home");
                    tab.setIcon(R.drawable.ic_home);
                    break;
                case 1:
                    //tab.setText("Search");
                    tab.setIcon(R.drawable.ic_search1);
                    break;
                case 2:
                    //tab.setText("Library");
                    //ic_library
                    tab.setIcon(R.drawable.ic_library);
                    break;
                case 3:
                    //tab.setText("Create");
                    tab.setIcon(R.drawable.ic_pen);
                    break;
                case 4:
                    //tab.setText("NotificationFCM");
                    tab.setIcon(R.drawable.ic_bell);
                    break;
            }
        }).attach();


    }




}