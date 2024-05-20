package com.example.helloworldjava.view.GioiThieuSach;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworldjava.FCM.TopicFCM;
import com.bumptech.glide.Glide;
import com.example.helloworldjava.R;
import com.example.helloworldjava.model.Realm.ThuVienSachCaNhan;
import com.example.helloworldjava.model.entity.Sach;
import com.example.helloworldjava.services.FirebaseAuthManager;
import com.example.helloworldjava.services.SachService;
import com.example.helloworldjava.services.ServiceBuilder;
import com.example.helloworldjava.services.UserService;
import com.example.helloworldjava.view.DanhsachchuongActivity;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDetailActivity extends AppCompatActivity {
    private String idSach;
    private ImageView imageViewQRGen;
    private ImageView btnOpenQRDiaglog;
    private ActivityResultLauncher<Intent> cameraLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduce_book);

        // Gia tri mac dinh
        idSach = "NVGGDJnCQhAkLe5UscYu";
        Bundle extras = getIntent().getExtras();

        // Nếu có idSach lúc mở activity
        if (extras != null) {
            idSach = extras.getString("idSach");
        }

        findView();

        SachService sachService = ServiceBuilder.buildService(SachService.class);
        Call<Sach> request = sachService.getSach(idSach);

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        UserService userService = ServiceBuilder.buildService(UserService.class);
        Intent intent = getIntent();
        String idsach = intent.getStringExtra("IdSach");

        SachService sachService = ServiceBuilder.buildService(SachService.class);
        Call<Sach> request = sachService.getSach(idsach);

        request.enqueue(new Callback<Sach>() {
            @Override
            public void onResponse(@NonNull Call<Sach> call, @NonNull Response<Sach> response) {
                Sach sach = response.body();
                //System.out.println(sach);
                ImageView imgSach = findViewById(R.id.img_TrangBia);
                TextView tv_TenTruyen = findViewById(R.id.tv_TenTruyen);
                TextView tv_moTa = findViewById(R.id.tv_mo_ta_sach);
                TextView tv_theLoai = findViewById(R.id.tv_Theloai);
                TextView tv_nhaxuatban = findViewById(R.id.tv_TenTacGia);
                tv_TenTruyen.setText(sach.getTenSach());
                tv_moTa.setText(sach.getMota());
                tv_nhaxuatban.setText(sach.getNhaXuatBan());
                Glide.with(BookDetailActivity.this).load(sach.getImg()).into(imgSach);
                tv_theLoai.setText("");
                if (sach.getListTheLoai() != null) {
                    sach.getListTheLoai().forEach(theLoaiSach -> {
                        tv_theLoai.append(theLoaiSach.getTenTheLoai() + ", ");
                    });
                }


                View btnDocSach = findViewById(R.id.btnDocSach);
                btnDocSach.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent myintent = new Intent(BookDetailActivity.this, DanhsachchuongActivity.class);
                        myintent.putExtra("idSach", sach.getId());
                        startActivity(myintent);
                    }
                });

                LinearLayout yeu_thich = findViewById(R.id.yeu_thich);
                FirebaseAuthManager firebaseAuthManager = new FirebaseAuthManager(getApplicationContext());
                yeu_thich.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TopicFCM topicFCM = new TopicFCM();
                        topicFCM.subcribeTopic(sach.getId());
                        Call<ThuVienSachCaNhan> request1 = userService.addSachToThuVienSach(firebaseAuthManager.getCurrentUser().getUid(), sach.getId());

                        request1.enqueue(new Callback<ThuVienSachCaNhan>() {
                            @Override
                            public void onResponse(Call<ThuVienSachCaNhan> call, Response<ThuVienSachCaNhan> response) {



                            }

                            @Override
                            public void onFailure(Call<ThuVienSachCaNhan> call, Throwable throwable) {
                                System.out.println("Lỗi main" + throwable.getMessage());
                            }
                        });

                    }
                });
            }

            @Override
            public void onFailure(Call<Sach> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(BookDetailActivity.this, "Failed to retrieve book.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void findView() {
        btnOpenQRDiaglog = findViewById(R.id.btnOpenQRDiaglog);
        btnOpenQRDiaglog.setOnClickListener(v -> {
            openQRGenDialog();
        });


    }



    public void openQRGenDialog() {
        Dialog builder = new Dialog(this);
        builder.setTitle("Cài đặt");
        // Tạo layout cho hộp thoại
        View view = getLayoutInflater().inflate(R.layout.dialog_qr_gen, null);
        builder.setContentView(view);

        // find view
        imageViewQRGen = view.findViewById(R.id.imageViewQRGen);
        geneateQR();

        builder.show();
    }

    private void geneateQR() {
        String text = idSach;
        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            // Generate QR code with size equal half of screen width and height
            BitMatrix matrix = writer.encode(text, BarcodeFormat.QR_CODE, 800, 800);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(matrix);
            imageViewQRGen.setImageBitmap(bitmap);
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }
    }




}