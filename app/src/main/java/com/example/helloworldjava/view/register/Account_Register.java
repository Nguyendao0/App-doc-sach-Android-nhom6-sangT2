package com.example.helloworldjava.view.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.helloworldjava.R;
import com.example.helloworldjava.model.entity.NguoiDung;
import com.example.helloworldjava.services.FirebaseAuthManager;
import com.example.helloworldjava.services.GoogleSignInManager;
import com.example.helloworldjava.services.NguoiDungService;
import com.example.helloworldjava.services.ServiceBuilder;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Account_Register extends AppCompatActivity {
    private final String CLIENT_ID = "676414236432-8eiikdlnquc32kem27kulsuc42a7o2mb.apps.googleusercontent.com";
    private GoogleSignInClient googleSignInClient;
    private static final int RC_SIGN_IN = 9001;
    private static final String TAG = "GoogleSignIn";
    private NguoiDungService nguoiDungService;
    private FirebaseAuthManager firebaseAuthManager;
    private EditText txtEmail;
    private EditText txtPassword;
    private EditText txtUsername;
    private EditText txtPhone;
    Button registerButton;
    View signInWithGoogle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_register);

        // Tạo người dùng Service
        nguoiDungService = ServiceBuilder.buildService(NguoiDungService.class);

        // Khởi tạo firebaseAuthManager
        firebaseAuthManager = new FirebaseAuthManager(this);

        txtEmail = findViewById(R.id.email_register);
        txtPassword = findViewById(R.id.password_register);
        txtUsername = findViewById(R.id.username_register);
        txtPhone = findViewById(R.id.phone_register);


        registerButton = findViewById(R.id.btnRegister);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(CLIENT_ID)
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);
        signInWithGoogle = findViewById(R.id.signUpWithGoogle);

        GoogleSignInManager googleSignInManager = new GoogleSignInManager(this, nguoiDungService, firebaseAuthManager);


        // Đăng nhập bằng email và password
        registerButton.setOnClickListener(view -> createUserByEmailAndPassword());

        // Sự kiện onClick cho nút đăng nhập bằng google
        signInWithGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleSignInManager.signIn();
            }
        });
    }

    public void createUserByEmailAndPassword() {
        String phone = txtPhone.getText().toString();
        String username = txtUsername.getText().toString();
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        FirebaseAuth firebaseAuth = firebaseAuthManager.getFirebaseAuthManager();
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success");

                    NguoiDung newNguoiDung = new NguoiDung();
                    newNguoiDung.setEmail(email);
                    newNguoiDung.setTenNguoiDung(username);
                    newNguoiDung.setMatKhau(password);
                    newNguoiDung.setUuid(firebaseAuth.getCurrentUser().getUid());
                    createNewUser(newNguoiDung);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                }
            }
        });
    }

    private void createNewUser(NguoiDung newNguoiDung) {
        Log.w("Start Call Api", "Start Call Api POST /nguoi-dung: CreateNewNguoiDung");
        nguoiDungService.createNewNguoiDung(newNguoiDung).enqueue(new Callback<NguoiDung>() {
            @Override
            public void onResponse(Call<NguoiDung> call, Response<NguoiDung> response) {
                if(response.isSuccessful()){
                    Log.w(TAG, "Creat new user respone ");
                    // Lưu vào csdl thành công
                    firebaseAuthManager.loginSuccess();
                }
            }

            @Override
            public void onFailure(Call<NguoiDung> call, Throwable throwable) {
                Log.w(TAG, "Creat new APIUserModel eror respone" + throwable);

            }
        });
    }

}