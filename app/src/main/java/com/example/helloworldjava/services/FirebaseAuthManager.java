package com.example.helloworldjava.services;

import android.content.Context;
import android.content.Intent;

import com.example.helloworldjava.FCM.NotificationFCM;
import com.example.helloworldjava.view.Menu.MenuActivity;
import com.example.helloworldjava.view.login.Account_Login;
import com.example.helloworldjava.view.register.Account_Register;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirebaseAuthManager {
    private FirebaseAuth firebaseAuthManager;
    private Context context;

    public FirebaseAuthManager(Context context) {
        firebaseAuthManager = FirebaseAuth.getInstance();
        this.context = context;
        isUserSignedIn();
    }

    public FirebaseAuth getFirebaseAuthManager() {
        return firebaseAuthManager;
    }

    public FirebaseUser getCurrentUser() {
        return firebaseAuthManager.getCurrentUser();
    }

    // Check if user is signed in (non-null)
    public void isUserSignedIn() {
        FirebaseUser currentUser = getCurrentUser();
        System.out.println(currentUser);
        System.out.println(context instanceof Account_Login);
        // nếu chưa đăng nhập
        if(currentUser == null) {
            // Nếu đang ko ở trang ngoài trang login và register thì bắt buộc chuyển sang
            // trang đăng nhập
            if (!(context instanceof Account_Login || context instanceof Account_Register)) {
                context.startActivity(new Intent(context, Account_Login.class));
            }
        } else {
            // Nếu đã đăng nhập mà vào trang đăng nhập và đăng ký
            if (context instanceof Account_Login  || context instanceof Account_Register) {
                context.startActivity(new Intent(context, MenuActivity.class));
            }

        }



    }

    public void signOut() {
        getFirebaseAuthManager().signOut();
        isUserSignedIn();
    }

    public void loginSuccess() {
        initNotificationID();
        context.startActivity(new Intent(context, MenuActivity.class));
    }

    private void initNotificationID()
    {
        String idNguoiDung = firebaseAuthManager.getCurrentUser().getUid();
        NotificationService notificationService = ServiceBuilder.buildService(NotificationService.class);
        Call<List<NotificationFCM>> request = notificationService.findAllNotificationById(idNguoiDung);
        request.enqueue(new Callback<List<NotificationFCM>>() {
            @Override
            public void onResponse(Call<List<NotificationFCM>> call, Response<List<NotificationFCM>> response) {
                if (response.isSuccessful()) {
                    ArrayList<NotificationFCM> notis = (ArrayList<NotificationFCM>) response.body();
                    if(notis.isEmpty() == true)
                    {
                        createNotificactionID(idNguoiDung);
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

    private void createNotificactionID(String idNguoiDung)
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
}