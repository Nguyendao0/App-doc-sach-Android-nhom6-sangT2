package com.example.helloworldjava.view.dangtruyen;

import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.helloworldjava.R;
import com.example.helloworldjava.model.FirebaseStorageHelper;
import com.example.helloworldjava.model.entity.Sach;
import com.example.helloworldjava.model.entity.TheLoaiSach;
import com.example.helloworldjava.services.FirebaseAuthManager;
import com.example.helloworldjava.services.SachService;
import com.example.helloworldjava.services.ServiceBuilder;
import com.example.helloworldjava.services.TheLoaiService;
import com.example.helloworldjava.view.Menu.MenuActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangSachFragment extends Fragment {

    private TextInputEditText txtTenSach;
    private TextInputEditText txtNhaXuatBan;
    private TextInputEditText txtNamXuatBan;
    private TextInputEditText txtMoTa;

    private ImageView imageViewDangSach;
    private ImageView editImageDangSach;
    private FirebaseAuthManager firebaseAuthManager;
    private MaterialButton btnDangSach;
    private String defaultImage = "https://upload.wikimedia.org/wikipedia/vi/b/b7/Doraemon1.jpg";
    private boolean hasUploadImage = false;
    private List<TheLoaiSach> listTheLoai = new ArrayList<>();
    private String[] listTheLoaiName;
    private List<Integer> listSelectedTheLoaiPosition = new ArrayList<>();
    private boolean[] selectedTheLoai;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_dang_sach, container, false);

        txtTenSach = view.findViewById(R.id.txtNewTenSach);
        txtNhaXuatBan = view.findViewById(R.id.txtNewNhaXuatBan);
        txtNamXuatBan = view.findViewById(R.id.txtNewNamXuatBan);
        txtMoTa = view.findViewById(R.id.txtMoTa);
        imageViewDangSach = view.findViewById(R.id.imageViewDangSach);
        editImageDangSach = view.findViewById(R.id.editImageDangSach);
        Picasso.get().load(defaultImage).into(imageViewDangSach);

        firebaseAuthManager = new FirebaseAuthManager(getContext());

        TheLoaiService theLoaiService = ServiceBuilder.buildService(TheLoaiService.class);
        theLoaiService.getListTheLoai().enqueue(new Callback<List<TheLoaiSach>>() {
            @Override
            public void onResponse(Call<List<TheLoaiSach>> call, Response<List<TheLoaiSach>> response) {
                listTheLoai = response.body();
                listTheLoaiName = new String[listTheLoai.size()];
                // Iterate through the list and populate the array
                for (int i = 0; i < listTheLoai.size(); i++) {
                    listTheLoaiName[i] = listTheLoai.get(i).getTenTheLoai();
                }
                selectedTheLoai = new boolean[listTheLoaiName.length];
            }

            @Override
            public void onFailure(Call<List<TheLoaiSach>> call, Throwable throwable) {

            }
        });

        editImageDangSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open the gallery to choose an image
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                galleryLauncher.launch(photoPickerIntent);
            }
        });

        btnDangSach = view.findViewById(R.id.btnXacNhanDangSach);
        btnDangSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSach();
            }
        });

        TextView tvSelectTheLoai = view.findViewById(R.id.tvSelectTheLoai);
        tvSelectTheLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                // set title
                builder.setTitle("Select Language");

                // set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(listTheLoaiName, selectedTheLoai, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        // check condition
                        if (b) {
                            // when checkbox selected
                            // Add position  in lang list
                            listSelectedTheLoaiPosition.add(i);
                            // Sort array list
                        } else {
                            // when checkbox unselected
                            // Remove position from langList
                            listSelectedTheLoaiPosition.remove(Integer.valueOf(i));
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        // use for loop
                        for (int j = 0; j < listSelectedTheLoaiPosition.size(); j++) {
                            // concat array value
                            stringBuilder.append(listTheLoaiName[listSelectedTheLoaiPosition.get(j)]);
                            // check condition
                            if (j != listSelectedTheLoaiPosition.size() - 1) {
                                // When j value  not equal
                                // to lang list size - 1
                                // add comma
                                stringBuilder.append(", ");
                            }
                        }
                        // set text on textView
                        tvSelectTheLoai.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dismiss dialog
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // use for loop
                        for (int j = 0; j < selectedTheLoai.length; j++) {
                            // remove all selection
                            selectedTheLoai[j] = false;
                            // clear language list
                            listSelectedTheLoaiPosition.clear();
                            // clear text view value
                            tvSelectTheLoai.setText("");
                        }
                    }
                });
                // show dialog
                builder.show();
            }
        });

        return view;
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
                            imageStream = getContext().getContentResolver().openInputStream(imageUri);
                            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                            imageViewDangSach.setImageBitmap(selectedImage);
                            hasUploadImage = true;
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });


    public void saveSach() {
        Sach sach = new Sach();
        sach.setTenSach(txtTenSach.getText().toString());
        sach.setNhaXuatBan(txtNhaXuatBan.getText().toString());
        sach.setNamXuatBan(Integer.parseInt(txtNamXuatBan.getText().toString()));
        sach.setMota(txtMoTa.getText().toString());
        sach.setIdNguoiDung(firebaseAuthManager.getCurrentUser().getUid());

        // Lay danh sach the loai
        List<String> listTheLoaiId = new ArrayList<>();
        for (int j = 0; j < listSelectedTheLoaiPosition.size(); j++) {
            // concat array value
            listTheLoaiId.add(listTheLoai.get(listSelectedTheLoaiPosition.get(j)).getId());
        }
        sach.setListTheLoaiId(listTheLoaiId);


        if (hasUploadImage) {
            uploadImageAndCreateSach(sach, ((BitmapDrawable) imageViewDangSach.getDrawable()).getBitmap());
        } else {
            sach.setImg(defaultImage);
            createSach(sach);
        }

    }

    public void uploadImageAndCreateSach(Sach sach, Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] byteArray = baos.toByteArray();

        FirebaseStorageHelper firebaseStorageHelper = new FirebaseStorageHelper();
        StorageReference storageRef = firebaseStorageHelper.getReference();
        StorageReference imagesRef = storageRef.child("images/sach-image/" + sach.getTenSach() + ".jpg");
        firebaseStorageHelper.uploadImageAndGetDownloadUrl(byteArray, imagesRef, new FirebaseStorageHelper.UploadImageCallback() {
            @Override
            public void onUploadSuccess(Uri uri) {
                sach.setImg(uri.toString());
                createSach(sach);
            }

            @Override
            public void onUploadFailure(Exception e) {
                Log.e("AccountSettingPresenter", "onUploadFailure: ", e);
            }
        });
    }

    public void createSach(Sach sach) {
        SachService sachService = ServiceBuilder.buildService(SachService.class);
        sachService.createSach(sach).enqueue(new Callback<Sach>() {
            @Override
            public void onResponse(Call<Sach> call, Response<Sach> response) {
                startActivity(new Intent(getContext(), MenuActivity.class));
            }

            @Override
            public void onFailure(Call<Sach> call, Throwable throwable) {

            }
        });
    }

}