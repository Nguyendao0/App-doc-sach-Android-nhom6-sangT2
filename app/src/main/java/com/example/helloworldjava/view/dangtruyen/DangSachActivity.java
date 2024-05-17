package com.example.helloworldjava.view.dangtruyen;

import android.content.Context;
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
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.helloworldjava.R;
import com.example.helloworldjava.model.FirebaseStorageHelper;
import com.example.helloworldjava.model.entity.Sach;
import com.example.helloworldjava.model.entity.User;
import com.example.helloworldjava.services.FirebaseAuthManager;
import com.example.helloworldjava.services.SachService;
import com.example.helloworldjava.services.ServiceBuilder;
import com.example.helloworldjava.view.Menu.MenuActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangSachActivity extends AppCompatActivity {
    private TextInputEditText txtTenSach;
    private TextInputEditText txtNhaXuatBan;
    private TextInputEditText txtNamXuatBan;
    private TextInputEditText txtMoTa;

    private ImageView imageViewDangSach;
    private ImageView editImageDangSach;
    private FirebaseAuthManager firebaseAuthManager;
    private String defaultImage = "https://upload.wikimedia.org/wikipedia/vi/b/b7/Doraemon1.jpg";
    private boolean hasUploadImage = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_sach);

        txtTenSach = findViewById(R.id.txtNewTenSach);
        txtNhaXuatBan = findViewById(R.id.txtNewNhaXuatBan);
        txtNamXuatBan = findViewById(R.id.txtNewNamXuatBan);
        txtMoTa = findViewById(R.id.txtMoTa);
        imageViewDangSach = findViewById(R.id.imageViewDangSach);
        editImageDangSach = findViewById(R.id.editImageDangSach);
        Picasso.get().load(defaultImage).into(imageViewDangSach);

        firebaseAuthManager = new FirebaseAuthManager(this);

        editImageDangSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open the gallery to choose an image
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                galleryLauncher.launch(photoPickerIntent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_menu, menu);
        return true;
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
                            imageViewDangSach.setImageBitmap(selectedImage);
                            hasUploadImage = true;
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemSave) {
            Sach sach = new Sach();
            sach.setTenSach(txtTenSach.getText().toString());
            sach.setNhaXuatBan(txtNhaXuatBan.getText().toString());
            sach.setNamXuatBan(Integer.parseInt(txtNamXuatBan.getText().toString()));
            sach.setMota(txtMoTa.getText().toString());
            sach.setIdNguoiDung(firebaseAuthManager.getCurrentUser().getUid());

            if (sach.getImg() == null) {
                sach.setImg(defaultImage);
            }


            SachService sachService = ServiceBuilder.buildService(SachService.class);
            sachService.createSach(sach).enqueue(new Callback<Sach>() {
                @Override
                public void onResponse(Call<Sach> call, Response<Sach> response) {
                    startActivity(new Intent(DangSachActivity.this, MenuActivity.class));
                }

                @Override
                public void onFailure(Call<Sach> call, Throwable throwable) {

                }
            });
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
