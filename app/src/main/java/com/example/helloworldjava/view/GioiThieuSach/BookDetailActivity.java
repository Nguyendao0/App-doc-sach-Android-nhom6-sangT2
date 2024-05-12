package com.example.helloworldjava.view.GioiThieuSach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworldjava.R;
import com.example.helloworldjava.model.Realm.ThuVienSachCaNhan;
import com.example.helloworldjava.model.entity.Sach;
import com.example.helloworldjava.services.SachService;
import com.example.helloworldjava.services.ServiceBuilder;
import com.example.helloworldjava.services.UserService;
import com.example.helloworldjava.view.DanhsachchuongActivity;
import com.example.helloworldjava.view.ReadBookActivity;

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

        UserService userService = ServiceBuilder.buildService(UserService.class);

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
                yeu_thich.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Call<ThuVienSachCaNhan> request1 = userService.addSachToThuVienSach("LTN", sach.getId());

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
}