package com.example.helloworldjava.view.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.helloworldjava.R;
import com.example.helloworldjava.services.FirebaseAuthManager;
import com.example.helloworldjava.services.NotificationService;
import com.example.helloworldjava.services.ServiceBuilder;
import com.example.helloworldjava.services.TokenService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.messaging.FirebaseMessaging;

import androidx.viewpager2.widget.ViewPager2;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends AppCompatActivity {
    private FirebaseAuthManager firebaseAuthManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_layout);

        initTokenAndNotification();

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


    private void initTokenAndNotification()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", this.MODE_PRIVATE);
        boolean isSettoken = sharedPreferences.getBoolean("isSettoken", false);
        if (!isSettoken)
        {
            firebaseAuthManager = new FirebaseAuthManager(this);
            String idNguoiDung = firebaseAuthManager.getCurrentUser().getUid();
            sendNotificaction(idNguoiDung);
            sendTokenToFCM(idNguoiDung);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isSettoken", true); // Lưu giá trị true khi token đã được thiết lập
            editor.apply();
        }
    }


    private void sendNotificaction(String idNguoiDung)
    {
        NotificationService notificationService = ServiceBuilder.buildService(NotificationService.class);
        Call<String> request = notificationService.createIDNotification(idNguoiDung);

        request.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String thongbao = response.body();
                    System.out.println("response: " + thongbao);
                } else {
                    System.out.println("Response failed: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                System.out.println("Error: " + throwable.getMessage());
            }
        });
    }


    private void sendTokenToFCM(String idNguoiDung) {

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (!task.isSuccessful()) {
                    System.out.println("Fetching FCM registration token failed: " + task.getException());
                    return;
                }

                String token = task.getResult();

                TokenService tokenService = ServiceBuilder.buildService(TokenService.class);
                Call<String> request = tokenService.createTokenById(token, idNguoiDung);

                request.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String thongbao = response.body();

                            System.out.println("response: " + thongbao);
                        } else {
                            System.out.println("Response failed: " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable throwable) {
                        System.out.println("Error: " + throwable.getMessage());
                    }
                });
            }
        });

    }
}