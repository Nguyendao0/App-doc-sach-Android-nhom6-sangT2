package com.example.helloworldjava.view.user;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.helloworldjava.R;
import com.example.helloworldjava.model.FirebaseStorageHelper;
import com.example.helloworldjava.model.entity.User;
import com.example.helloworldjava.presenter.AccountSettingPresenter;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AccountSettingActivity extends AppCompatActivity implements AccountSettingView {
    private TextInputEditText txtUsername;
    private TextInputEditText txtEmail;
    private TextInputEditText txtPassword;
    private ImageView ivButtonEditAvatar;
    private ImageView ivAvatar;
    private AccountSettingPresenter accountSettingPresenter;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);

        // Find the views
        txtUsername = findViewById(R.id.usernameTextInputEditText);
        txtPassword = findViewById(R.id.passwordTextInputEditText);
        txtEmail = findViewById(R.id.emailTextInputEditText);
        ivButtonEditAvatar = findViewById(R.id.editAvatarImageView);
        ivAvatar = findViewById(R.id.userAvatarImage);


        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ivButtonEditAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the gallery to choose an image
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                galleryLauncher.launch(photoPickerIntent);
            }
        });

        // Create the presenter
        accountSettingPresenter = new AccountSettingPresenter(this);
        accountSettingPresenter.fillUserDataToEditView();
    }

    ActivityResultLauncher<Intent> galleryLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        // Get the image URI
                        // Set the image to the ImageView
                        try {
                            final Uri imageUri = data.getData();
                            final InputStream imageStream;
                            imageStream = getContentResolver().openInputStream(imageUri);
                            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                            ivAvatar.setImageBitmap(selectedImage);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemSave) {
            // Save the user data
            user.setUsername(txtUsername.getText().toString());
            user.setPassword(txtPassword.getText().toString());
            user.setEmail(txtEmail.getText().toString());
            accountSettingPresenter.uploadImage(user, ((BitmapDrawable) ivAvatar.getDrawable()).getBitmap());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void fillUserDataToEditView(User user) {
        this.user = user;
        txtUsername.setText(user.getUsername());
        txtPassword.setText(user.getPassword());
        txtEmail.setText(user.getEmail());
        Picasso.get().load(user.getAvatar()).into(ivAvatar);
    }

    @Override
    public void onUploadSuccess(String uri) {
        user.setAvatar(uri);
        accountSettingPresenter.updateUserData(user);
        navigateUpTo(getParentActivityIntent());
    }
}