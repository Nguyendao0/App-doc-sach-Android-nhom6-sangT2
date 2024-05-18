package com.example.helloworldjava.view.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.helloworldjava.FCM.NotificationFCM;
import com.example.helloworldjava.R;
import com.example.helloworldjava.services.FirebaseAuthManager;
import com.example.helloworldjava.services.NotificationService;
import com.example.helloworldjava.services.ServiceBuilder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.messaging.FirebaseMessaging;

import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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