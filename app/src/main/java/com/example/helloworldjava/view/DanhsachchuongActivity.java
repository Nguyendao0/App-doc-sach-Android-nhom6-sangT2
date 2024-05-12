package com.example.helloworldjava.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.helloworldjava.R;
import com.example.helloworldjava.model.Realm.Chuong;
import com.example.helloworldjava.model.entity.Sach;
import com.example.helloworldjava.services.ChuongService;
import com.example.helloworldjava.services.SachService;
import com.example.helloworldjava.services.ServiceBuilder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachchuongActivity extends AppCompatActivity implements DanhSachChuongAdapter.ItemClickListener {

    DanhSachChuongAdapter adapter;
    int numberOfRows = 5;
    private Sach sach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachchuong);

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

    @Override
    public void onItemClick(View view, int position) {
        Chuong chuong = adapter.getChuong(position);
        Intent goToDetailChuongIntent = new Intent(this, ReadBookActivity.class);
        goToDetailChuongIntent.putExtra("idChuong", chuong.getId());
        goToDetailChuongIntent.putExtra("idSach", sach.getId());
        startActivity(goToDetailChuongIntent);
    }
}