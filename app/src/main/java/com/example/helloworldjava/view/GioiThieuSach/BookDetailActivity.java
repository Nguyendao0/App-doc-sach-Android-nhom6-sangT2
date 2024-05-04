package com.example.helloworldjava.view.GioiThieuSach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworldjava.MainActivity;
import com.example.helloworldjava.R;
import com.example.helloworldjava.model.entity.Sach;
import com.example.helloworldjava.services.SachService;
import com.example.helloworldjava.services.ServiceBuilder;
import com.example.helloworldjava.view.QRGen;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduce_book);

        SachService sachService = ServiceBuilder.buildService(SachService.class);
        Call<Sach> request = sachService.getSach("NVGGDJnCQhAkLe5UscYu");

        request.enqueue(new Callback<Sach>() {
            @Override
            public void onResponse(@NonNull Call<Sach> call, @NonNull Response<Sach> response) {
                Sach sach = response.body();
                System.out.println(sach);
                TextView tv_TenTruyen = findViewById(R.id.tv_TenTruyen);
                TextView tv_moTa = findViewById(R.id.tv_mo_ta_sach);
                TextView tv_theLoai = findViewById(R.id.tv_Theloai);
                tv_TenTruyen.setText(sach.getTenSach());
                tv_moTa.setText(sach.getMota());
                tv_theLoai.setText("");
                sach.getListTheLoai().forEach(theLoaiSach -> {
                    tv_theLoai.append(theLoaiSach.getTenTheLoai() + ", ");
                });

                Button btnQRGen = findViewById(R.id.btn_lay_ma_QR);
                btnQRGen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent myIntent = new Intent(BookDetailActivity.this, QRGen.class);
                        myIntent.putExtra("idSach", sach.getId() + "-" + sach.getTenSach());
                        startActivity(myIntent);
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


    public  void gotoQRGen(View view ){
        Intent intent = new Intent(this, QRGen.class);
        startActivity(intent);
    }

}