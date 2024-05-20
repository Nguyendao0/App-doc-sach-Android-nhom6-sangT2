package com.example.helloworldjava.view.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.helloworldjava.R;
import com.example.helloworldjava.services.FirebaseAuthManager;
import com.example.helloworldjava.services.GoogleSignInManager;
import com.example.helloworldjava.services.NguoiDungService;
import com.example.helloworldjava.services.ServiceBuilder;
import com.example.helloworldjava.view.register.Account_Register;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Account_Login extends AppCompatActivity{
    private final String CLIENT_ID = "676414236432-8eiikdlnquc32kem27kulsuc42a7o2mb.apps.googleusercontent.com";
    private GoogleSignInClient googleSignInClient;
    private static final int RC_SIGN_IN = 9001;
    private static final String TAG = "GoogleSignIn";
    private NguoiDungService nguoiDungService;
    private FirebaseAuthManager firebaseAuthManager;
    private EditText txtEmail;
    private EditText txtPassword;
    Button btnLogin;
    Button btnRegister;
    // btnsignup
    View signInWithGoogle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_login);

        // Tạo người dùng Service
        nguoiDungService = ServiceBuilder.buildService(NguoiDungService.class);

        // Khởi tạo firebaseAuthManager
        firebaseAuthManager = new FirebaseAuthManager(this);

        txtEmail = findViewById(R.id.txtEmailLogin);
        txtPassword = findViewById(R.id.txtPasswordLogin);


        btnLogin = findViewById(R.id.btnlogin);
        signInWithGoogle = findViewById(R.id.signInWithGoogle);

        GoogleSignInManager googleSignInManager = new GoogleSignInManager(this, nguoiDungService, firebaseAuthManager);

        // Đăng nhập bằng email và password
        btnLogin.setOnClickListener(view -> signInByEmailAndPassword());

        // Sự kiện onClick cho nút đăng nhập bằng google
        signInWithGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                googleSignInManager.signIn();
            }
        });

        // chuyển hướng đăng ký
        btnRegister = findViewById(R.id.btnsignup);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Account_Login.this, Account_Register.class);
                startActivity(intent);
            }
        });
    }



    public void signInByEmailAndPassword() {
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        FirebaseAuth firebaseAuth = firebaseAuthManager.getFirebaseAuthManager();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success");
                    firebaseAuthManager.loginSuccess();
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                }
            }
        });
    }

}