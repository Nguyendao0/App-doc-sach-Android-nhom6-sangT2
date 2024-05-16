package com.example.helloworldjava;



import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;


import com.example.helloworldjava.view.GioiThieuSach.BookDetailActivity;
import com.example.helloworldjava.services.NotificationService;
import com.example.helloworldjava.services.ServiceBuilder;
import com.example.helloworldjava.services.TokenService;
import com.example.helloworldjava.view.GioiThieuSach.BookDetailActivity;
import com.example.helloworldjava.FCM.NotificationFCM;
import com.example.helloworldjava.view.home.HomeActivity;
import com.example.helloworldjava.view.Menu.MenuActivity;
import com.example.helloworldjava.view.login.Account_Login;
import com.example.helloworldjava.view.register.Account_Register;
import com.example.helloworldjava.view.QRGen;
import com.example.helloworldjava.view.Search.SearchActivity;
import com.example.helloworldjava.view.ReadBookActivity;
import com.example.helloworldjava.view.SpeechBookTest.SpeechActivity;
import com.example.helloworldjava.view.login.WebViewGoogleActivity;
import com.example.helloworldjava.view.user.UserActivity;
import com.example.helloworldjava.view.welcome.WelcomePagerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private void findAllNotification()
    {
        NotificationService notificationService = ServiceBuilder.buildService(NotificationService.class);
        Call<List<NotificationFCM>> request = notificationService.findAllNotificationById("zed");
        request.enqueue(new Callback<List<NotificationFCM>>() {
            @Override
            public void onResponse(Call<List<NotificationFCM>> call, Response<List<NotificationFCM>> response) {
                if (response.isSuccessful()) {
                    List<NotificationFCM> notis = response.body();

                    for (NotificationFCM n:notis)
                    {
                        System.out.println(notis.toString());
                    }
                } else {
                    System.out.println("Response failed: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<NotificationFCM>> call, Throwable throwable) {
                System.out.println("Error: " + throwable.getMessage());
            }
        });
    }


    private void sendNotificationToFB()
    {
        NotificationService notificationService = ServiceBuilder.buildService(NotificationService.class);
        NotificationFCM notificationFCM = new NotificationFCM();
        notificationFCM.setContent("yasuasdaso");
        notificationFCM.setTitle("milkitsadasa");

        Call<String> request = notificationService.createNotificationById("zed", notificationFCM);

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

    private void sendNotificaction()
    {
        NotificationService notificationService = ServiceBuilder.buildService(NotificationService.class);
        Call<String> request = notificationService.createIDNotification("zed");

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

    private void sendTokenToFCM() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", this.MODE_PRIVATE);
        boolean isSettoken = sharedPreferences.getBoolean("isSettoken", false);

        if (!isSettoken) {
            FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
                @Override
                public void onComplete(@NonNull Task<String> task) {
                    if (!task.isSuccessful()) {
                        System.out.println("Fetching FCM registration token failed: " + task.getException());
                        return;
                    }

                    String token = task.getResult();

                    TokenService tokenService = ServiceBuilder.buildService(TokenService.class);
                    Call<String> request = tokenService.createTokenById(token, "zed");

                    request.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response.isSuccessful()) {
                                String thongbao = response.body();
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putBoolean("isSettoken", true); // Lưu giá trị true khi token đã được thiết lập
                                editor.apply();
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Kiểm tra nếu đây là lần đầu chạy ứng dụng
        boolean isFirstRun = checkFirstRun();

        showWelcomeDialog();
//        if (isFirstRun) {
//            showWelcomeDialog();
//        }
        // goToMenu();

//        sendNotificaction();
//        findAllNotification();
        sendTokenToFCM();
//        sendNotificationToFB();

        Button button_home = findViewById(R.id.button_home);
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        Log.d("MainActivity" ,"Hello world");


        Button btnUser = findViewById(R.id.btnUser);
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });



        Button btnReadBook = findViewById(R.id.btbReadBook);
        btnReadBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, ReadBookActivity.class);
                startActivity(myIntent);
            }
        });
        Button btnopenWebview = findViewById(R.id.btn_openwebview);
        btnopenWebview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, WebViewGoogleActivity.class);
                startActivity(myIntent);
            }
        });

        Button btnlogin = findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, Account_Login.class);
                startActivity(myIntent);
            }
        });

        Button btnQRGen = findViewById(R.id.btnQRGen);
        btnQRGen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, QRGen.class);
                startActivity(myIntent);
            }
        });


    }


    public  void gotoLogin(View view ){
        Intent intent = new Intent(this, Account_Login.class);
        startActivity(intent);
    }

    public  void gotoQRGen(View view ){
        Intent intent = new Intent(this, QRGen.class);
        startActivity(intent);
    }

    public  void gotoSearch(View view ){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    public  void gotoSpeech(View view ){
        Intent intent = new Intent(this, SpeechActivity.class);
        startActivity(intent);
    }

    public void goToMenu(View v) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void goToGioiThieuSach(View view) {
        Intent intent = new Intent(this, BookDetailActivity.class);
        startActivity(intent);
    }
    //goToSignin
    public void goToSignup(View view) {
        Intent intent = new Intent(this, Account_Register.class);
        startActivity(intent);
    }
    
//    public void ThongBao(View view) {
//
//        Intent intent = new Intent(this, NoitificationActivity.class);
//        startActivity(intent);
//    }
//    public void GoToLibraryActivity(View view)
//    {
//        Intent intent = new Intent(this, LibraryActivity.class);
//        startActivity(intent);
//    }
//    public void goToPDF(View view)
//    {
//        Intent intent = new Intent(this, TESTGETPDFActivity.class);
//        startActivity(intent);
//    }


    // Kiểm tra lần đầu chạy ứng dụng
    private boolean checkFirstRun() {
        // Sử dụng SharedPreferences để lưu trạng thái đã chạy ứng dụng
        // Ở đây, giả sử tên file SharedPreferences là "MyPrefs" và tên key là "isFirstRun"
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);

        // Nếu đây là lần đầu chạy ứng dụng, lưu trạng thái đã chạy
        if (isFirstRun) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isFirstRun", false);
            editor.apply();
        }

        return isFirstRun;
    }

    // Hiển thị hộp thoại chào mừng
    private void showWelcomeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this,
                android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_welcome, null);
        builder.setView(dialogView);

        ViewPager2 viewPager = dialogView.findViewById(R.id.viewPager);
        WelcomePagerAdapter adapter = new WelcomePagerAdapter();
        viewPager.setAdapter(adapter);

        builder.setPositiveButton("Hoàn thành", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}