package com.example.helloworldjava.view.dangtruyen;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.helloworldjava.R;
import com.example.helloworldjava.model.Realm.Chuong;
import com.example.helloworldjava.model.entity.Sach;
import com.example.helloworldjava.services.ChuongService;
import com.example.helloworldjava.services.SachService;
import com.example.helloworldjava.services.ServiceBuilder;
import com.example.helloworldjava.view.GioiThieuSach.BookDetailActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangChuongActivity extends AppCompatActivity {

    private static final String TAG = "DangChuongActivity";
    private Sach sach;
    private Chuong chuong;
    private SachService sachService = ServiceBuilder.buildService(SachService.class);
    private ChuongService chuongService = ServiceBuilder.buildService(ChuongService.class);
    private TextInputEditText txtTieuDeChuong;
    private TextInputEditText txtNoiDungChuong;
    private MaterialButton btnXacNhanThemChuong;
    private boolean isEdit = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_chuong);

        findView();
        setActionBar();

        loadSachData(getIntent().getStringExtra("idSach"));

        if (getIntent().getStringExtra("idChuong") != null) {
            isEdit = true;
            btnXacNhanThemChuong.setText("Cập nhật chương");
            getSupportActionBar().setTitle("Sửa nội dung chương");
            // get chuong data from server
            chuongService.getChuong(getIntent().getStringExtra("idChuong")).enqueue(new Callback<Chuong>() {
                @Override
                public void onResponse(Call<Chuong> call, Response<Chuong> response) {
                    if (response.isSuccessful()) {
                        chuong = response.body();
                        txtTieuDeChuong.setText(chuong.getTenChuong());
                        txtNoiDungChuong.setText(chuong.getNoiDung());
                    }
                }

                @Override
                public void onFailure(Call<Chuong> call, Throwable throwable) {
                    throwable.printStackTrace();
                }
            });
        }


        btnXacNhanThemChuong.setOnClickListener(v -> {
            if (isEdit) {
                // update chuong
                updateChuong();
            } else {
                // add new chuong
                addNewChuong();
            }

        });
    }

    public void findView() {
        // find view by id
        txtTieuDeChuong = findViewById(R.id.txtTieuDeThemChuong);
        txtNoiDungChuong = findViewById(R.id.txtNoiDungThemChuong);
        btnXacNhanThemChuong = findViewById(R.id.btnXacNhanThemChuong);
    }

    public void setActionBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        // set action bar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("THÊM CHƯƠNG MỚI");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void loadSachData(String idSach) {
        // get sach data from server
        sachService.getSach(idSach).enqueue(new Callback<Sach>() {
            @Override
            public void onResponse(Call<Sach> call, Response<Sach> response) {
                if (response.isSuccessful()) {
                    sach = response.body();
                }
            }

            @Override
            public void onFailure(Call<Sach> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    public void addNewChuong() {
        // get text from input
        String tieuDeChuong = txtTieuDeChuong.getText().toString();
        String noiDungChuong = txtNoiDungChuong.getText().toString();

        // create new chuong
        Chuong chuong = new Chuong();
        chuong.setTenChuong(tieuDeChuong);
        chuong.setNoiDung(noiDungChuong);

        chuongService.addChuong(sach.getId(), chuong).enqueue(new Callback<Chuong>() {
            @Override
            public void onResponse(Call<Chuong> call, Response<Chuong> response) {
                if (response.isSuccessful()) {
                    // finish activity and go to detail book activity
                    Intent goToBookDetailIntent = new Intent(getApplicationContext(), BookDetailActivity.class);
                    goToBookDetailIntent.putExtra("idSach", sach.getId());
                    startActivity(goToBookDetailIntent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Chuong> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    public void updateChuong() {
        // get text from input
        String tieuDeChuong = txtTieuDeChuong.getText().toString();
        String noiDungChuong = txtNoiDungChuong.getText().toString();

        // update chuong
        chuong.setTenChuong(tieuDeChuong);
        chuong.setNoiDung(noiDungChuong);

        chuongService.updateChuong(chuong.getId(), chuong).enqueue(new Callback<Chuong>() {
            @Override
            public void onResponse(Call<Chuong> call, Response<Chuong> response) {
                if (response.isSuccessful()) {
                    // finish activity and go back
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Chuong> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
