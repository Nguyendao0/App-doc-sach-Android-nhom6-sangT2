package com.example.helloworldjava.view.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.helloworldjava.R;
import com.example.helloworldjava.model.entity.Sach;
import com.example.helloworldjava.services.FirebaseAuthManager;
import com.example.helloworldjava.services.SachService;
import com.example.helloworldjava.services.ServiceBuilder;
import com.example.helloworldjava.view.GioiThieuSach.BookDetailActivity;
import com.google.android.material.button.MaterialButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListBooksActity extends AppCompatActivity implements ListBooksRecyclerViewAdapter.ItemClickListener {
    ListBooksRecyclerViewAdapter adapter;
    int numberOfColumns = 3;
    private SachService sachService = ServiceBuilder.buildService(SachService.class);
    private FirebaseAuthManager firebaseAuthManager;
    private MaterialButton btnDangSach;
    private TextView tvTongSoSach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_books);

        firebaseAuthManager = new FirebaseAuthManager(this);

        ImageView btnBackHome = findViewById(R.id.iconBackHome);

        btnDangSach = findViewById(R.id.btnDangSach);
        btnDangSach.setVisibility(View.GONE);

        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String titleListBooks = extras.getString("titleListBooks");
            TextView txtListBooks = findViewById(R.id.txtListMore);
            txtListBooks.setText(titleListBooks);

            if (titleListBooks.equalsIgnoreCase("THƯ VIỆN CỦA BẠN"))
            {
//                btnDangSach.setVisibility(View.VISIBLE);
//                btnDangSach.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        startActivity(new Intent(ListBooksActity.this, DangSachActivity.class));
//                    }
//                });
                loadListThuVienCuaBan();
            } else {
                loadKhoSach();
            }

        }


    }

    public void loadListThuVienCuaBan() {
        String idNguoiDung = firebaseAuthManager.getCurrentUser().getUid();
        sachService.getListSachYourLibrary(idNguoiDung).enqueue(new Callback<List<Sach>>() {
            @Override
            public void onResponse(Call<List<Sach>> call, Response<List<Sach>> response) {
                List<Sach> listYourLibrarySach = response.body();
                RecyclerView recyclerView = findViewById(R.id.list_book);
                recyclerView.setLayoutManager(new GridLayoutManager(ListBooksActity.this, numberOfColumns));
                adapter = new ListBooksRecyclerViewAdapter(ListBooksActity.this, listYourLibrarySach);
                adapter.setClickListener(ListBooksActity.this);
                recyclerView.setAdapter(adapter);
                tvTongSoSach = findViewById(R.id.tv_tong_sach);
                tvTongSoSach.setText("(" + listYourLibrarySach.size() + ")");
            }

            @Override
            public void onFailure(Call<List<Sach>> call, Throwable throwable) {

            }
        });
    }

    public void loadKhoSach() {
        String idNguoiDung = firebaseAuthManager.getCurrentUser().getUid();
        sachService.getListSachs().enqueue(new Callback<List<Sach>>() {
            @Override
            public void onResponse(Call<List<Sach>> call, Response<List<Sach>> response) {
                List<Sach> listYourLibrarySach = response.body();
                RecyclerView recyclerView = findViewById(R.id.list_book);
                recyclerView.setLayoutManager(new GridLayoutManager(ListBooksActity.this, numberOfColumns));
                adapter = new ListBooksRecyclerViewAdapter(ListBooksActity.this, listYourLibrarySach);
                adapter.setClickListener(ListBooksActity.this);
                recyclerView.setAdapter(adapter);
                tvTongSoSach = findViewById(R.id.tv_tong_sach);
                tvTongSoSach.setText("(" + listYourLibrarySach.size() + ")");
            }

            @Override
            public void onFailure(Call<List<Sach>> call, Throwable throwable) {

            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        Sach sach = adapter.getItem(position);
        Intent goToBookDetailIntent = new Intent(this, BookDetailActivity.class);
        goToBookDetailIntent.putExtra("idSach", sach.getId());
        startActivity(goToBookDetailIntent);

    }
}