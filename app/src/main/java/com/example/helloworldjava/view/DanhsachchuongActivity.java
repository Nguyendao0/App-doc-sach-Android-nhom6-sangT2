package com.example.helloworldjava.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworldjava.R;
import com.example.helloworldjava.model.Realm.Chuong;
import com.example.helloworldjava.model.entity.Sach;
import com.example.helloworldjava.services.ChuongService;
import com.example.helloworldjava.services.FirebaseAuthManager;
import com.example.helloworldjava.services.SachService;
import com.example.helloworldjava.services.ServiceBuilder;
import com.example.helloworldjava.view.Menu.MenuActivity;
import com.example.helloworldjava.view.dangtruyen.DangChuongActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachchuongActivity extends AppCompatActivity implements DanhSachChuongAdapter.ItemClickListener {

    DanhSachChuongAdapter adapter;
    int numberOfRows = 5;
    private Sach sach;
    private FirebaseAuthManager firebaseAuthManager = new FirebaseAuthManager(this);
    private Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachchuong);

        setToolBar();

        Intent intent =  getIntent();
        String idSach = intent.getStringExtra("idSach");

        SachService sachService = ServiceBuilder.buildService(SachService.class);
        Call<Sach> requestone = sachService.getSach(idSach);
        requestone.enqueue(new Callback<Sach>() {
            @Override
            public void onResponse(Call<Sach> call, Response<Sach> response) {
                sach = response.body();
                System.out.println(sach);
                TextView tv_TenSach = findViewById(R.id.tv_tenSach);
                tv_TenSach.setText(sach.getTenSach());
                ImageView imageView = findViewById(R.id.imageSach);
                Picasso.get().load(sach.getImg()).into(imageView);

                if (firebaseAuthManager.getCurrentUser().getUid().equals(sach.getIdNguoiDung())) {
                    menu.findItem(R.id.action_them_chuong).setVisible(true);
                    menu.findItem(R.id.action_xoa_chuong).setVisible(true);
                }
            }

            @Override
            public void onFailure(Call<Sach> call, Throwable throwable) {

            }
        });

        ChuongService chuongService = ServiceBuilder.buildService(ChuongService.class);
        Call<List<Chuong>> request = chuongService.getListChuong(idSach);

        request.enqueue(new Callback<List<Chuong>>() {
            @Override
            public void onResponse(Call<List<Chuong>> call, Response<List<Chuong>> response) {
                List<Chuong> listChuong = response.body();
                RecyclerView recyclerView = findViewById(R.id.list_chuong_item);
                recyclerView.setLayoutManager(new LinearLayoutManager(DanhsachchuongActivity.this, LinearLayoutManager.VERTICAL, false));
                adapter = new DanhSachChuongAdapter(DanhsachchuongActivity.this, listChuong);
                adapter.setClickListener(DanhsachchuongActivity.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Chuong>> call, Throwable throwable) {

            }
        });


    }

    public void setToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("DANH SÁCH CHƯƠNG");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.muc_luc_menu, menu);
        menu.findItem(R.id.action_them_chuong).setVisible(false);
        menu.findItem(R.id.action_xoa_chuong).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_them_chuong) {
            Intent intent = new Intent(DanhsachchuongActivity.this, DangChuongActivity.class);
            intent.putExtra("idSach", sach.getId());
            startActivity(intent);
        } else if (item.getItemId() == R.id.action_xoa_chuong) {
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            //Yes button clicked
                            SachService sachService = ServiceBuilder.buildService(SachService.class);
                            Call<Void> request = sachService.deleteSach(sach.getId());
                            request.enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    Intent intent = new Intent(DanhsachchuongActivity.this, MenuActivity.class);
                                    startActivity(intent);
                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable throwable) {

                                }
                            });
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            //No button clicked
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Bạn có thật sự muốn xóa sách này không?").setPositiveButton("Có", dialogClickListener)
                    .setNegativeButton("Không", dialogClickListener).show();

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(View view, int position) {
        Chuong chuong = adapter.getChuong(position);
        Intent goToDetailChuongIntent = new Intent(this, ReadBookActivity.class);
        goToDetailChuongIntent.putExtra("idChuong", chuong.getId());
        goToDetailChuongIntent.putExtra("idSach", sach.getId());
        goToDetailChuongIntent.putExtra("TenSach", sach.getTenSach());
        startActivity(goToDetailChuongIntent);
    }
}