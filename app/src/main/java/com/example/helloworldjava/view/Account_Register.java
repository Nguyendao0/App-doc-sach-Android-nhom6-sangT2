package com.example.helloworldjava.view;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.helloworldjava.R;
import com.example.helloworldjava.model.NguoiDungModel;
import com.example.helloworldjava.model.entity.NguoiDung;
import com.example.helloworldjava.presenter.LoginPresenter;
import com.example.helloworldjava.view.user.UserActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class Account_Register extends AppCompatActivity {
    private GoogleSignInClient googleSignInClient;

    private EditText txtEmail;
    private EditText txtPassword;
    private EditText txtUsername;
    private EditText txtPhone;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_register);

        txtEmail = findViewById(R.id.username_register);
        txtPassword = findViewById(R.id.password_register);
        txtUsername = findViewById(R.id.username_register);
        btnRegister = findViewById(R.id.register);
        txtPhone = findViewById(R.id.phone);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        googleSignInClient = GoogleSignIn.getClient(this, gso);

        View signInWithGoogle = findViewById(R.id.signInWithGoogle);

        signInWithGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchSignInWithGoogle();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();
                String phone = txtPhone.getText().toString();
                String username = txtUsername.getText().toString();

                NguoiDungModel nguoiDungModel = new NguoiDungModel();
                nguoiDungModel.getNguoiDungByEmail(email, new NguoiDungModel.GetNguoiDungCallback() {
                    @Override
                    public void onNguoiDungLoaded(NguoiDung nguoiDung) {
                        Toast.makeText(Account_Register.this, "Account is exist!", Toast.LENGTH_LONG);
                    }

                    @Override
                    public void onNguoiDungNotFound() {
                        NguoiDung nguoiDung = new NguoiDung();
                        nguoiDung.setTenNguoiDung(username);
                        nguoiDung.setEmail(email);
                        nguoiDung.setMatKhau(password);
                        nguoiDungModel.createNguoiDung(nguoiDung, new NguoiDungModel.CreateNguoiDungCallback() {
                            @Override
                            public void onNguoiDungCreated(NguoiDung nguoiDung) {
                                LoginPresenter.saveNguoiDungToSharedPref(Account_Register.this, nguoiDung);
                                Intent intent = new Intent(Account_Register.this, UserActivity.class);
                                startActivity(intent);
                            }
                            @Override

                            public void onError(Exception e) {

                            }
                        });
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
            }
        });
    }

    public void launchSignInWithGoogle() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        signInActivityResultLauncher.launch(signInIntent);
    }

    ActivityResultLauncher<Intent> signInActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(result.getData());
                    handleSignInResult(task);
                }
            }
    );

    // Xử lý kết quả khi chọn "Đăng nhập bằng Google"
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            String googleId = account.getId();
            NguoiDungModel nguoiDungModel = new NguoiDungModel();
            nguoiDungModel.getNguoiDungByGoogleId(googleId, new NguoiDungModel.GetNguoiDungCallback() {
                @Override
                public void onNguoiDungLoaded(NguoiDung nguoiDung) {
                    Toast.makeText(Account_Register.this, "Account is exist!", Toast.LENGTH_LONG);

                }

                @Override
                public void onNguoiDungNotFound() {
                    NguoiDung nguoiDung = new NguoiDung();
                    nguoiDung.setTenNguoiDung(account.getDisplayName());
                    nguoiDung.setEmail(account.getEmail());
                    nguoiDung.setGoogleId(account.getId());
                    nguoiDungModel.createNguoiDung(nguoiDung, new NguoiDungModel.CreateNguoiDungCallback() {
                        @Override
                        public void onNguoiDungCreated(NguoiDung nguoiDung) {
                            LoginPresenter.saveNguoiDungToSharedPref(Account_Register.this, nguoiDung);
                            Intent intent = new Intent(Account_Register.this, UserActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onError(Exception e) {

                        }
                    });
                }

                @Override
                public void onError(Exception e) {

                }
            });
        } catch (ApiException e) {
            Log.w(TAG, e);
        }
    }
}