package com.example.helloworldjava;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.example.helloworldjava.Fragment.AccountFragment;
import com.example.helloworldjava.Fragment.NotificationFragment;
import com.example.helloworldjava.Fragment.ReelsFragment;
import com.example.helloworldjava.Fragment.SearchFragment;
import com.example.helloworldjava.view.Menu.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomePageDemoActivity extends AppCompatActivity {
    AccountFragment accountFragment = new AccountFragment();
    HomeFragment homeFragment = new HomeFragment();
    NotificationFragment notificationFragment = new NotificationFragment();
    ReelsFragment reelsFragment = new ReelsFragment();
    SearchFragment searchFragment = new SearchFragment();
    BottomNavigationView bottomNavigationView;
    String page;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_demo);
        getSupportActionBar().hide();
       Intent intent = getIntent();
       page = intent.getStringExtra("page");
       if( intent!=null && page.equals("Home")){
        getSupportFragmentManager().beginTransaction().replace(R.id.container,accountFragment).commit();
        }else{
       }
        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getOrder()){
                    case 1:getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                        return true;
                    case 2:getSupportFragmentManager().beginTransaction().replace(R.id.container,reelsFragment).commit();
                        return true;
                    case 3:getSupportFragmentManager().beginTransaction().replace(R.id.container,searchFragment).commit();
                        return true;
                    case 4:getSupportFragmentManager().beginTransaction().replace(R.id.container,notificationFragment).commit();
                        return true;
                    case 5:getSupportFragmentManager().beginTransaction().replace(R.id.container,accountFragment).commit();
                        return true;
                }
                return false;

            }
        });
    }
}