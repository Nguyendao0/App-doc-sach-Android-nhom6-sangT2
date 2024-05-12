package com.example.helloworldjava.services;

import android.content.Context;
import android.content.Intent;

import com.example.helloworldjava.DemoActivity;
import com.example.helloworldjava.view.Menu.MenuActivity;
import com.example.helloworldjava.view.login.Account_Login;
import com.example.helloworldjava.view.register.Account_Register;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseAuthManager {
    private FirebaseAuth firebaseAuth;
    private Context context;

    public FirebaseAuthManager(Context context) {
        firebaseAuth = FirebaseAuth.getInstance();
        this.context = context;
        isUserSignedIn();
    }

    public FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }

    public FirebaseUser getCurrentUser() {
        return firebaseAuth.getCurrentUser();
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
        getFirebaseAuth().signOut();
        isUserSignedIn();
    }

    public void loginSuccess() {
        context.startActivity(new Intent(context, MenuActivity.class));
    }
}