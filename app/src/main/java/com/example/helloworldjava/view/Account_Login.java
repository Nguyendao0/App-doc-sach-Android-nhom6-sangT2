package com.example.helloworldjava.view;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.helloworldjava.DemoActivity;
import com.example.helloworldjava.HomePageDemoActivity;
import com.example.helloworldjava.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Account_Login extends AppCompatActivity{
    private final String CLIENT_ID = "676414236432-8eiikdlnquc32kem27kulsuc42a7o2mb.apps.googleusercontent.com";
    private GoogleSignInClient googleSignInClient;
    private FirebaseAuth mAuth;
    private static final int RC_SIGN_IN = 9001;
    private static final String TAG = "GoogleSignIn";
    SignInButton signInButton;
    View signInWithGoogle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_account_login);
        signInButton = findViewById(R.id.sign_in_button);
        String userEmail = "selected@email.com";
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(CLIENT_ID)
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(Account_Login.this,gso);
        signInWithGoogle = findViewById(R.id.signInWithGoogle);
        //signInWithGoogle.setVisibility(View.GONE);
        signInButton.setVisibility(View.GONE);
        signInWithGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInWithGoogle(googleSignInClient);
            }
        });
        signInWithGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInWithGoogle(googleSignInClient);
            }
        });
    }
    private void signInWithGoogle(GoogleSignInClient googleSignInClient)  {

        Intent signInIntent = googleSignInClient.getSignInIntent();
        GoogleSignInAccount ss = GoogleSignIn.getLastSignedInAccount(Account_Login.this.getApplicationContext());
        if(ss != null){
            Log.w(TAG, "Google sign getLastSignedInAccount" + ss.getIdToken());
            Log.w(TAG, "Google sign getLastSignedInAccount" + ss.getEmail());
            Log.w(TAG, "Google sign getLastSignedInAccount" + ss.getAccount());
            Intent intent = new Intent(this, DemoActivity.class);
            startActivity(intent);
        }else {
            startActivityForResult(signInIntent, RC_SIGN_IN);
        }
    }
    private void signOutAndRevokeAccess(GoogleSignInClient googleSignInClient) {
        Log.w(TAG, "Google sign in signOutAndRevokeAccess");
        googleSignInClient.signOut()
                .addOnCompleteListener(this, task -> {
                    // Sign-out successful, now revoke access token
                    googleSignInClient.revokeAccess()
                            .addOnCompleteListener(Account_Login.this, task1 -> {
                                // Access token revoked, update UI or take other action
                                Log.w(TAG, "Google sign in sss");
                                updateUI(false);
                            });
                });
    }
    private void updateUI(boolean signedIn) {
        if (signedIn) {
            // User is signed in, update UI accordingly
        } else {
            Toast.makeText(getApplicationContext(), "Logout Sucsses", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.w(TAG, "Google sign in start requestCode" + requestCode);
        Log.w(TAG, "Google sign in start resultCode" + resultCode);
        Log.w(TAG, "Google sign in start data" + data.getData());
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            if(task.isSuccessful()){
                try {
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    Toast.makeText(getApplicationContext(), account.getEmail(), Toast.LENGTH_SHORT).show();
                    if(account != null){
                        Log.w(TAG, "Google sign in start" + account.getEmail());
                        Log.w(TAG, "Google sign in start" + account.getIdToken());
                    }
                    Intent intent = new Intent(this, DemoActivity.class);
                    startActivity(intent);
                }
                catch (ApiException e) {
                    // Google Sign In failed, update UI appropriately
                    Log.w(TAG, "Google sign in failed", e);
                    // ...
                }
            }
        }
    }
    public void launchSignInWithGoogle(Intent signInIntent) {
        signInActivityResultLauncher.launch(signInIntent);
    }
    ActivityResultLauncher<Intent> signInActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(result.getData());
                    Toast.makeText(Account_Login.this, result.describeContents(),Toast.LENGTH_SHORT).show();
                    //handleSignInResult(task);
                }
            }
    );
}