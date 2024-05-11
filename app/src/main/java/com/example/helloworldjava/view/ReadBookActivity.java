package com.example.helloworldjava.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworldjava.R;
import com.example.helloworldjava.model.Realm.Chuong;
import com.example.helloworldjava.model.entity.Sach;
import com.example.helloworldjava.services.ChuongService;
import com.example.helloworldjava.services.ServiceBuilder;
import com.example.helloworldjava.view.GioiThieuSach.BookDetailActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadBookActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_book);

        Intent intent =  getIntent();
        String idSach = intent.getStringExtra("idSach");

        View btnDSChuong = findViewById(R.id.btnDSChuong);
        btnDSChuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(ReadBookActivity.this, DanhsachchuongActivity.class);
                myintent.putExtra("idSach", idSach);
                startActivity(myintent);
            }
        });

        ChuongService chuongService = ServiceBuilder.buildService(ChuongService.class);
        Call<Chuong> request = chuongService.getChuong("1YbO0c6lh42znmZUc9zI");

        request.enqueue(new Callback<Chuong>() {
            @Override
            public void onResponse(@NonNull Call<Chuong> call, @NonNull Response<Chuong> response) {
                Chuong chuong = response.body();
                System.out.println(chuong);
                TextView tv_TenChuong = findViewById(R.id.tv_tenchuong);
                TextView tv_noidungChuong = findViewById(R.id.tv_noidungChuong);
                TextView tv_sochuong = findViewById(R.id.tvchuong);
                tv_TenChuong.setText(chuong.getTenChuong());
                tv_noidungChuong.setText(chuong.getNoiDung());
                tv_sochuong.setText("Chương " + chuong.getSoThuTu() + ":");
            }

            @Override
            public void onFailure(Call<Chuong> call, Throwable throwable) {

            }
        });

        ImageView imageViewComment = findViewById(R.id.imageView_comment);
        imageViewComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ReadBookActivity.this, CommentActivity.class);
                ReadBookActivity.this.startActivity(myIntent);
            }
        });



    }
    public void showPopup (View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.menu_read);
        popup.setForceShowIcon(true);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.item1){
            Toast.makeText(this, "Item 1 clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (menuItem.getItemId() == R.id.item2){
            Toast.makeText(this, "Item 2 clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (menuItem.getItemId() == R.id.item3){
            Toast.makeText(this, "Item 3 clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        else    {
            return false;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_readbook, menu);
        return true;
    }
}