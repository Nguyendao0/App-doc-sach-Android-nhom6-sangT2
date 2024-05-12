package com.example.helloworldjava.services;

import static androidx.activity.result.ActivityResultCallerKt.registerForActivityResult;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworldjava.model.entity.NguoiDung;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GoogleSignInManager {
    private final String TAG = "GoogleSignIn";
    private final String CLIENT_ID = "644178781913-mnkr53hiho084kdd9geb77maossdijr6.apps.googleusercontent.com";
    private AppCompatActivity activity;
    private GoogleSignInClient googleSignInClient;
    private ActivityResultLauncher<Intent> signInLauncher;
    private NguoiDungService nguoiDungService;
    private FirebaseAuthManager firebaseAuthManager;
    private GoogleApiClient googleApiClient;


    public GoogleSignInManager(AppCompatActivity activity, NguoiDungService nguoiDungService, FirebaseAuthManager firebaseAuthManager) {
        this.activity = activity;
        this.firebaseAuthManager = firebaseAuthManager;
        this.nguoiDungService = nguoiDungService;
        configureGoogleSignIn();
        initializeActivityLauncher();
    }

    private void configureGoogleSignIn() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(CLIENT_ID)
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(activity, gso);
        googleApiClient = new GoogleApiClient.Builder(activity).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
        googleApiClient.connect();
    }

    private void initializeActivityLauncher() {
        signInLauncher = activity.registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        handleSignInResult(GoogleSignIn.getSignedInAccountFromIntent(result.getData()));
                    } else {
                        Log.e(TAG, "Sign-in failed");
                    }
                });
    }

    public void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();

        if (googleApiClient != null && googleApiClient.isConnected()) {
            googleApiClient.clearDefaultAccountAndReconnect();
        }

        signInLauncher.launch(signInIntent);
    }

    private void handleSignInResult(com.google.android.gms.tasks.Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Got an ID token from Google. Use it to authenticate
            // with Firebase.
            AuthCredential firebaseCredential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
            Toast.makeText(activity, account.getEmail(), Toast.LENGTH_SHORT).show();

            // Thực hiện đăng nhập bằng firebaseAuth
            FirebaseAuth firebaseAuth = firebaseAuthManager.getFirebaseAuth();
            firebaseAuth.signInWithCredential(firebaseCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success");

                        FirebaseUser newUser = firebaseAuthManager.getCurrentUser();
                        // Lưu newUser vào csdl người dùng
                        NguoiDung newNguoiDung = new NguoiDung();
                        newNguoiDung.setEmail(newUser.getEmail());
                        newNguoiDung.setAvatar(newUser.getPhotoUrl().toString());
                        newNguoiDung.setTenNguoiDung(newUser.getDisplayName());
                        newNguoiDung.setMatKhau("123123");

                        // Bắt đầu gọi api
                        nguoiDungService.loginByGoogleId(account.getId()).enqueue(new Callback<NguoiDung>() {
                            @Override
                            public void onResponse(Call<NguoiDung> call, Response<NguoiDung> response) {
                                // Nếu chưa có người dùng này trong csdl thì tạo mới
                                if (response.code() == 404) {
                                    nguoiDungService.createNewNguoiDung(newNguoiDung).enqueue(new Callback<NguoiDung>() {
                                        @Override
                                        public void onResponse(Call<NguoiDung> call, Response<NguoiDung> response) {
                                            if(response.isSuccessful()){
                                                // Lưu vào csdl thành công
                                                firebaseAuthManager.loginSuccess();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<NguoiDung> call, Throwable throwable) {
                                        }
                                    });
                                } else { // Nếu đã có thì đăng nhập thành công
                                    firebaseAuthManager.loginSuccess();
                                }
                            }

                            @Override
                            public void onFailure(Call<NguoiDung> call, Throwable throwable) {

                            }
                        });



                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                        Toast.makeText(activity, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (ApiException e) {
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
    }
}